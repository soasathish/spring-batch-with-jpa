package uk.gov.iebr.batch.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import uk.gov.iebr.batch.dao.PersonDao;
import uk.gov.iebr.batch.model.Person;

public class Writer implements ItemWriter<Person> {

	private final PersonDao PersonDao;

	public Writer(PersonDao PersonDao) {
		this.PersonDao = PersonDao;
	}

	@Override
	public void write(List<? extends Person> Persons) throws Exception {
		PersonDao.insert(Persons);
	}

}
