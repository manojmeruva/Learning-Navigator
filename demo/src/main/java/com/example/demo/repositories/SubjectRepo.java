package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {}