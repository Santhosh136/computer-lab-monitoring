package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gct.it.computerlabmonitoring.entities.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
    
}
