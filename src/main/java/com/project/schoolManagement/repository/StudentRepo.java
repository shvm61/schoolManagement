package com.project.schoolManagement.repository;

import java.util.Collection;
import java.util.List;

import com.project.schoolManagement.model.Section;
import com.project.schoolManagement.model.Student;
import com.project.schoolManagement.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lombok.Value;

public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findBySection(Section section);

    NameOnly getStudentById(Long id);

    public static interface NameOnly {

        String getFirstName();

        String getLastName();

    }

    public static interface Proj {

        String getFirstName();

        String getLastName();

        Long getSectionClassTeacherId();

    }

    @Value
    public class NameClass {
        String firstName, lastName;
    }
}
