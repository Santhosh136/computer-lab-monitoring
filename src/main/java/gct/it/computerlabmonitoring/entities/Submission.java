package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int submissionId;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Experiment exp;

    @OneToOne
    private ExpDocument doc;

    private int marks;

    public Submission() {}

    //getters and setters

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
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Experiment getExp() {
        return exp;
    }
    public void setExp(Experiment exp) {
        this.exp = exp;
    }
    public ExpDocument getDoc() {
        return doc;
    }
    public void setDoc(ExpDocument doc) {
        this.doc = doc;
    }  
    
    
}
