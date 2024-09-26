package com.example.demo.models;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;
    private String name;

    @ManyToMany
    private List<Subject> subjects;

    @ManyToMany
    private List<Exam> exams;

    public List<Subject> getSubjects() {
        if (subjects == null) {
            subjects = new ArrayList<>();
        }
        return subjects;
    }

    public List<Exam> getExams() {
        if (exams == null) {
            exams = new ArrayList<>();
        }
        return exams;
    }

   
}