package com.e2espring.studentdal;

import com.e2espring.studentdal.entities.Student;
import com.e2espring.studentdal.repos.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@SpringBootTest
public class StudentdalApplicationTests {

    @Configuration
    static class ContextConfiguration {

        // this bean will be injected into the Test class
        @Bean
        public StudentRepository orderService() {
            // set properties, etc.
            return Mockito.mock(StudentRepository.class);
        }
    }

    @Autowired
    private StudentRepository studentRepositoryMock;

    Student student = new Student();

    @Before
    public void setup() {
        student.setName("John");
        student.setCourse("Java Web Service");
        student.setFee(30d);
        List<Student> studentTestDb = Collections.emptyList();
        given(studentRepositoryMock.findById(1l)).willReturn(Optional.of(student));
    }

    @Test
    public void contextLoads() {

//        studentRepository.save(student);
        when(studentRepositoryMock.save(student)).thenReturn(student);
    }

    @Test
    public void testFindStudentById() {

        student.setName("John");
        student.setCourse("Java Web Service");
        student.setFee(30d);

        when(studentRepositoryMock.findById(1l).get()).thenReturn(student);
        System.out.println(student);
    }

}
