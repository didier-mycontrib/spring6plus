package tp.mySpringBatch.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "person")
public class Person {

    String firstName;
    String lastName;
    Integer age;
    Boolean active;
}