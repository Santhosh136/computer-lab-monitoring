package gct.it.computerlabmonitoring.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gct.it.computerlabmonitoring.entities.Student;
import gct.it.computerlabmonitoring.repositories.ExpDocumentRepo;
import gct.it.computerlabmonitoring.repositories.StudentRepo;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ExpDocumentRepo docRepo;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "student/student-list";
    }

    @GetMapping("/submissions")
    public String listExp(@RequestParam(value = "id", required = false) String courseCode, Model model, Principal principal){
        String regno = principal.getName();
        model.addAttribute("docs", docRepo.findDocsByRegNo(regno));
        return "student/student-exp-list";
    }

    @PostMapping("/save")
    public String save(Principal principal, Student student) {
        student.setRegNo(principal.getName());
        studentRepo.save(student);
        return "redirect:/profile";
    }
}