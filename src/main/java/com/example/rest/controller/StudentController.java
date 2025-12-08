package com.example.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rest.dto.StudentDTO;
import com.example.rest.model.Student;
import com.example.rest.service.StudentService;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST - Create
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return studentService.saveStudent(student)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    // PUT - Full Update
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable String id,
                                          @RequestBody Student student) {
        return studentService.updateStudent(id, student)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET - All
    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    // GET - By ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable String id) {
        return studentService.getStudentNyId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        if (studentService.deleteStudentById(id)) {
            return ResponseEntity.ok("Student deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH - Update Name
    @PatchMapping("/{id}/name")
    public ResponseEntity<Student> updateName(@PathVariable String id,
                                              @RequestBody StudentDTO dto) {
        return studentService.updateNameById(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH - Update City
    @PatchMapping("/{id}/city")
    public ResponseEntity<Student> updateCity(@PathVariable String id,
                                              @RequestBody StudentDTO dto) {
        return studentService.updateCityById(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // OPTIONS
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<List<String>> options() {
        return ResponseEntity.ok(studentService.getAllApplicableMethods());
    }

    // HEAD
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> head(@PathVariable String id) {
        if (studentService.isStudentExistById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
