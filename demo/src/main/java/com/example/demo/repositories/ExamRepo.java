package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Exam;

public interface ExamRepo extends JpaRepository<Exam, Long> {}