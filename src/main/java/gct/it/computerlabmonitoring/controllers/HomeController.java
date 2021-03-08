package gct.it.computerlabmonitoring.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gct.it.computerlabmonitoring.entities.Student;
import gct.it.computerlabmonitoring.entities.User;
import gct.it.computerlabmonitoring.repositories.StudentRepo;
import gct.it.computerlabmonitoring.repositories.UserRepo;

@Controller
public class HomeController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/sign-up")
    public String sayHello(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        user.setActive(true);
        user.setRoles("ROLE_USER");
        Student student = new Student(user.getUserName());

        studentRepo.save(student);
        user.setStudent(student);
        
        userRepo.save(user);
        return "redirect:/sign-up";
    }
}
