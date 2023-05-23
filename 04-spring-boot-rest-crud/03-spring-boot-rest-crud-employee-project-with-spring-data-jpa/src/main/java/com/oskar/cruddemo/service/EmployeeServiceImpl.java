package com.oskar.cruddemo.service;

import com.oskar.cruddemo.dao.EmployeeRepository;
import com.oskar.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;
        if(result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Couldn't find employee with id - " + id);
        }

        return employee;
    }
    // we might not use @Transactional because JpaRepository provides it for us
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    // we might not use @Transactional because JpaRepository provides it for us
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
