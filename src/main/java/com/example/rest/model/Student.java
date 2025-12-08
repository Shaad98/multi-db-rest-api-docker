package com.example.rest.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "students")
public class Student {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue
    @UuidGenerator
    private String id;
    private String name;
    private String city;
}
