package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Exam;
import com.example.demo.models.Student;
import com.example.demo.models.Subject;
import com.example.demo.repositories.ExamRepo;
import com.example.demo.repositories.StudentRepo;
import com.example.demo.repositories.SubjectRepo;
import com.example.demo.services.StudentService;





@SpringBootTest
public class StudentServiceTests {

    @Mock
    private StudentRepo studentRepository;

    @Mock
    private SubjectRepo subjectRepository;

    @Mock
    private ExamRepo examRepository;

    @InjectMocks
    private StudentService studentService;

    
    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setName("John Doe");

        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);

        assertNotNull(createdStudent);
        assertEquals("John Doe", createdStudent.getName());
    }

    
    @Test
    public void testEnrollInSubject() {
        Student student = new Student();
        student.setRegistrationId(1L);
        student.setName("John Doe");

        Subject subject = new Subject();
        subject.setSubjectId(1L);
        subject.setName("Mathematics");

        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student));
        Mockito.when(subjectRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(subject));

        studentService.enrollInSubject(student, subject);

        assertTrue(student.getSubjects().contains(subject));
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
    }

    
    @Test
    public void testRegisterForExam() {
        Student student = new Student();
        student.setRegistrationId(1L);
        student.setName("John Doe");

        Subject subject = new Subject();
        subject.setSubjectId(1L);
        subject.setName("Mathematics");

        Exam exam = new Exam();
        exam.setExamId(1L);
        exam.setSubject(subject);

        student.getSubjects().add(subject);

        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student));
        Mockito.when(examRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(exam));

        studentService.registerForExam(student, exam);

        assertTrue(student.getExams().contains(exam));
        Mockito.verify(studentRepository, Mockito.times(1)).save(student);
    }

    
}
