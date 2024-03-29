package com.oskar.cruddemo.dao;

import com.oskar.cruddemo.entity.Course;
import com.oskar.cruddemo.entity.Instructor;
import com.oskar.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor); //since we have CascadeType.ALL this will also save details object
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        //get courses
        List<Course> courses = instructor.getCourses();

        //break associations of all courses for the instructor
        //otherwise well get exception constraint violation since we cant delete
        //instructor who is a foregin key for some courses
        for(Course course: courses){
            course.setInstructor(null);
        }

        entityManager.remove(instructor); //<- will also remove instructor detail because of CascadeType.ALL
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        //to break bi-directional link
        //remove the associated object reference
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> coursesQuery = entityManager.createQuery(
                "from Course where instructor.id = :id", Course.class);

        coursesQuery.setParameter("id", id);


        List<Course> courses = coursesQuery.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> instructorWithCoursesQuery = entityManager.createQuery(
                "select i from Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "where i.id = :id", Instructor.class);


        instructorWithCoursesQuery.setParameter("id", id);

        Instructor instructor = instructorWithCoursesQuery.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);

        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class
        );
        query.setParameter("data" , id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class
        );
        query.setParameter("data" , id);

        Course course = query.getSingleResult();

        return course;
    }
}
