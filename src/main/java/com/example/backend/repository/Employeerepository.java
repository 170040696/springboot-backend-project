package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Employee;

@Repository
public interface Employeerepository extends JpaRepository<Employee,Long> {

}
