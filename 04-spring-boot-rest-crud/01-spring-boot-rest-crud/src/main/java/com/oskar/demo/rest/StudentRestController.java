package com.oskar.demo.rest;

import com.oskar.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //check if index exists
        if(studentId > students.size() - 1 || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return students.get(studentId);
    }


    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //add another exception handler to catch any exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        //create StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
