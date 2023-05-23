package com.oskar.cruddemo.dao;

import com.oskar.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //that it, no need to write any code
}
