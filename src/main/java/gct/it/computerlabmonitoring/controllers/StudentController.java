package gct.it.computerlabmonitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gct.it.computerlabmonitoring.entities.Student;
import gct.it.computerlabmonitoring.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public String displayStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "/student-list";
    }

    @GetMapping("/new")
    public String displayStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "/student-form";
    }

    @PostMapping("/save")
    public String saveStudent(Student student) {
        studentService.save(student);
        return "redirect:/students/new";
    }
}
