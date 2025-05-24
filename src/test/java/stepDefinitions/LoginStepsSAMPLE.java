package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPageSAMPLE;

public class LoginStepsSAMPLE {

    private WebDriver driver;
    private LoginPageSAMPLE loginPageSAMPLE;
    public LoginStepsSAMPLE() {
        this.driver = Hooks.driver;
        loginPageSAMPLE = new LoginPageSAMPLE(driver);
    }
    private static final String SITE = "https://the-internet.herokuapp.com/login";

    @Given("the user is on the login page")
    public void userOnLoginPage() {
        driver.get(SITE);
        loginPageSAMPLE = new LoginPageSAMPLE(driver);
    }

    @When("the user logs in with username {string} and password {string}")
    public void loginWithCredentials(String username, String password) {
        loginPageSAMPLE.enterUsername(username);
        loginPageSAMPLE.enterPassword(password);
        loginPageSAMPLE.clickLogin();
    }

    @Then("the user should see a success message")
    public void verifyLoginSuccess() {
        String message = loginPageSAMPLE.getSuccessMessage();
        Assert.assertTrue(message.contains("You logged into a secure area!"));
        System.out.println("Msg is " + message);

    }

    @Then("the user should see an error message")
    public void verifyLoginFailure() {
        String message = loginPageSAMPLE.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!") || message.contains("Your password is invalid!"));
    }
}
