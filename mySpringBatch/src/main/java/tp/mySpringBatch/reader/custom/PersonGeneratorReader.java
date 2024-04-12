package tp.mySpringBatch.reader.custom;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import tp.mySpringBatch.model.Person;

public class PersonGeneratorReader  implements ItemReader<Person>{
	
	private long dataSetSize=20; //ou 10000 ou autre
	private long index=0;
	
	//most frequent lastNames list 
	private List<String> lastNameList = Arrays.asList("Martin" , "Bernard" , "Thomas" , "Petit" , "Robert" , "Richard" , "Dubois" , "Durand" , 
			 "Moreau" , "Laurent" , "Simon" , "Michel" , "Lefebvre" , "Leroy" , "Roux" , "David" , "Bertrand" , "Morel" , "Fournier" , "Girard" ,
			 "Bonnet" , "Dupont" , "Lambert" , "Fontaine" , "Rousseau" , "Vincent" , "Muller" , "Faure" , "Andre" , "Mercier");
	
	//most frequent firstNames list
	private List<String> firstNameList = Arrays.asList("Gabriel" , "Leo" , "Raphael" , "Mael" , "Louis" , "Noah" , "Jules" , "Arthur" , "Adam",
			"Lucas" , "Liam" , "Sacha" , "Isaac" , "Gabin" , "Eden" , "Hugo" , "Mohamed" , "Leon" , "Paul" , "Nathan" ,
			
			"Jade", "Louise" , "Ambre" , "Emma" , "Rose" , "Alice" , "Romy" , "Anna" , "Mia" , "Lou" , "Juila" , "Chloe" , "Alma" , 
			"Agathe" , "Iris" , "Juliette" , "Lea" , "Victoire" , "Nina" , "Zoe" , "Ines" , "Lucie" , "Lola" , "Alix" 
 			);
	
	public PersonGeneratorReader() {
		super();
	}

	public PersonGeneratorReader(long dataSetSize) {
		super();
		this.dataSetSize = dataSetSize;
	}

	private Person generatePerson(){
		index++;
		int nbFirstNames = firstNameList.size();
		int nbLastNames = lastNameList.size();
		if(index<=dataSetSize) {
			double randomCoeff = Math.random();
			int age = (int)((100 * randomCoeff) % 100);
			String firstName = firstNameList.get((int)(nbFirstNames * randomCoeff) % nbFirstNames);
			String lastName = lastNameList.get((int)(nbLastNames * randomCoeff) % nbLastNames);
			return new Person(firstName , lastName ,age  , true );
		}else
			return null;
	}
		
	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return generatePerson();
	}

	public long getDataSetSize() {
		return dataSetSize;
	}

	public void setDataSetSize(long dataSetSize) {
		this.dataSetSize = dataSetSize;
	}

}
