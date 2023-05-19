package com.oskar.cruddemo.dao;

import com.oskar.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor ijection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    //dont need to add @transactional, beocuse it is just a query
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    //dont need to add @transactional, beocuse it is just a query
    public List<Student> findAll() {
        //create query
        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY lastName ASC", Student.class);

        //return query results
        return query.getResultList();
    }

    @Override
    //dont need to add @transactional, beocuse it is just a query
    public List<Student> findByLastName(String theLastName) {
        //create query
        TypedQuery<Student> query = entityManager.createQuery(
                "From Student WHERE lastName=:theLastName", Student.class);
        //set query params
        query.setParameter("theLastName", theLastName);
        //return query results
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //retrieve the student
        Student student = entityManager.find(Student.class, id);

        //delete the student
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }
}
