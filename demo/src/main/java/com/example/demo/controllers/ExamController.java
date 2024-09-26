package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Exam;
import com.example.demo.models.Student;
import com.example.demo.services.IExamService;
import com.example.demo.services.IStudentService;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final IExamService examService;
    private final IStudentService studentService;

    @Autowired
    public ExamController(IExamService examService, IStudentService studentService) {
        this.examService = examService;
        this.studentService = studentService;
    }

    
    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam savedExam = examService.createExam(exam);
        return new ResponseEntity<>(savedExam, HttpStatus.CREATED);
    }

    
    @PostMapping("/{examId}/students/{studentId}")
    public ResponseEntity<String> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        Exam exam = examService.getExam(examId);
        Student student = studentService.getStudent(studentId);
        studentService.registerForExam(student, exam);
        return ResponseEntity.ok("Student successfully registered for the exam.");
    }

    
    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExam(@PathVariable Long examId) {
        Exam exam = examService.getExam(examId);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    
    @GetMapping("/{examId}/students")
    public ResponseEntity<List<Student>> getStudentsForExam(@PathVariable Long examId) {
        Exam exam = examService.getExam(examId);
        List<Student> students = exam.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}