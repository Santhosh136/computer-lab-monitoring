package gct.it.computerlabmonitoring.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gct.it.computerlabmonitoring.entities.Student;
import gct.it.computerlabmonitoring.repositories.StudentRepo;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "student-list";
    }

    // @GetMapping("/new")
    // public String newForm(Model model) {
    //     model.addAttribute("student", new Student());
    //     return "student-new";
    // }

    @PostMapping("/save")
    public String save(Principal principal, Student student) {
        student.setRegNo(principal.getName());
        studentRepo.save(student);
        return "redirect:/students/profile";
    }

    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model) {
        Student student = studentRepo.findById(principal.getName()).get();
        model.addAttribute("student", student);
        return "student-profile";
    }
}