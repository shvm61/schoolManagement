package com.project.schoolManagement.model;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    @JsonIgnore
    @UpdateTimestamp
    private Instant updatedAt;

    @JsonIgnore
    @CreationTimestamp
    private Instant createdAt;
}
