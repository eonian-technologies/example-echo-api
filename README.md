## Example Echo API
A simple HTTP API implemented with Domain-driven Design, Hexagonal Architecture, Spring, and Jersey. Messages sent in the request are echoed back in the response.

Additionally this project showcases the Parent POM's pipelining capabilities. For details, see our companion article <a href="https://medium.com/eonian-technologies/maven-for-pipelining-part-1-8b850d10a7ee" target="_blank">Maven For Pipelining</a>.

## Discrete Pipeline Steps
The plugins that make up these steps are bound to the Maven Lifecycle by the Parent POM. So a command like `mvn install` will execute them by default. But when pipelining, we want to execute a series of smaller, more granular steps, and make judgments on whether or not to proceed based on their output. A lifecycle goal like `mvn install` does too much. If the CI job running the command breaks there are several things that could have gone wrong. Could it not compile? Could it not run tests? Did the tests fail? Which tests failed, unit tests or integration tests? So when setting up the project’s CI pipeline we won’t use a single step that runs a command like mvn install. Instead we’ll create discrete pipeline steps which directly call their corresponding Maven plugins.

### Build
The first step in the pipeline is to build the project. But this is not just compiling the code. We’ll also want to enforce our dependency blacklist, create the `build.properties` file, and do all the things that the Maven Lifecycle does to process resources, class files, and dependencies. We'll do this is by using the verify goal from the Maven Lifecycle and skip all testing.

```
mvn -DskipTests clean verify
```

### Unit Test
Next we will execute the SureFire plugin to run our unit tests. But we also need to execute the JaCoCo plugin which will determine test coverage and generate the coverage report.

```
mvn jacoco:prepare-agent surefire:test jacoco:report
```

### Integration Test
To run integration tests, we’ll spin up a local server and deploy the project. The server is configured to measure test coverage, and the FailSafe plugin is used to run the tests. Tests make calls to the server and then validate its responses. After the tests have run, coverage information is dumped, the server is stopped, and a coverage report is generated. When the integration test server starts, our WAR project should connect to resources (databases and services) in a static development environment.

```
mvn jacoco:prepare-agent-integration cargo:start failsafe:integration-test jacoco:dump cargo:stop jacoco:report-integration
```

### Code Analysis
Now that we have run unit tests and integration tests we can determine the overall test coverage and run static analysis. This is an important step. We'll need to ensure that the build passes the quality gates we’ve set up in our SonarQube server. The gates should include checks on code quality and ensure that the test coverage meets the minimum requirements. 

```
mvn sonar:sonar -Dsonar.host.url=URL -Dsonar.login=TOKEN
```

### Publish Artifacts
At this point we are ready to publish the build artifact. Because we are calling the Deploy plugin outside of the Maven Lifecycle, Maven does not know about the artifact that was previously built. So we’ll need to specify the build artifact, the POM file, the URL to our artifact repository, and the ID of the repo in the `settings.xml` file that contains the repository credentials.

```
mvn deploy:deploy-file -DpomFile=pom.xml -Dfile=target/ARTIFACT -Durl=REPO_URL -DrepositoryId=REPO_ID
``` 
