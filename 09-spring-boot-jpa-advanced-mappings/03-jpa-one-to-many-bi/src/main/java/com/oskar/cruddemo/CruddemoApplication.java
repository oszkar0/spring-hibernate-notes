package com.oskar.cruddemo;

import com.oskar.cruddemo.dao.AppDAO;
import com.oskar.cruddemo.entity.Course;
import com.oskar.cruddemo.entity.Instructor;
import com.oskar.cruddemo.entity.InstructorDetail;
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

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createInstructor(appDAO);
			//findInstructorById(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetailById(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesOfInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			updateCourse(appDAO);
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

	private void findInstructorDetailById(AppDAO appDAO){
		int id = 2;
		System.out.println("Finding instructor detail with id: " + id);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println(instructorDetail);
	}

	private void deleteInstructorDetail(AppDAO appDAO){
		int id = 3;
		System.out.println("Deleting instructor detail with id: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO){
		Instructor tempInstructor =
				new Instructor("Marek", "Landrin","marla@g.com" );
		InstructorDetail tempInstructorDetail =
				new InstructorDetail("youtube.com/marek", "football");
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course tempCourse1 = new Course("Football tricks 1");
		Course tempCourse2 = new Course("Football tricks 3");
		Course tempCourse3 = new Course("How to become pro footballer");

		tempInstructor.addCourse(tempCourse1);
		tempInstructor.addCourse(tempCourse2);
		tempInstructor.addCourse(tempCourse3);

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempCourse1 + "\n"
				+ tempCourse2 + "\n" +  tempCourse3 );
		appDAO.save(tempInstructor); // <- this will also save instructorDetails
	}

	public void findInstructorWithCourses(AppDAO appDAO){
		int id = 1;
		System.out.println("Finding instructor id:" + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Found instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());
	}

	public void findCoursesOfInstructor(AppDAO appDAO){
		int id = 1;
		System.out.println("Finding instructor id:" + id);

		Instructor instructor = appDAO.findInstructorById(id);

		List<Course> courses = appDAO.findCoursesByInstructorId(instructor.getId());
		instructor.setCourses(courses);

		System.out.println("Found instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());
	}

	public void findInstructorWithCoursesJoinFetch(AppDAO appDAO){
		int id = 1;
		System.out.println("Finding instructor id:" + id);

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("Found instructor: " + instructor);
		System.out.println("the associated courses: " + instructor.getCourses());
	}

	private void updateInstructor(AppDAO appDAO){
		int id = 1;

		//find instructor
		System.out.println("Finding instructor id:" + id);
		Instructor tempInstructor = appDAO.findInstructorById(id);

		//update the instructor
		System.out.println("Updating the instructor: " + id);
		tempInstructor.setLastName("TESTERINSTRUCTOR");

		appDAO.update(tempInstructor);

		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO){
		int id = 10;

		//find course
		System.out.println("Finding course: " + id);
		Course tempCourse = appDAO.findCourseById(id);

		//update the course
		System.out.println("Updating the course: " + id);
		tempCourse.setTitle(":DDDDDD hehe");
		appDAO.update(tempCourse);

		System.out.println("Done");
	}
}
