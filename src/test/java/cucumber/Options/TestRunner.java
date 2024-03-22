package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)	
@CucumberOptions(
		features=("src/test/java/features") ,glue= {"stepDefinations"},
		plugin={"pretty", "html:target/cucumber" , "json:target/jsonReports/cucumber-report.json"},
				
		tags=("@addplace or @deleteplace")
				
		)

//to run in command line we have to  use mvn test -Dcucumber.filter.tags="@AddPlace" - single tag

//mvn test verify -Dcucumber.filter.tags="@addplace or @deleteplace"   -- multiple tag

// to run cucumber report thru cmd type mvn test verify

public class TestRunner {


}



