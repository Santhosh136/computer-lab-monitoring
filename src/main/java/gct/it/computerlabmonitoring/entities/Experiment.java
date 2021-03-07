package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Experiment {
    @Id
    private Integer expId;
    private String expNo;
    private String title;
    private String description;
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


}
