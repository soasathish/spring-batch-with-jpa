package uk.gov.iebr.batch.dao;

import java.util.List;

import uk.gov.iebr.batch.model.Person;

public interface PersonDao {
	public void insert(List<? extends Person> Persons);
	//List<Person> loadAllPersons();
}
