package com.e2espring.studentdal;

import com.e2espring.studentdal.entities.Student;
import com.e2espring.studentdal.repos.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentdalApplicationTests2 {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void setup() {
        Student student = new Student();
        student.setName("John");
        student.setCourse("Java Web Service");
        student.setFee(30d);
        entityManager.persist(student);
        entityManager.flush();
        studentRepository.save(student);
    }

    @Test
    public void testSaveStudent() {
        Assertions.assertThat(studentRepository.findById(1l).toString()).containsIgnoringCase("30.0");
        Assertions.assertThat(studentRepository.findById(1l).toString()).containsIgnoringCase("john");
    }

    @Test
    public void testUpdateStudent() {

        Student student = studentRepository.findById(2l).get();
        student.setFee(40d);
        studentRepository.save(student);
        System.out.println(studentRepository.findAll());
        Assertions.assertThat(studentRepository.findById(2l).toString()).containsIgnoringCase("40.0");
        System.out.println(student);
    }

    @Test
    public void testDeleteStudent() {
        studentRepository.deleteById(3l);
        Assertions.assertThat(!studentRepository.findById(3l).isPresent());
    }

}
