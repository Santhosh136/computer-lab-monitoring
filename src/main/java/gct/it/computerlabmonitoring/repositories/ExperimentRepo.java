package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.repository.CrudRepository;

import gct.it.computerlabmonitoring.entities.Experiment;

public interface ExperimentRepo extends CrudRepository<Experiment, Integer>{
    
}
