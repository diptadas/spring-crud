package edu.baylor.cs.example.service;

import edu.baylor.cs.example.model.*;
import edu.baylor.cs.example.repository.ContactRepository;
import edu.baylor.cs.example.repository.CourseRepository;
import edu.baylor.cs.example.repository.StudentRepository;
import edu.baylor.cs.example.repository.UniversityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MyService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    CourseRepository courseRepository;

    public void populate() {
        Student student1 = new Student();
        student1.setName("student-1");
        student1.setAge(21);
        student1.setGender(Gender.MALE);
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("student-2");
        student2.setAge(22);
        student2.setGender(Gender.FEMALE);
        studentRepository.save(student2);

        Contact contact1 = new Contact();
        contact1.setAddress("contact-1");
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setAddress("contact-2");
        contactRepository.save(contact2);

        University university1 = new University();
        university1.setName("university-1");
        universityRepository.save(university1);

        Course course1 = new Course();
        course1.setName("course-1");
        courseRepository.save(course1);

        // add relations in owning side and save

        student1.setContact(contact1);
        student1.setUniversity(university1);
        student1.getCourses().add(course1);
        studentRepository.save(student1);

        student2.setContact(contact2);
        student2.setUniversity(university1);
        student2.getCourses().add(course1);
        studentRepository.save(student2);
    }

    public List<Student> getStudents(String name) {
        if (name == null) {
            return studentRepository.findAll();
        } else {
            return studentRepository.findByName(name);
        }
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            return student.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No student record exist for given id");
        }
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No student record exist for given id");
        }
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}