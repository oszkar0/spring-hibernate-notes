package com.oskar.cruddemo;

import com.oskar.cruddemo.dao.AppDAO;
import com.oskar.cruddemo.entity.Instructor;
import com.oskar.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createInstructor(appDAO);
			//findInstructorById(appDAO);
			deleteInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO){
		Instructor tempInstructor = new Instructor("Oskar", "Szysiak","osz@g.com" );
		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube.com/oskar", "pizza");
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor); // <- this will also save instructorDetails
	}

	private void findInstructorById(AppDAO appDAO){
		int id = 1;
		System.out.println("Finding instructor with id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println(instructor);
	}

	private void deleteInstructor(AppDAO appDAO){
		int id = 1;
		System.out.println("Deleting instructor with id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}
}
