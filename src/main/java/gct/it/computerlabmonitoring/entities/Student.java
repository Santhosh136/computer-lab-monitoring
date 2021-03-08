package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

@Entity 
public class Student {
    @Id
    private String regNo;
    private String name;
    private String department;
    private String emailId;

    @OneToMany(mappedBy = "student")
    private List<Submission> submissions;

    public Student() {}

    public Student(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public List<Submission> getSubmissions() {
        return submissions;
    }
    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

}
