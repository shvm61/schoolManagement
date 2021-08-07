package com.project.schoolManagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String LastName;

    private String mob;

    @JsonIgnore
    @UpdateTimestamp
    private Date updatedAt;

    @OneToOne
    private User user;

    @JsonIgnore
    @CreationTimestamp
    private Date createdAt;

    @ManyToMany
    private List<Subject> subjects;

    public Teacher(String firstName, String lastName, String mob, User user, List<Subject> subjects) {
        this.firstName = firstName;
        LastName = lastName;
        this.mob = mob;
        this.user = user;
        this.subjects = subjects;
    }

}
