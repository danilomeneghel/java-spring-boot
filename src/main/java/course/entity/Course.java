package course.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Course {

    @Id
    private long courseid;

    @Size(min = 3, message = "Course is invalid")
    @Column(name = "coursename")
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public long getCourseid() {
        return courseid;
    }
    
    public void setCourseid(long courseid) {
        this.courseid = courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
