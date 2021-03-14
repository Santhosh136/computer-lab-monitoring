package gct.it.computerlabmonitoring.controllers;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gct.it.computerlabmonitoring.entities.Student;
import gct.it.computerlabmonitoring.entities.User;
import gct.it.computerlabmonitoring.repositories.CourseRepo;
import gct.it.computerlabmonitoring.repositories.StudentRepo;
import gct.it.computerlabmonitoring.repositories.UserRepo;

@Controller
public class HomeController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/signup")
    public String createUser(Model model) {
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
        return "redirect:/signup";
    }

    @GetMapping("/profile") 
    public String showUser(Principal principal, Model model) {
        String regNo = principal.getName();
        Optional<Student> result = studentRepo.findById(regNo);
        if(!result.isPresent()) throw new UsernameNotFoundException("User not found");
        model.addAttribute("student", result.get());
        return "student/student-profile";
    }

    @GetMapping("/dashboard")    
    public String showDashBoard(Principal principal, Model model) {
        String regNo = principal.getName();
        Optional<Student> result = studentRepo.findById(regNo);
        if(!result.isPresent()) throw new UsernameNotFoundException("User not found");
        model.addAttribute("courses", courseRepo.findBySemester(result.get().getSemester()));
        return "student/student-dashboard";
    }
}
