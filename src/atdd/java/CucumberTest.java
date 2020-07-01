import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","pretty:target/cucumber-html-report.html"},
        features = {"src/atdd/resources/features/test.feature"},
        tags = {"@integrationTest"})


public class CucumberTest {
}
