package gct.it.computerlabmonitoring.controllers;

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
    public String listExp(@RequestParam(value = "id", required = false) String code, Model model) {
        if(code != null) {
            Course course = courseRepo.findById(code).get();
            model.addAttribute("exps", expRepo.findByCourse(course));
        }
        else model.addAttribute("exps", expRepo.findAll());
        return "exp-list";
    }

    @GetMapping("/new")
    public String newExp(@RequestParam(value = "id", required = false) String courseCode,Model model) throws Exception {
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
