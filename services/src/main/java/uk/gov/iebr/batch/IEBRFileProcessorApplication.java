package uk.gov.iebr.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import uk.gov.iebr.batch.config.DataSourceConfiguration;
import uk.gov.iebr.batch.repository.PersonRepository;

@SpringBootApplication
public class IEBRFileProcessorApplication {

	public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);

        PersonRepository repository = context.getBean(PersonRepository.class);
		SpringApplication.run(IEBRFileProcessorApplication.class, args);
	}
}
