package com.example.rest.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.example.rest.model.Employee;

public interface EmployeeService {

    // POST
    Optional<Employee> saveEmployee(Employee employee);
    // PUT
    Optional<Employee> updateEmployee(ObjectId id , Employee employee);
    // GET
    List<Employee> getAllEmployees();
    // GET
    Optional<Employee> getEmployeeById(ObjectId id);
    // DELETE
    Boolean deleteEmployeeById(ObjectId id);
}
