package course.repository;

import course.entity.Student;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByFirstName(String firstName);
    List<Student> findAllByOrderByFirstNameAsc();

}
