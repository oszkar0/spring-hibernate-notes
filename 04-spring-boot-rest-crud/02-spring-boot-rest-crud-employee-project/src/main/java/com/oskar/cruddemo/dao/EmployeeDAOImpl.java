package com.oskar.cruddemo.dao;

import com.oskar.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> getAllEmployees = entityManager.createQuery("from Employee", Employee.class);

        List<Employee> employees = getAllEmployees.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        //get employee
        Employee employee = entityManager.find(Employee.class, id);

        //return employee
        return employee;
    }

    @Override
    //we dont use transactional, it will be managed at service layer
    public Employee save(Employee theEmployee) {
        //save employee
        Employee employee = entityManager.merge(theEmployee); // <- if id == 0 then insert/save else update - thats how merge works

        //return employee from db!!!
        return employee;
    }

    @Override
    //we dont use transactional, it will be managed at service layer
    public void deleteById(int id) {
        //first find the employee
        Employee employee = entityManager.find(Employee.class, id);

        //remove
        entityManager.remove(employee);
    }
}
