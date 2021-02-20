package gct.it.computerlabmonitoring.entities;

// @Entity
public class Student {
    private String regNo;
    private String name;
    private String department;
    private String emailId;

    public Student() {}

    public Student(String regNo, String name, String department, String emailId) {
        this.regNo = regNo;
        this.name = name;
        this.department = department;
        this.emailId = emailId;
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
}
