package com.example.rest.service.implementation;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.model.Employee;
import com.example.rest.repository.mongodb.EmployeeRepository;
import com.example.rest.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> saveEmployee(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        return Optional.ofNullable(saved);
    }

    // @Override
    // public Optional<Employee> updateEmployee(ObjectId id, Employee employee) {
    // Optional<Employee> existing = employeeRepository.findById(id);

    // if (existing.isPresent()) {
    // Employee e = existing.get();
    // e.setName(employee.getName());
    // e.setEmail(employee.getEmail());
    // e.setDepartment(employee.getDepartment());
    // e.setSalary(employee.getSalary());
    // return Optional.of(employeeRepository.save(e));
    // }

    // return Optional.empty();
    // }

    @Override
    public Optional<Employee> updateEmployee(ObjectId id, Employee employee) {

        // Protect against null request body
        if (employee == null) {
            return Optional.empty();
        }

        return employeeRepository.findById(id).map(existing -> {

            // Update only if fields are not null
            if (employee.getName() != null) {
                existing.setName(employee.getName());
            }

            if (employee.getEmail() != null) {
                existing.setEmail(employee.getEmail());
            }

            if (employee.getDepartment() != null) {
                existing.setDepartment(employee.getDepartment());
            }

            // Primitive double can't be null, so check logical condition
            if (employee.getSalary() > 0) {
                existing.setSalary(employee.getSalary());
            }

            return employeeRepository.save(existing);
        });
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(ObjectId id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Boolean deleteEmployeeById(ObjectId id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
