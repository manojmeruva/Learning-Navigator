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

import com.example.demo.models.Student;
import com.example.demo.models.Subject;
import com.example.demo.services.ISubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final ISubjectService subjectService;

    @Autowired
    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    
    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject savedSubject = subjectService.createSubject(subject);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    
    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long subjectId) {
        Subject subject = subjectService.getSubject(subjectId);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    
    @GetMapping("/{subjectId}/students")
    public ResponseEntity<List<Student>> getStudentsFromSubject(@PathVariable Long subjectId) {
        Subject subject = subjectService.getSubject(subjectId);
        List<Student> students = subject.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
