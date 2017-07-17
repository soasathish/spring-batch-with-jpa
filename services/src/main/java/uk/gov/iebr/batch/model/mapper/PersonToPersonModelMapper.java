package uk.gov.iebr.batch.model.mapper;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import uk.gov.iebr.batch.model.input.PersonDto;
import uk.gov.iebr.batch.model.Person;


/**
 * @author Sathish.Natarajan
 *
 */
@Component
public class PersonToPersonModelMapper {
			
	
		static Logger logger = LoggerFactory.getLogger(Person.class);

	
		public static List<Person> convertToPersonModel( List<? extends PersonDto>input) {
			
			List<Person> personList = new ArrayList<>();

			for (PersonDto source : input) {
				
				logger.debug("Converting PersonDto to Person" + source.toString());

				Person person = new Person();
				person.setId(source.getId());	
				person.setHarmScore(source.getHarmScore());
				person.setKowScore(source.getKowScore());
				person.setRemovabilityScore(source.getRemovabilityScore());
				person.setFirstName(source.getFirstName());
				person.setLastName(source.getLastName());
			//	person.setHarmCategory(source.getHarmCategory().name());
				//person.setKowCategory(source.getKowCategory().name());
				//person.setRemovabilityCategory(source.getRemovabilityCategory().name());
				//person.setBand(source.getBands().name());
				logger.debug("Converted cdr " + person);
				personList.add(person);
			}
			
			

			return personList;
			
		}
	
	
}
