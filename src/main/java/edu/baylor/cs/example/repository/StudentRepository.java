package edu.baylor.cs.example.repository;

import edu.baylor.cs.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    @Query(value = "SELECT * FROM STUDENT WHERE AGE > ?1", nativeQuery = true)
    List<Student> findByMinimumAge(int age);
}
