package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import com.example.rest.dto.StudentDTO;
import com.example.rest.model.Student;

public interface StudentService {

    // POST
    Optional<Student> saveStudent(Student student);

    //  PUT
    Optional<Student> updateStudent(String id,Student student);

    // GET
    List<Student> getAllStudents();
    // GET
    Optional<Student> getStudentNyId(String id);

    // DELETE
    Boolean deleteStudentById(String id);

    // PATCH
    Optional<Student> updateNameById(String id,StudentDTO studentDTO);
    // PATCH
    Optional<Student> updateCityById(String id,StudentDTO studentDTO);

    // OPTIONS
    List<String> getAllApplicableMethods();

    // HEAD
    Boolean isStudentExistById(String id);
}
