package course.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import course.entity.Course;
import course.repository.CourseRepository;
import course.entity.Student;
import course.repository.StudentRepository;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private CourseRepository crepository;

    @RequestMapping("students")
    public String index(Model model) {
        List<Student> students = (List<Student>) repository.findAllByOrderByFirstNameAsc();
        model.addAttribute("students", students);
        return "students";
    }

    @RequestMapping(value = "addStudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Add Student");
        return "formStudent";
    }

    @RequestMapping(value = "editStudent/{id}")
    public String editStudent(@PathVariable("id") Long studentId, Model model) {
        model.addAttribute("student", repository.findById(studentId));
        model.addAttribute("title", "Edit Student");
        return "formStudent";
    }

    @RequestMapping(value = "saveStudent", method = RequestMethod.POST)
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, @RequestParam(value = "id", required = false) Long studentId) {
        if (!bindingResult.hasErrors()) { 
            repository.save(student);
        } else {
            return "formStudent";
        }
        return "redirect:/students";
    }

    @RequestMapping(value = "deleteStudent/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
        repository.deleteById(studentId);
        return "redirect:/students";
    }

    @RequestMapping(value = "addStudentCourse/{id}", method = RequestMethod.GET)
    public String addCourse(@PathVariable("id") Long studentId, Model model) {
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("student", repository.findById(studentId).get());
        return "addStudentCourse";
    }

    @RequestMapping(value = "student/{id}/courses", method = RequestMethod.GET)
    public String studentsAddCourse(@RequestParam(value = "action", required = true) String action, @PathVariable Long id, @RequestParam Long courseId, Model model) {
        Optional<Course> course = crepository.findById(courseId);
        Optional<Student> student = repository.findById(id);

        if (student.isPresent() && action.equalsIgnoreCase("save")) {
            if (!student.get().hasCourse(course.get())) {
                student.get().getCourses().add(course.get());
            }
            repository.save(student.get());
            model.addAttribute("student", crepository.findById(id));
            model.addAttribute("courses", crepository.findAll());
            return "redirect:/students";
        }

        model.addAttribute("developers", repository.findAll());
        return "redirect:/students";

    }

    @RequestMapping(value = "getstudents", method = RequestMethod.GET)
    public @ResponseBody
    List<Student> getStudents() {
        return (List<Student>) repository.findAll();
    }
}
