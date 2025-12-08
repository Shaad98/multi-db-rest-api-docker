package com.example.rest.service.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.dto.StudentDTO;
import com.example.rest.model.Student;
import com.example.rest.repository.postgres.StudentRepository;
import com.example.rest.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> saveStudent(Student student) {
        Student saved = studentRepository.save(student);
        return Optional.ofNullable(saved);
    }

    // @Override
    // public Optional<Student> updateStudent(String id, Student student) {
    // Optional<Student> existing = studentRepository.findById(id);

    // if (existing.isPresent()) {
    // Student s = existing.get();
    // s.setName(student.getName());
    // s.setCity(student.getCity());
    // return Optional.of(studentRepository.save(s));
    // }
    // return Optional.empty();
    // }

    @Override
    public Optional<Student> updateStudent(String id, Student student) {

        // Protect against null request body
        if (student == null) {
            return Optional.empty();
        }

        return studentRepository.findById(id).map(existing -> {

            // Update only non-null fields
            if (student.getName() != null) {
                existing.setName(student.getName());
            }

            if (student.getCity() != null) {
                existing.setCity(student.getCity());
            }

            return studentRepository.save(existing);
        });
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentNyId(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Boolean deleteStudentById(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Student> updateNameById(String id, StudentDTO studentDTO) {
        return studentRepository.findById(id).map(student -> {
            student.setName(studentDTO.getKey());
            return studentRepository.save(student);
        });
    }

    @Override
    public Optional<Student> updateCityById(String id, StudentDTO studentDTO) {
        return studentRepository.findById(id).map(student -> {
            student.setCity(studentDTO.getKey());
            return studentRepository.save(student);
        });
    }

    @Override
    public List<String> getAllApplicableMethods() {
        return Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD", "OPTIONS");
    }

    @Override
    public Boolean isStudentExistById(String id) {
        return studentRepository.existsById(id);
    }
}
