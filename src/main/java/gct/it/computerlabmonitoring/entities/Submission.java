package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Submission {
    @Id
    private int submissionId;
    // private Student student;
    // private Experiment experiment;
    private int marks;    
}
