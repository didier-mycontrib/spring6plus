package tp.mySpringBatch.processor;

import org.springframework.batch.item.ItemProcessor;

import tp.mySpringBatch.model.Person;

public class SimpleUppercasePersonProcessor implements ItemProcessor<Person,Person>{
	
	public static SimpleUppercasePersonProcessor INSTANCE = new SimpleUppercasePersonProcessor();

	@Override
	public Person process(Person pers) throws Exception {
		Person person=new Person(pers.getFirstName(),pers.getLastName().toUpperCase(),pers.getAge(),pers.getActive());
		return person;
	}

}
