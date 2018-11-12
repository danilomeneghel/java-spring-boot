package course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import course.entity.Student;
import course.entity.Course;
import course.service.StudentServiceImpl;
import course.service.CourseServiceImpl;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping("students")
    public String index(Model model) {
        List<Student> students = (List<Student>) studentService.findAllByOrderByFirstNameAsc();
        model.addAttribute("students", students);
        return "students";
    }

    @RequestMapping(value = "student/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Add Student");
        return "studentForm";
    }

    @RequestMapping(value = "student/edit/{id}")
    public String editStudent(@PathVariable("id") Long studentId, Model model) {
        model.addAttribute("student", studentService.findStudentById(studentId));
        model.addAttribute("title", "Edit Student");
        return "studentForm";
    }

    @RequestMapping("student/{id}")
    public String showStudent(@PathVariable("id") Long studentId, Model model) {
        model.addAttribute("student", studentService.findStudentById(studentId));
        model.addAttribute("title", "Show Student");
        return "studentShow";
    }

    @RequestMapping(value = "saveStudent", method = RequestMethod.POST)
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, @RequestParam("id") Long studentId, Model model) {
        if (!bindingResult.hasErrors()) {
            studentService.saveStudent(student);
        } else {
            String title = (studentId == null) ? "Add Student" : "Edit Student";
            model.addAttribute("title", title);
            return "studentForm";
        }
        return "redirect:/students";
    }

    @RequestMapping(value = "student/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
        studentService.deleteStudentById(studentId);
        return "redirect:/students";
    }

    @RequestMapping(value = "addStudentCourse/{id}", method = RequestMethod.GET)
    public String addStudentCourse(@PathVariable("id") Long studentId, Model model) {
        model.addAttribute("student", studentService.findStudentById(studentId));
        model.addAttribute("courses", courseService.findAllCourses());
        return "addStudentCourse";
    }

    @RequestMapping(value = "student/{id}/courses", method = RequestMethod.GET)
    public String studentsAddCourse(@RequestParam(value = "action", required = true) String action, @PathVariable Long id, @RequestParam Long courseId, Model model) {
        Student student = studentService.findStudentById(id);
        Course course = courseService.findCourseById(courseId);

        if (!student.hasCourse(course)) {
            student.getCourses().add(course);
        }
        studentService.saveStudent(student);
        model.addAttribute("student", studentService.findStudentById(id));
        model.addAttribute("courses", courseService.findAllCourses());
        return "redirect:/students";
    }

    @RequestMapping(value = "getstudents", method = RequestMethod.GET)
    public @ResponseBody
    List<Student> getStudents() {
        return (List<Student>) studentService.findAllStudents();
    }
}
