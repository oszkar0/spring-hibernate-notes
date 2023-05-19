package com.oskar.cruddemo.dao;

import com.oskar.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student theStudent);

    Student findById(int id);

}
