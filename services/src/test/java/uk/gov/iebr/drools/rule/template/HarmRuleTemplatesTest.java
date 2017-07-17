/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.gov.iebr.drools.rule.template;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.drools.template.jdbc.ResultSetGenerator;
import org.junit.Test;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import uk.gov.iebr.batch.model.Person;
import uk.gov.iebr.batch.model.Person.HarmCategory;
import uk.gov.iebr.batch.util.PersonBuilder;

/**
 *
 */
public class HarmRuleTemplatesTest extends BaseTest{
    
   
	   private static InputStream getRulesStream() throws FileNotFoundException {
	      	  return new FileInputStream("/Users/sathishkumarnatarajan/Projects/IEBR/iebr-file-processor/Function/services/bin/template-dtable/harm-score-classification.drt");
	        }
    
    /**
     * Tests harm-score-classification.drt.drt template by manually creating
     * the corresponding DRL using an embedded database as the data source.
     */
    @Test
    public void testSimpleTemplateWithDatabase() throws Exception{
        
      
        
     
        // setup the HSQL database with our rules.
      //  Class.forName("org.hsqldb.jdbcDriver");
        //Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:drools-templates", "sa", "");
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                     "jdbc:postgresql://localhost:5432/drools_via_db",
                     "postgres", "asdf#");
        try {
        	executeInDB("DROP TABLE HarmScoreRules ", conn);
            executeInDB("CREATE TABLE HarmScoreRules ( id INTEGER , minScore INTEGER, maxScore INTEGER, previousCategory VARCHAR(256), newCategory VARCHAR(256) )", conn);

            executeInDB("INSERT INTO HarmScoreRules VALUES (1, 0, 19, 'NA', 'LOW')", conn);
            executeInDB("INSERT INTO HarmScoreRules VALUES (2, 20, 99, 'NA', 'MEDIUM')", conn);
            executeInDB("INSERT INTO HarmScoreRules VALUES (3, 100, 1000, 'NA', 'HIGH')", conn);
           
        } catch (SQLException e) {
            throw new IllegalStateException("Could not initialize in memory database", e);
        }

        Statement sta = conn.createStatement();
        ResultSet rs = sta.executeQuery("SELECT minScore, maxScore, previousCategory, newCategory " +
                                        " FROM HarmScoreRules");

        final ResultSetGenerator converter = new ResultSetGenerator();
        final String drl = converter.compile(rs, getRulesStream());
        
        System.out.println(drl);
        
        KieSession ksession = this.createKieSessionFromDRL(drl);
        
        this.doTest(ksession);
    }
    
    
    private void doTest(KieSession ksession){
        Person person1 = new PersonBuilder()
                .withId(1L)
                .withHarmScore(23)
           //     .withHarmCategory(HarmCategory.NA)
                .build();
        
        Person person2 = new PersonBuilder()
                .withId(2L)
                .withHarmScore(8)
         //       .withHarmCategory(HarmCategory.NA)
                .build();
        
        Person person3 = new PersonBuilder()
                .withId(3L)
                .withHarmScore(11)
            //    .withHarmCategory(HarmCategory.NA)
                .build();
        
        Person person4 = new PersonBuilder()
                .withId(4L)
                .withHarmScore(121)
          //    .withHarmCategory(HarmCategory.NA)
                .build();
        
        ksession.insert(person1);
        ksession.insert(person2);
        ksession.insert(person3);
        ksession.insert(person4);
        
        ksession.fireAllRules();
        
        assertThat(person1.getHarmCategory(), is(Person.HarmCategory.MEDIUM));
        assertThat(person2.getHarmCategory(), is(Person.HarmCategory.LOW));
        assertThat(person3.getHarmCategory(), is(Person.HarmCategory.LOW));
        assertThat(person4.getHarmCategory(), is(Person.HarmCategory.HIGH));
    }
    
    /**
     * Executes an update statement into a database.
     * @param expression the SQL expression to be executed.
     * @param conn the connection the the database where the statement will be
     * executed.
     * @throws SQLException 
     */
    private void executeInDB(String expression, Connection conn) throws SQLException {
        Statement st;
        st = conn.createStatement();
        int i = st.executeUpdate(expression);
        if (i == -1) {
            System.out.println("db error : " + expression);
        }

        st.close();
    }

    
    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
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
