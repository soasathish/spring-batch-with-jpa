package uk.gov.iebr.batch.step;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import uk.gov.iebr.batch.model.Person;

public class Reader {
	public static FlatFileItemReader<Person> reader(String path) {
		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
		reader.setLinesToSkip(1);
		reader.setResource(new ClassPathResource(path));
		reader.setLineMapper(new DefaultLineMapper<Person>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "id", "firstName", "lastName" ,"harmScore", "removabilityScore", "kowScore"});
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				});
			}
		});
		return reader;
	}
}
