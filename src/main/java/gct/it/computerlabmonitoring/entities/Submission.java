package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Submission {
    @Id
    private int submissionId;
    // private Student student;
    // private Experiment experiment;
    private int marks;
    public Submission() {}
    public int getSubmissionId() {
        return submissionId;
    }
    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }    
}
