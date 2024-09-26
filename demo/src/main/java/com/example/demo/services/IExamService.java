package com.example.demo.services;

import com.example.demo.models.Exam;

public interface IExamService {

    Exam createExam(Exam exam);

    Exam getExam(Long examId);

}