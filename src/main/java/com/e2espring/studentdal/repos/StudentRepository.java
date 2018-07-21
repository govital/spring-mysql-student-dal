package com.e2espring.studentdal.repos;

import com.e2espring.studentdal.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
