package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

@Entity
public class Experiment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer expId;
    private String expNo;
    private String title;
    private String description;

    @OneToMany(mappedBy = "exp")
    private List<Submission> submissions;
    // test ip
    // due date

    public Experiment() {}

    public Integer getExpId() {
        return expId;
    }
    public void setExpId(Integer expId) {
        this.expId = expId;
    }
    public String getExpNo() {
        return expNo;
    }
    public void setExpNo(String expNo) {
        this.expNo = expNo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}
