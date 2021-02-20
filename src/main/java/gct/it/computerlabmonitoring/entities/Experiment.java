package gct.it.computerlabmonitoring.entities;

// @Entity 
public class Experiment {
    private String expNo;
    private String title;
    private String description;
    // test ip
    // due date

    public Experiment() {}

    public Experiment(String expNo, String title, String description) {
        this.expNo = expNo;
        this.title = title;
        this.description = description;
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
