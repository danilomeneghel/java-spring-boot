package course.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Student {

    private Long id;

    @Size(min = 3, message = "Firstname is invalid")
    private String firstName;

    @Size(min = 3, message = "Lastname is invalid")
    private String lastName;

    @Size(min = 2, message = "Department is invalid")
    private String department;

    @NotEmpty
    @Email
    private String email;

    private Set<Course> courses = new HashSet<Course>(0);

    public Student() {
    }

    public Student(String firstName, String lastName, String department, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "student_course", joinColumns = {
        @JoinColumn(name = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "courseid")
    })
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public boolean hasCourse(Course course) {
        for (Course studentCourse : getCourses()) {
            if (studentCourse.getCourseid() == course.getCourseid()) {
                return true;
            }
        }
        return false;
    }
}
