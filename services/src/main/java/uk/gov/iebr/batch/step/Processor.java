package uk.gov.iebr.batch.step;

import java.util.List;
import java.util.Random;

import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import uk.gov.iebr.batch.model.Person;
import uk.gov.iebr.batch.model.Person.HarmCategory;

import org.drools.template.jdbc.ResultSetGenerator;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;
import org.kie.api.runtime.KieSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Processor  implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(Processor.class);

	@Override
	public Person process(Person Person) throws Exception {
		Random r = new Random();
		
		final String firstName = Person.getFirstName().toUpperCase();
		final String lastName = Person.getLastName().toUpperCase();
		final int harmScore=Person.getHarmScore();
		final int removabilityScore=Person.getRemovabilityScore();
		final int kowScore= Person.getKowScore();
		//final HarmCategory harmcategory= Person.getHarmCategory().NA;
		
		 Person personObj = new Person(r.nextLong(), firstName, lastName, harmScore, removabilityScore, kowScore );
       //  personObj.setHarmCategory(Person.getHarmCategory().NA);
		log.info("Converting (" + Person + ") into (" + personObj + ")");
		
		 KieServices ks = KieServices.Factory.get();

		   KieContainer kc = ks.getKieClasspathContainer();
		
		//KieSession ksession= kc.newKieSession();
	    // ksession = applyHarmScoreRule(ksession);
		   
		//   KieBase kBase1 = kc.getKieBase("KBase1");
		//  KieSession ksession = kc.newKieSession("KSession2_1");
		   
		   
		   KieSession ksession3 = kc.newKieSession("kession-rule3");
		   ksession3=applyKOWRule(ksession3);
		   ksession3.insert(personObj);
		   ksession3.fireAllRules();
			 
		   
		   KieSession ksession2 = kc.newKieSession("kession-rule2");
		   ksession2=applyRemovabilityRule(ksession2);
		   ksession2.insert(personObj);
		   ksession2.fireAllRules();
			 
			 
		   KieSession ksession = kc.newKieSession("kession-rule1");
		   ksession = applyHarmScoreRule(ksession);
		   
		 
	   
		   
	     /*	// Bootstrapping a Rule Engine Session
	           KieServices kservice = KieServices.Factory.get();
	           KieContainer kContainer = kservice.getKieClasspathContainer();
	           KieSession kSession4 =  kContainer.newKieSession();
		    // KieSession ksession4 = kc.newKieSession("kession-band-rule");
	            kSession4.getAgenda().getAgendaGroup("withoutNoLoop").setFocus();
	        // kSession4 = applyBandScoreRule(kSession4);
			
	        kSession4.insert(personObj);
	        kSession4.fireAllRules();*/
		   
		   KieSession ksession4 = kc.newKieSession("complex");
		   ksession4 = applyBandTemplateScoreRule(ksession);
			
		  ksession.insert(personObj);
		   ksession.fireAllRules();
		  
		   
		//    KieSession ksession4 =  kc.newKieSession("ksession-rule");
		    ksession4.insert(personObj);
		    ksession4.fireAllRules();
		 
		return personObj;
	}
	
	
	   private static InputStream getRulesStream() throws FileNotFoundException {
	      	  return new FileInputStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/src/main/resources/template-dtable/harm-score-classification.drt");
	        }
	   
	   private static InputStream getRemovabilityRulesStream() throws FileNotFoundException {
	      	  return new FileInputStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/src/main/resources/template-dtable/removability-score-classification.drt");
	        }
	   
	   private static InputStream getKOWRulesStream() throws FileNotFoundException {
	      	  return new FileInputStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/src/main/resources/template-dtable/kow-score-classification.drt");
	        }
	
	   private static InputStream getBAndTemplateRulesStream() throws FileNotFoundException {
	      	  return new FileInputStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/src/main/resources/template-dtable/iebr-band-classification.drt");
	        }
	   
	   
	   

	   

		 public KieSession applyBandScoreRule(KieSession ksession) throws Exception{	        	
			  InputStream bandRule = new FileInputStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/src/main/resources/rules/iebr-bands-classification.drl");
		         System.out.println(bandRule); 
		         ksession = this.createKieSessionFromDRL(bandRule.toString());
		        return ksession;
		    }
		 
	
		 
		 
		 public KieSession applyBandTemplateScoreRule(KieSession ksession) throws Exception{	        

		        Class.forName("org.postgresql.Driver");
		        Connection conn = DriverManager.getConnection(
		                     "jdbc:postgresql://localhost:5432/testcsvdb",
		                     "postgres", "asdf#");
		       

		        Statement sta = conn.createStatement();
		        ResultSet rs = sta.executeQuery("SELECT harm, removability,kow, previousCategory, newCategory " +
                         " FROM band_rules" );
		        
		        //ResultSet rs2 = sta.executeQuery("SELECT minScore, maxScore, previousCategory, newCategory " +
		      //           " FROM removability_score_rules");

		        final ResultSetGenerator converter = new ResultSetGenerator();
		        final String drl = converter.compile(rs, getBAndTemplateRulesStream());
		       // final String drl2 = converter.compile(rs, getRemovabilityRulesStream());
		        
		        System.out.println(drl);
		        
		         ksession = this.createKieSessionFromDRL(drl);
		        
		        return ksession;
		    }
	 public KieSession applyHarmScoreRule(KieSession ksession) throws Exception{	        

	        Class.forName("org.postgresql.Driver");
	        Connection conn = DriverManager.getConnection(
	                     "jdbc:postgresql://localhost:5432/testcsvdb",
	                     "postgres", "asdf#");
	       

	        Statement sta = conn.createStatement();
	        ResultSet rs = sta.executeQuery("SELECT minScore, maxScore, previousCategory, newCategory " +
                    " FROM harm_score_rules");
	        
	        //ResultSet rs2 = sta.executeQuery("SELECT minScore, maxScore, previousCategory, newCategory " +
	      //           " FROM removability_score_rules");

	        final ResultSetGenerator converter = new ResultSetGenerator();
	        final String drl = converter.compile(rs, getRulesStream());
	       // final String drl2 = converter.compile(rs, getRemovabilityRulesStream());
	        
	        System.out.println(drl);
	        
	         ksession = this.createKieSessionFromDRL(drl);
	        
	        return ksession;
	    }
	 
	 
	 public KieSession applyRemovabilityRule(KieSession ksession) throws Exception{
	        
	        InputStream template = Processor.class.getResourceAsStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/src/main/resources/template-dtable/removability-score-classification.drt");
	        

	        Class.forName("org.postgresql.Driver");
	        Connection conn = DriverManager.getConnection(
	                     "jdbc:postgresql://localhost:5432/testcsvdb",
	                     "postgres", "asdf#");
	       

	        Statement sta = conn.createStatement();
	        ResultSet rs = sta.executeQuery("SELECT minScore, maxScore, previousCategory, newCategory " +
                 " FROM removability_score_rules");

	        final ResultSetGenerator converter = new ResultSetGenerator();
	        final String drl = converter.compile(rs, getRemovabilityRulesStream());
	        
	        System.out.println(drl);
	        
	         ksession = this.createKieSessionFromDRL(drl);
	        
	        return ksession;
	    }
	    
	    
	
	 
	 public KieSession applyKOWRule(KieSession ksession) throws Exception{
	        
	        InputStream template = Processor.class.getResourceAsStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/src/main/resources/template-dtable/kow-score-classification.drt");
	        

	        Class.forName("org.postgresql.Driver");
	        Connection conn = DriverManager.getConnection(
	                     "jdbc:postgresql://localhost:5432/testcsvdb",
	                     "postgres", "asdf#");
	       

	        Statement sta = conn.createStatement();
	        ResultSet rs = sta.executeQuery("SELECT minScore, maxScore, previousCategory, newCategory " +
                 " FROM kow_score_rules");

	        final ResultSetGenerator converter = new ResultSetGenerator();
	        final String drl = converter.compile(rs, getKOWRulesStream());
	        
	        System.out.println(drl);
	        
	         ksession = this.createKieSessionFromDRL(drl);
	        
	        return ksession;
	    }
	
    private KieSession createKieSessionFromDRL(String drl,String drl2){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        kieHelper.addContent(drl2, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
    
    private KieSession createKieSessionFromDRL(String drl
    		){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
      //  kieHelper.addContent(drl2, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
}
