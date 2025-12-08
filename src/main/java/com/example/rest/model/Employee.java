package com.example.rest.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "employees")
public class Employee {

    private ObjectId objectId;
    private String name;
    private String email;
    private String department;
    private double salary;
}
