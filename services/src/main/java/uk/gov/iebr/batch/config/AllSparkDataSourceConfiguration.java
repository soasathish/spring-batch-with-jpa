package uk.gov.iebr.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"uk.gov.iebr.batch.repository"}, entityManagerFactoryRef = "allsparkEntityMF" , transactionManagerRef = "defaultTm")
public class AllSparkDataSourceConfiguration {
	
}
