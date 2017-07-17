package uk.gov.iebr.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.gov.iebr.batch.model.Person;


/**
 * @author Sathish.Natarajan
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


}


