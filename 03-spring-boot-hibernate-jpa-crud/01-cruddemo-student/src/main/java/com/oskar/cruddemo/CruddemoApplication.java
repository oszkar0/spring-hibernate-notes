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
			//createStudent(studentDAO);
			createMultipleStudents(studentDAO);
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

	public void createMultipleStudents(StudentDAO studentDAO){
		//create student object
		System.out.println("Creating 3 student objects...");
		Student student1 = new Student("Marek", "Kongo", "omarkon@g.com");
		Student student2 = new Student("Olek", "Soran", "ols@g.com");
		Student student3 = new Student("Bartek", "Kalon", "bkaloy@g.com");

		//save the student object to db
		System.out.println("Saving the students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
		
	}
}
