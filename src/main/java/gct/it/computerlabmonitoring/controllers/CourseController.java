package gct.it.computerlabmonitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gct.it.computerlabmonitoring.entities.Course;
import gct.it.computerlabmonitoring.repositories.CourseRepo;

@Controller @RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @GetMapping
    public String allCourses(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "course/course-list";
    }

    @GetMapping("/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/course-new";
    }

    @PostMapping("/save")
    public String saveCourse(Course course) {
        courseRepo.save(course);
        return "redirect:/courses";
    }
}
