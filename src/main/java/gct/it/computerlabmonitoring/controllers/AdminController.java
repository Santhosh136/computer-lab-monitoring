package gct.it.computerlabmonitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gct.it.computerlabmonitoring.entities.Student;
import gct.it.computerlabmonitoring.entities.User;
import gct.it.computerlabmonitoring.repositories.StudentRepo;
import gct.it.computerlabmonitoring.repositories.UserRepo;

@Controller @RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/signup")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "admin-signup";
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        user.setActive(true);
        user.setRoles("ROLE_ADMIN");
        Student student = new Student(user.getUserName());

        studentRepo.save(student);
        user.setStudent(student);
        
        userRepo.save(user);
        return "redirect:/";
    }
}
