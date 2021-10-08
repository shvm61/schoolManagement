package com.project.schoolManagement.controller;

import com.project.schoolManagement.config.MessagingConfig;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("")
    public String rabbitMQ(@RequestParam("var") String var) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, var, m -> {
            m.getMessageProperties().setHeader("color", "red");
            return m;
        });
        return "done";
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Message var) {
        String temp = new String(var.getBody());
        System.out.println("Message recieved from queue : " + new String(var.getBody()));
    }
}
