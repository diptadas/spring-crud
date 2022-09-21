# Spring Boot JPA & CRUD Example

## Run tests

`$ mvn test`

## Run the application

`$ mvn spring-boot:run`

## APIs

- `GET /api/populate`


- `GET /api/students`
- `GET /api/students?name=StudentName`
- `GET /api/students/{id}`
- `POST /api/students`
- `PUT /api/students/{id}`
- `DELETE /api/students/{id}`


- `GET /api/contacts`
- `GET /api/universities`
- `GET /api/courses`

## Entity relations

- Enumeration

```java
public enum Gender {
    MALE,
    FEMALE
}

public class Student {
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
```

- One to One

```java
public class Student {
    @OneToOne // owning-side
    @JoinColumn(name = "contact_id")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Contact contact;
}

public class Contact {
    @OneToOne(mappedBy = "contact") // inverse-side
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Student student;
}
```

- Many to One

```java
public class Student {
    @ManyToOne // owning-side
    @JoinColumn(name = "university_id")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private University university;
}

public class University {
    @OneToMany(mappedBy = "university") // inverse-side
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Student> students = new HashSet<>();
}
```

- Many to Many

```java
public class Student {
    @ManyToMany // owning-side
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Course> courses = new HashSet<>();
}

public class Course {
    @ManyToMany(mappedBy = "courses") // inverse-side
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Student> students = new HashSet<>();
}
```