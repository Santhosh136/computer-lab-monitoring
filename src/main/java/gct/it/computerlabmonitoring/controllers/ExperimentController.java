package gct.it.computerlabmonitoring.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gct.it.computerlabmonitoring.entities.Course;
import gct.it.computerlabmonitoring.entities.Experiment;
import gct.it.computerlabmonitoring.repositories.CourseRepo;
import gct.it.computerlabmonitoring.repositories.ExperimentRepo;

@Controller @RequestMapping("/experiments")
public class ExperimentController {
    @Autowired
    private ExperimentRepo expRepo;

    @Autowired
    private CourseRepo courseRepo;

    @GetMapping
    public String listExp(Model model) {
        model.addAttribute("exps", expRepo.findAll());
        return "exp-list";
    }

    @GetMapping("/new")
    public String newExp(@RequestParam("id") String courseCode,Model model) throws Exception {
        model.addAttribute("courseCode", courseCode);
        model.addAttribute("exp", new Experiment());

        return "exp-new";
    }

    @PostMapping("/save")
    public String saveExp(@RequestParam("courseCode") String code, Experiment exp) {
        exp.setCourse(courseRepo.findById(code).get());
        expRepo.save(exp);
        return "redirect:/experiments";
    }
}
