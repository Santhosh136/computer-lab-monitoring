package gct.it.computerlabmonitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gct.it.computerlabmonitoring.entities.Experiment;
import gct.it.computerlabmonitoring.repositories.ExperimentRepo;

@Controller @RequestMapping("/experiments")
public class ExperimentController {
    @Autowired
    private ExperimentRepo expRepo;

    @GetMapping
    public String listExp(Model model) {
        model.addAttribute("exps", expRepo.findAll());
        return "exp-list";
    }

    @GetMapping("/new")
    public String newExp(Model model) {
        model.addAttribute("exp", new Experiment());
        return "exp-new";
    }

    @PostMapping("/save")
    public String saveExp(Experiment exp) {
        expRepo.save(exp);
        return "redirect:/experiments";
    }
}
