package trade.javiergutierrez.DaypoDatasExtractor.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@Getter
@Setter
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Employee implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private Department department;

    public Employee() {
        super();
    }

    public Employee(int id, String fName, String lName, Department department) {
        super();
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.department = department;
    }

    //Setters and Getters

    @Override
    public String toString() {
        return String.format("Employee [id=%s, firstName=%s, lastName=%s, department=%s]", id, firstName, lastName, department);
    }
}
