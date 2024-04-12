package tp.mySpringBatch.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name = "employee") //just for read/generate XML file wkith jaxb2 marshaller
public class Employee extends Person {
	String function;
	Double salary;
}
