package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Getter @Setter @NoArgsConstructor @ToString
public class Experiment {
    @Id
    private Integer expId;
    private String expNo;
    private String title;
    private String description;
    // test ip
    // due date
}
