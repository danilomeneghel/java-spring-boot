package course.service;

import course.entity.Student;
import course.repository.StudentRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl {

    @Autowired
    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    public Student findStudentById(Long id) {
        return repository.findById(id).orElse(new Student());
    }

    public List<Student> findAllByOrderByFirstNameAsc() {
        return repository.findAllByOrderByFirstNameAsc();
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public void updateStudent(Student student) {
        repository.save(student);
    }

    public void deleteStudentById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllStudents() {
        repository.deleteAll();
    }

    public List<Student> findAllStudents() {
        return (List<Student>) repository.findAll();
    }

}
