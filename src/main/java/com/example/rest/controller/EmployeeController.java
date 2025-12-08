package com.example.rest.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rest.model.Employee;
import com.example.rest.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // CREATE
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable ObjectId id,
                                           @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET ALL
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable ObjectId id) {
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        return emp.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ObjectId id) {
        if (employeeService.deleteEmployeeById(id)) {
            return ResponseEntity.ok("Employee deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
