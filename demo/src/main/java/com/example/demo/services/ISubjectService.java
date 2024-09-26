package com.example.demo.services;

import com.example.demo.models.Subject;

public interface ISubjectService {

    Subject createSubject(Subject subject);

    Subject getSubject(Long subjectId);

}