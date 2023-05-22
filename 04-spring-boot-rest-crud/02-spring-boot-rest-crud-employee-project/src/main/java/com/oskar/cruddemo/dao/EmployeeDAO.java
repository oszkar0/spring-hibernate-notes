package com.oskar.cruddemo.dao;

import com.oskar.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
