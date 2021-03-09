package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gct.it.computerlabmonitoring.entities.Course;

@Repository
public interface CourseRepo extends CrudRepository<Course, String>{
    
}
