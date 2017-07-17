/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.gov.iebr.batch.util;

import uk.gov.iebr.batch.model.Person;

/**
 *
 * @author sathish.Natarajan
 */
public class PersonBuilder {
    
    private final Person instance;
    private static Long personIdGenerator = 0L;
    public PersonBuilder() {
        instance = new Person();
        instance.setId(personIdGenerator++);
    }
    
    public PersonBuilder withId(Long id){
        instance.setId(id);
        return this;
    }
    
    public PersonBuilder withFirstName(String firstName){
        instance.setFirstName(firstName);
        return this;
    }
    
    public PersonBuilder withLastName(String lastName){
        instance.setLastName(lastName);
        return this;
    }
    public PersonBuilder withHarmCategory(Person.HarmCategory category){
        instance.setHarmCategory(category);
        return this;
    }
    
    public PersonBuilder withHarmScore(int harmScore){
        instance.setHarmScore(harmScore);
        return this;
    }
    
    public PersonBuilder withRemovabilityScore(int removabilityScore){
        instance.setRemovabilityScore(removabilityScore);
        return this;
    }
    
    public PersonBuilder withRemovabilityCategory(Person.RemovabilityCategory category){
        instance.setRemovabilityCategory(category);
        return this;
    }
    
    public PersonBuilder withKOWScore(int kowScore){
        instance.setKowScore(kowScore);
        return this;
    }
    
    public PersonBuilder withKOWCategory(Person.KOWCategory category){
        instance.setKowCategory(category);
        return this;
    }
    
    public Person build(){
        return instance;
    }

    public PersonBuilder end() {
        return this;
    }
    
}
