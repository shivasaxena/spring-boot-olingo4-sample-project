## Project: Spring Boot Olingo 4 Sample Project

### Purpose/Goal:
The Spring Boot Olingo 4 Sample Project that demonstrates how to use build OData 4.0 Service using Olingo and Spring boot


### Technology Stack:

Spring Boot 2.7.x
Apache Olingo 4
Java

### Licensing: 
Apache-2.0


### Installation:

- Maven is used as the build system. Please run `mvn install` to setup the project
- To start the application the please execute the main `classSpringBootOlingo4SampleProjectApplication.java`

- The application is configured to be available at the endpoint http://localhost:8080/DemoService.svc/

### Details:

`SpringBootOlingo4SampleProjectApplication.java` is the main class to start the application

`ODataController.java` configures the rest controller and maps the ODataHadeller at the specified endpoint to process the requests

`EDMProvider.java` defines the OData Metadata to define Schema and Entities. 
// TODO: Define metadata for Action, FunctionImports and other functions

`ODataServiceProcessorImpl.java` is the OData Processor where we define how various operations are implemented. Currently  it only implements `EntityCollectionProcessor` interface to provide capability for reading EntityCollection, this can be enhanced to implement other interfaces like `EntityProcessor`, `BatchProcessor`,`ActionEntityProcessor` to provide various other functionalities 

### Contributions:
We welcome contributions from the open-source community. You can contribute by submitting bug reports, feature requests, or code contributions on our GitHub repository.