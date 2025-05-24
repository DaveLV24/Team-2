package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


//RunWith - tells to JUnit to run this class as Cucumber test
@RunWith(Cucumber.class)
// CucumberOptions - provides important information.
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-html-report.html"
        },
        // feature word - provides location of feature file
        features = "src/test/resources",
        // glue word = provides path to steps definitions class
        glue = {"stepDefinitions", "hooks"}
//      ,tags = "(@smoke or @regression) and not (@bug or @wip)"
        ,monochrome = true
// "@regression" - will run only tests with this tag
// "@regression and @sanity" - run test which have tags as @regression and @sanity
// "@regression and not @sanity" - run test which have tags as @regression and NOT @sanity
// "@regression or @sanity"  - run test or @regression or @sanity

)

public class CucumberRunnerTest {
}

