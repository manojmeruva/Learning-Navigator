package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Exam;
import com.example.demo.models.Student;
import com.example.demo.models.Subject;
import com.example.demo.repositories.StudentRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService implements IStudentService{
    private final StudentRepo studentRepository;

    @Autowired
    public StudentService(StudentRepo studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId)
            .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    public void enrollInSubject(Student student, Subject subject) {
        if (!student.getSubjects().contains(subject)) {
            student.getSubjects().add(subject);
            studentRepository.save(student);
        }
    }

    public void registerForExam(Student student, Exam exam) {
        if (student.getSubjects().contains(exam.getSubject()) && !student.getExams().contains(exam)) {
            student.getExams().add(exam);
            studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student must be enrolled in the corresponding subject before registering for the exam.");
        }
    }
}
