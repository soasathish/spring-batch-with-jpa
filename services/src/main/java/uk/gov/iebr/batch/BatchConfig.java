package uk.gov.iebr.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.gov.iebr.batch.config.DataSourceConfiguration;
import uk.gov.iebr.batch.dao.PersonDao;
import uk.gov.iebr.batch.model.Person;
import uk.gov.iebr.batch.step.Processor;
import uk.gov.iebr.batch.step.Reader;
import uk.gov.iebr.batch.step.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	private static final Logger log = LoggerFactory.getLogger(BatchConfig.class);
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public PersonDao PersonDao;

	@Autowired
	public DataSourceConfiguration dataSourceConfiguration;
	
	@Bean
	public Job job() {
		long startTime = System.currentTimeMillis();
		log.info("START OF BATCH ========================================================================" +startTime);
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer())
				//.listener(new Listener(PersonDao))
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(10)
				.reader(Reader.reader("tram-data.csv"))
				.processor(new Processor()).writer(new Writer(PersonDao)).build();
	}
}