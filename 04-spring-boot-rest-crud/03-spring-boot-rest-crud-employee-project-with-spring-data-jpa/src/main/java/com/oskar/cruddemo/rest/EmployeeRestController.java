package com.oskar.cruddemo.rest;

import com.oskar.cruddemo.dao.EmployeeDAO;
import com.oskar.cruddemo.entity.Employee;
import com.oskar.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    //quick and dirty: inject employee dao
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employess" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    //add mapping for POST /employees to add an employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //also just in case they pass an id in JSON set id to 0
        //this is to force a save of new item, instead of update

        theEmployee.setId(0);

        Employee employee = employeeService.save(theEmployee);

        return employee;
    }

    //example of input from postman
    //we set the method to post, and choose raw data, json
//    {
//        "firstName": "Hector",
//            "lastName":  "Perez",
//            "email": "hectorperez@g.com"
//    }

    //add mapping for PUT /employees to edit employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee employee = employeeService.save(theEmployee);

        return employee;
    }

    //add mapping for DELETE /employees/{employeeId} to delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee deletedEmployee = employeeService.findById(employeeId);

        if(deletedEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }



}
