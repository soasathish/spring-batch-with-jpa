package uk.gov.iebr.batch;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Sathish.Natarajan
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Profile(value = "${spring.profiles.active}")
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IEBRFileProcessorApplication.class);
	}
	


}
