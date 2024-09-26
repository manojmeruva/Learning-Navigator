package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Exam;
import com.example.demo.repositories.ExamRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExamService implements IExamService {
    private final ExamRepo examRepository;

    @Autowired
    public ExamService(ExamRepo examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam getExam(Long examId) {
        return examRepository.findById(examId)
            .orElseThrow(() -> new EntityNotFoundException("Exam not found"));
    }
}
