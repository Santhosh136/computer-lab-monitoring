package gct.it.computerlabmonitoring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import gct.it.computerlabmonitoring.entities.Experiment;

@Service
public class ExperimentService {
    List<Experiment> experiments = new ArrayList<>();

    public List<Experiment> findAll() {
        return this.experiments;
    }

    public void save(Experiment experiment) {
        this.experiments.add(experiment);
    }

    public Experiment findById(String expNo) {
        return this.experiments.stream()
        .filter(e -> e.getExpNo().equals(expNo))
        .findFirst()
        .get();
    }
}
