package gct.it.computerlabmonitoring.entities;

// @Entity
public class Submission {

    private int submissionId;
    private Student student;
    private Experiment experiment;
    private int marks;

    public Submission() {}

    public Student getStudent() {
        return student;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    
}
