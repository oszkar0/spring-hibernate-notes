package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService employeeService;
	@Autowired
	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}


	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get the employees from db
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
				//folder
		return "employees/list-employees";
	}

	@GetMapping("/showFormAdd")
	public String showFormForAdd(Model theModel){
		//create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);
                //folder
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		//save the employee
		employeeService.save(employee);
		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/list"; //post-redirect-get
	}

	@GetMapping("/showFormUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model model){
		//get the employee from db
		Employee employee = employeeService.findById(theId);
		model.addAttribute("employee", employee);
		//send over to our form
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delteEmployee(@RequestParam("employeeId") int theId, Model model){
		//delete the employee
		employeeService.deleteById(theId);
		//redirect to the /employees/list
		return "redirect:/employees/list";
	}
}









