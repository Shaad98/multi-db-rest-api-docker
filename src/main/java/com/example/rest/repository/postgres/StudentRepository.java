package com.example.rest.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.model.Student;

public interface StudentRepository extends JpaRepository<Student,String>{

}
