package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.repository.CrudRepository;

import gct.it.computerlabmonitoring.entities.Submission;

public interface SubmissionRepo extends CrudRepository<Submission, Integer>{
    
}
