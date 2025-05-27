package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class ShoppingCartQuantityAndTotalSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    public ShoppingCartQuantityAndTotalSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on test product page$")
    public void goToTestProductPage(){
        driver.get("https://demowebshop.tricentis.com/smartphone");
    }

    @Given("^Test item is added to shopping cart$")
    public void productAddedToShoppingCartForTests() throws Exception{
        wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        driver.findElement(By.className("qty-input")).clear();
        driver.findElement(By.className("qty-input")).sendKeys("3");
        driver.findElement(By.className("add-to-cart-button")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("success")));
    }

    @And("^I am on the shopping cart page$")
    public void setDriverToShoppingCartPage(){
        driver.get("https://demowebshop.tricentis.com/cart");
    }

    @When("^I input 0 as new quantity value$")
    public void zeroAsItemQuantityInput(){
        driver.findElement(By.className("qty-input")).clear();
        driver.findElement(By.className("qty-input")).sendKeys("0");
    }

    @And("^I click update shopping cart button$")
    public void updateShoppingCartButtonPress(){
        driver.findElement(By.className("update-cart-button")).click();
    }

    @Then("^Message can be seen: \"Your Shopping Cart is empty!\"$")
    public void checkShoppingCartEmpty(){
        assertTrue(driver.findElement(By.className("order-summary-content")).isDisplayed());
        assertTrue(driver.findElement(By.className("order-summary-content")).getText().equals("Your Shopping Cart is empty!"));
    }

    @And("^Shopping cart link value is 0$")
    public void linkValueWithEmptyCartTest(){
        assertTrue(driver.findElement(By.className("cart-qty")).getText().equals("(0)"));
    }
}
