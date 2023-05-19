package com.oskar.cruddemo;

import com.oskar.cruddemo.dao.StudentDAO;
import com.oskar.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean	//from spirng boot //executed after spring beans have been loaded
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			updateStudent(studentDAO);
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

	public void readStudent(StudentDAO studentDAO){
		//create student object
		System.out.println("Creating new student object...");
		Student student = new Student("Ola", "Kola", "olkol@g.com");
		//save the student object to db
		System.out.println("Saving the student...");
		studentDAO.save(student);
		//display id of saved student
		int theId = student.getId();
		System.out.println("Saved student. Generated id: " + theId);
		//retrieve  student based on id
		Student retrievedStudent = studentDAO.findById(theId);
		//display student
		System.out.println("Found student: " + retrievedStudent.toString());
	}

	public void queryForStudents(StudentDAO studentDAO){
		List<Student> students = studentDAO.findAll();

		for(Student s: students){
			System.out.println(s);
		}
	}

	public void queryForStudentsByLastName(StudentDAO studentDAO){
		//get a list of students
		List<Student> list = studentDAO.findByLastName("Kongo");
		//display list of students
		for(Student s: list){
			System.out.println(s);
		}
	}

	public void updateStudent(StudentDAO studentDAO){
		//retrieve student based on the id
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student student = studentDAO.findById(studentId);
		//change fist name
		System.out.println("Updating student...");
		student.setLastName("Kopara");
		//update the student
		studentDAO.update(student);
		//display the updated student
		System.out.println("Updated student: " + student);
	}
}
