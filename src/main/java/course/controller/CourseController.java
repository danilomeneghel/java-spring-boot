package course.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import course.entity.Course;
import course.repository.CourseRepository;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository repository;

    @RequestMapping("/courses")
    public String index(Model model) {
        List<Course> courses = (List<Course>) repository.findAll();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @RequestMapping(value = "addCourse")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }

    @RequestMapping(value = "/editCourse/{id}")
    public String editCourse(@PathVariable("id") Long courseId, Model model) {
        model.addAttribute("course", repository.findById(courseId));
        return "editCourse";
    }

    @RequestMapping(value = "saveCourse", method = RequestMethod.POST)
    public String saveCourse(Course course) {
        repository.save(course);
        return "redirect:/courses";
    }

    @RequestMapping(value = "/deleteCourse/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") Long courseId, Model model) {
        repository.deleteById(courseId);
        return "redirect:/courses";
    }

}
