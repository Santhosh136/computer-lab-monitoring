package gct.it.computerlabmonitoring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gct.it.computerlabmonitoring.entities.Experiment;
import gct.it.computerlabmonitoring.services.ExperimentService;

@Controller
public class ExperimentController {
    @Autowired
    private ExperimentService expService;

    @GetMapping("")
    public String listExp(Model model) {
        model.addAttribute("exps", expService.findAll());
        return "experiments/exp-list";
    }

    @GetMapping("/new")
    public String newExp(Model model) {
        model.addAttribute("exp", new Experiment());
        return "experiments/exp-new";
    }

    @PostMapping("/save")
    public String saveExp(Experiment exp) {
        expService.save(exp);
        return "redirect:/experiments/new";
    }
}
