package course.service;

import course.entity.Course;
import course.repository.CourseRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl {

    @Autowired
    private final CourseRepository repository;

    @Autowired
    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    public Course findCourseById(Long courseid) {
        return repository.findById(courseid).orElse(new Course());
    }

    public List<Course> findAllByOrderByNameAsc() {
        return repository.findAllByOrderByNameAsc();
    }

    public void saveCourse(Course course) {
        repository.save(course);
    }

    public void updateCourse(Course course) {
        repository.save(course);
    }

    public void deleteCourseById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllCourses() {
        repository.deleteAll();
    }

    public List<Course> findAllCourses() {
        return (List<Course>) repository.findAll();
    }

}
