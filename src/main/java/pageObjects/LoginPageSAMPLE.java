package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageSAMPLE {
    WebDriver driver;

    // Locators
    By usernameInput = By.id("username");
    By passwordInput = By.name("password");
    By loginButton = By.cssSelector("button[type='submit']");
    By successMessage = By.cssSelector(".flash.success");
    By errorMessage = By.xpath("//div[@data-alert=''][contains(@class,'flash')]");

    public LoginPageSAMPLE(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
