package tp.mySpringBatch.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tp.mySpringBatch.model.Person;

@Component
@StepScope
public class SimpleUppercasePersonProcessor implements ItemProcessor<Person,Person>{
	
	@Value("#{jobParameters['enableUpperCase']}") 
	private Boolean enableUpperCase=true;

	@Override
	public Person process(Person pers) throws Exception {
		String lastName = enableUpperCase?pers.getLastName().toUpperCase():pers.getLastName();
		Person person=new Person(pers.getFirstName(),lastName,pers.getAge(),pers.getActive());
		return person;
	}
}
