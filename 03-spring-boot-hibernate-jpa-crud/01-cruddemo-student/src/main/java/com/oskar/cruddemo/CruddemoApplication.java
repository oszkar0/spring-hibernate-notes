package com.oskar.cruddemo;

import com.oskar.cruddemo.dao.StudentDAO;
import com.oskar.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean	//from spirng boot //executed after spring beans have been loaded
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO){
		//create student object
		System.out.println("Creating new student object...");
		Student student = new Student("Oskar", "Szysiak", "oskszy@g.com");
		//save the student object to db
		System.out.println("Saving the student...");
		studentDAO.save(student);
		//display id of saved student
		System.out.println("Saved student. Generated id: " + student.getId());
	}
}
