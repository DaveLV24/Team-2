//package stepDefinitions;
//
//import hooks.Hooks;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.cucumber.java.en.Then;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.junit.Assert;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class PracticeFormStepsSAMPLE {
//
//    private WebDriver driver;
//
//    public PracticeFormStepsSAMPLE() {
//        this.driver = Hooks.driver;
//    }
//
//    @Given("I navigate to the basic HTML form test page")
//    public void i_navigate_to_the_basic_html_form_test_page() {
//        driver.get("https://testpages.eviltester.com/styled/basic-html-form-test.html");
//    }
//
//    @When("I fill username with {string}")
//    public void i_fill_username_with(String username) {
//        WebElement usernameField = driver.findElement(By.name("username"));
//        usernameField.clear();
//        usernameField.sendKeys(username);
//    }
//
//    @When("I fill comments with {string}")
//    public void i_fill_comments_with(String comments) {
//        WebElement commentsField = driver.findElement(By.name("comments"));
//        commentsField.clear();
//        commentsField.sendKeys(comments);
//    }
//
//    @When("I check the {string} checkbox")
//    public void i_check_the_checkbox(String checkboxValue) {
//        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox'][value='" + checkboxValue + "']"));
//        if (!checkbox.isSelected()) {
//            checkbox.click();
//        }
//    }
//
//    @Then("I click submit button")
//    public void i_click_submit_button() {
//        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
//        submitButton.click();
//    }
//
//    @Then("I should see {string} in results form")
//    public void i_should_see_username_in_results(String username) {
//        // Wait for results section to become visible
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-results")));
//
//        String pageText = driver.getPageSource();
//        Assert.assertTrue("Username not found in results", pageText.contains(username));
//    }
//}