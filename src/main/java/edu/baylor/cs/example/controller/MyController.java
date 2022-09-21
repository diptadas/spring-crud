package edu.baylor.cs.example.controller;

import edu.baylor.cs.example.model.Contact;
import edu.baylor.cs.example.model.Course;
import edu.baylor.cs.example.model.Student;
import edu.baylor.cs.example.model.University;
import edu.baylor.cs.example.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    MyService service;

    @GetMapping("/populate")
    public ResponseEntity<String> populate() {
        service.populate();
        return new ResponseEntity<>("Data populated", HttpStatus.OK);
    }

    // ========== Student ==========

    @GetMapping(("/students"))
    public ResponseEntity<List<Student>> getStudents(@RequestParam(required = false) String name) {
        List<Student> students = service.getStudents(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Student student = service.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student updated = service.createStudent(student);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        Student updated = service.updateStudent(id, student);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
        service.deleteStudent(id);
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

    // ========== Contact ==========

    @GetMapping(("/contacts"))
    public ResponseEntity<List<Contact>> getContacts() {
        List<Contact> contacts = service.getContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // ========== University ==========

    @GetMapping(("/universities"))
    public ResponseEntity<List<University>> getUniversities() {
        List<University> universities = service.getUniversities();
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }

    // ========== Course ==========

    @GetMapping(("/courses"))
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = service.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}