package uk.gov.iebr.drools.rule.template;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import uk.gov.iebr.batch.model.Person;
import uk.gov.iebr.batch.model.Person.HarmCategory;
import uk.gov.iebr.batch.util.PersonBuilder;

public class BandingRuleTest extends BaseTest {

    protected final String ksessionName = "bandingRuleKsession";
   
    @Ignore
    @Test
    public void testNoLoop() {
    	

     //   KieSession ksession = createSession(ksessionName);

     // Bootstrapping a Rule Engine Session
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession =  kContainer.newKieSession("ksession-rule");
        
       Person person1 = new PersonBuilder()
                .withId(1L)
                .withHarmScore(23)
                .withFirstName("JOE")
               .withHarmCategory(HarmCategory.LOW)
                .build();
        
        Person person2 = new PersonBuilder()
                .withId(2L)
                .withFirstName("ROCK")
                .withHarmScore(8)
                .withHarmCategory(HarmCategory.HIGH)
                .build();
        
        Person person3 = new PersonBuilder()
                .withId(3L)
                .withHarmScore(11)
                .withHarmCategory(HarmCategory.LOW)
                .build();
        
        Person person4 = new PersonBuilder()
                .withId(4L)
                .withHarmScore(121)
                .withHarmCategory(HarmCategory.HIGH)
                .build();
        
        ksession.insert(person1);
        ksession.insert(person2);
        ksession.insert(person3);
        ksession.insert(person4);
        ksession.fireAllRules();

        assertThat(person1.getBands(), is("OPT1"));
        
        
        
       
    }
    
}