package gct.it.computerlabmonitoring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import gct.it.computerlabmonitoring.entities.Student;

@Service
public class StudentService {
    List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return this.students;
    }
    
    public void save(Student student) {
        this.students.add(student);
    }

    public Student findById(String regNo) {
        return this.students.stream()
        .filter(s -> s.getRegNo().equals(regNo))
        .findFirst()
        .get();
    }
}
