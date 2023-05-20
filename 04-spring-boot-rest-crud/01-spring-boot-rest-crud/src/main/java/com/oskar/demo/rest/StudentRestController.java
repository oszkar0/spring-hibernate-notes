package com.oskar.demo.rest;

import com.oskar.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    //define endpoint /students
    @GetMapping("/students")
    public List<Student> getStudents(){
        //now we hardcode it but it could be database query
        List<Student> students = new ArrayList<>();

        students.add(new Student("Oskar", "Szysiak"));
        students.add(new Student("Marek", "Marecki"));
        students.add(new Student("Norbert", "Norbercki"));

        return students;
    }
}
