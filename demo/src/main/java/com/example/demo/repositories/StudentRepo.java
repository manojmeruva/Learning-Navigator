package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {}