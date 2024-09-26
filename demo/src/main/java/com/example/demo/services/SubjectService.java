package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Subject;
import com.example.demo.repositories.SubjectRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SubjectService implements ISubjectService {
    private final SubjectRepo subjectRepository;

    @Autowired
    public SubjectService(SubjectRepo subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId)
            .orElseThrow(() -> new EntityNotFoundException("Subject not found"));
    }
}
