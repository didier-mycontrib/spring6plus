package tp.mySpringBatch.model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "person") //just for genrate XML file
//implements Serializable just for serialize the execution context (if needed)
public class Person implements Serializable{

    String firstName;
    String lastName;
    Integer age;
    Boolean active;
}