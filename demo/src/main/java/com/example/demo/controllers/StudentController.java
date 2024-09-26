package com.example.demo.controllers;



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
import com.example.demo.models.Subject;
import com.example.demo.services.ExamService;
import com.example.demo.services.IExamService;
import com.example.demo.services.IStudentService;
import com.example.demo.services.ISubjectService;
import com.example.demo.services.SubjectService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final IStudentService studentService;
    private final ISubjectService subjectService;
    private final IExamService examService;

    @Autowired
    public StudentController(IStudentService studentService, ISubjectService subjectService, IExamService examService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.examService = examService;
    }

    
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

  
    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<String> enrollInSubject(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {
        Student student = studentService.getStudent(studentId);
        Subject subject = subjectService.getSubject(subjectId);
        studentService.enrollInSubject(student, subject);
        return ResponseEntity.ok("Student enrolled in subject successfully.");
    }

    @PostMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<String> registerForExam(
            @PathVariable Long studentId,
            @PathVariable Long examId) {
        Student student = studentService.getStudent(studentId);
        Exam exam = examService.getExam(examId);
        studentService.registerForExam(student, exam);
        return ResponseEntity.ok("Student registered for exam successfully.");
    }

    
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudent(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
