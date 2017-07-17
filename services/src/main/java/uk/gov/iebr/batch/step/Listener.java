package uk.gov.iebr.batch.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import uk.gov.iebr.batch.dao.PersonDao;
import uk.gov.iebr.batch.model.Person;

public class Listener extends JobExecutionListenerSupport {
	private static final Logger log = LoggerFactory.getLogger(Listener.class);

	private final PersonDao PersonDao;

	public Listener(PersonDao PersonDao) {
		this.PersonDao = PersonDao;
	}
/*
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("Finish Job! Check the results");

			List<Person> Persons = PersonDao.loadAllPersons();

			for (Person Person : Persons) {
				log.info("Found <" + Person + "> in the database.");
			}
		}
	}
	*/
}
