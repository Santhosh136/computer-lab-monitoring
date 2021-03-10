package gct.it.computerlabmonitoring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gct.it.computerlabmonitoring.entities.Course;
import gct.it.computerlabmonitoring.entities.Experiment;


@Repository
public interface ExperimentRepo extends CrudRepository<Experiment, Integer>{
    List<Experiment> findByCourse(Course course);
}
