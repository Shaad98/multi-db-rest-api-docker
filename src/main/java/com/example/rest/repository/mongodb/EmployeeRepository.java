package com.example.rest.repository.mongodb;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.rest.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee,ObjectId>{

}
