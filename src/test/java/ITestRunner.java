import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/PhysicalFileCreation.feature",
        glue = {"stepdefinition"},
        dryRun = false)
public class ITestRunner {
}
