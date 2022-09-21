package edu.baylor.cs.example;

import edu.baylor.cs.example.model.Student;
import edu.baylor.cs.example.repository.ContactRepository;
import edu.baylor.cs.example.repository.CourseRepository;
import edu.baylor.cs.example.repository.StudentRepository;
import edu.baylor.cs.example.repository.UniversityRepository;
import edu.baylor.cs.example.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExampleTest {
    @Autowired
    private StudentRepository studentRepository; // @DataJpaTest will initialize a transactional repository

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CourseRepository courseRepository;

    private MyService service;

    @BeforeEach
    public void initService() {
        service = new MyService(studentRepository, contactRepository, universityRepository, courseRepository);
        service.populate();
    }

    @Test
    public void getStudentsTest() {
        List<Student> students = service.getStudents(null);
        assert (students.size() == 2);
    }

    @Test
    public void findByMinimumAgeTest() {
        List<Student> students = studentRepository.findByMinimumAge(21);
        assert (students.size() == 1);
    }
}
