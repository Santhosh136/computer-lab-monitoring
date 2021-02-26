package gct.it.computerlabmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Getter @Setter @ToString @NoArgsConstructor
public class Student {
    @Id
    private String regNo;
    private String name;
    private String department;
    private String emailId;
}
