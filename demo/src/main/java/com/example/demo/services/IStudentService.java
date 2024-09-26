package com.example.demo.services;

import com.example.demo.models.Exam;
import com.example.demo.models.Student;
import com.example.demo.models.Subject;

public interface IStudentService {
    Student createStudent(Student student);
    Student getStudent(Long studentId);
    void enrollInSubject(Student student, Subject subject);
    void registerForExam(Student student, Exam exam);
}