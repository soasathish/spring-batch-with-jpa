Function
Contains the implementation service iebr-file-processor, along with any  services that support the application:



Maven Notes
Must use Maven modules - The Maven POM must make use of modules: each functional area inside its own
Maven artifactID should follow the vertical name with the module name appended 


Example structure
Function/
Function/pom.xml
Function/service-impl/
Function/service-impl/src/
Function/service-impl/src/main/
Function/service-impl/src/main/java/com/uk/gov/iebr/batch
 

The central maven repository Url
   - http://localhost:8081/nexus/content/groups/public


Maven command to the start spring boot  application
   - mvn spring-boot:run