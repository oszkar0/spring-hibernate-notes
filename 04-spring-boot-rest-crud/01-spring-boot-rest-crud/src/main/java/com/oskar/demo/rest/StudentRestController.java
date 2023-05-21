package com.oskar.demo.rest;

import com.oskar.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;
    //define PostConstruct
    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();

        students.add(new Student("Oskar", "Szysiak"));
        students.add(new Student("Marek", "Marecki"));
        students.add(new Student("Norbert", "Norbercki"));
    }

    //define endpoint /students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    //define endpoint /students/{studentId} - return student at index
    //it gives and exception if we give index out of bounds, we neeed to handle exception
    @GetMapping("/students/{studentId}")
    public Student getStudentAtIndex(@PathVariable int studentId){
        return students.get(studentId);
    }
}
