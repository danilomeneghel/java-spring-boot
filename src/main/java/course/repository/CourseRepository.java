package course.repository;

import course.entity.Course;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findByName(String name);
    List<Course> findAllByOrderByNameAsc();
    
}
