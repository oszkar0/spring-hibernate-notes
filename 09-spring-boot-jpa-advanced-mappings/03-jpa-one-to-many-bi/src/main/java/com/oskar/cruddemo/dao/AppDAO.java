package com.oskar.cruddemo.dao;

import com.oskar.cruddemo.entity.Course;
import com.oskar.cruddemo.entity.Instructor;
import com.oskar.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    void deleteInstructorDetailById(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);
}
