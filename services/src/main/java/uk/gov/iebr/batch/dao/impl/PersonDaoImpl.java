package uk.gov.iebr.batch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import uk.gov.iebr.batch.config.DataSourceConfiguration;
import uk.gov.iebr.batch.dao.PersonDao;
import uk.gov.iebr.batch.model.Person;
import uk.gov.iebr.batch.model.Person.BANDS;
import uk.gov.iebr.batch.model.Person.HarmCategory;
import uk.gov.iebr.batch.model.Person.KOWCategory;
import uk.gov.iebr.batch.model.Person.RemovabilityCategory;
import uk.gov.iebr.batch.repository.PersonRepository;


public class PersonDaoImpl  implements PersonDao {

	
	
	@Autowired
	DataSourceConfiguration dataSource;

	@Autowired
	PersonRepository personrepo;

	@Override
	public void insert(List<? extends Person> Persons) {
		personrepo.save(Persons);
			}
			
		
	}


