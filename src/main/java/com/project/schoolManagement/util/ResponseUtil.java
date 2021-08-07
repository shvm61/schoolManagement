package com.project.schoolManagement.util;

import com.project.schoolManagement.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<Object> errorResponse(Object data, String message, HttpStatus status) {
        Response res = new Response(data, false, message);
        return new ResponseEntity<>(res, status);
    }

    public static ResponseEntity<Object> successResponse(Object data, HttpStatus status) {
        Response res = new Response(data, true, null);
        return new ResponseEntity<>(res, status);
    }
}
