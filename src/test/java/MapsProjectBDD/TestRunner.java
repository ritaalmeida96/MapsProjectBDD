package MapsProjectBDD;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = "MapsProjectBDD.steps",
    plugin = { "pretty", "html:target/cucumber/report.html"},
    monochrome = true,
    tags = "@tag"
)

public class TestRunner {

}
