package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gct.it.computerlabmonitoring.entities.Course;

import java.util.List;

@Repository
public interface CourseRepo extends CrudRepository<Course, String>{
    List<Course> findBySemester(String semester);
}
