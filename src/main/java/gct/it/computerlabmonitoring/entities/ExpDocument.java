package gct.it.computerlabmonitoring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ExpDocument {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fileName;
    private Long size;
    
    @Column(columnDefinition = "mediumblob")
    private byte[] content;
    
    @OneToOne(mappedBy = "doc")
    private Submission submission;
    private int marks;

    public ExpDocument() {}

    //getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] bs) {
        this.content = bs;
    }  

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    public Submission getSubmission() {
        return submission;
    }
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}