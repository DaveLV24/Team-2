package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class ShoppingCartQuantityAndTotalSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    private final int testItemQuantity = 3; // Change test items quantity
    public ShoppingCartQuantityAndTotalSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on test product page$")
    public void goToTestProductPage(){
        driver.get("https://demowebshop.tricentis.com/smartphone");
    }

    @Given("^Test item is added to shopping cart$")
    public void productAddedToShoppingCartForTests() throws Exception{
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.className("qty-input")).clear();
        driver.findElement(By.className("qty-input")).sendKeys(Integer.toString(testItemQuantity));
        driver.findElement(By.className("add-to-cart-button")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("success")));
    }

    @And("^I am on the shopping cart page$")
    public void setDriverToShoppingCartPage(){
        driver.get("https://demowebshop.tricentis.com/cart");
    }

    @When("^I input (\\d+) as new quantity value$")
    public void insertItemQuantity(int number){
        driver.findElement(By.className("qty-input")).clear();
        driver.findElement(By.className("qty-input")).sendKeys(Integer.toString(number));
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

    @And("^Shopping cart link value is (\\d+)$")
    public void linkValueWithEmptyCartTest(int number){
        assertTrue(driver.findElement(By.className("cart-qty")).getText().equals("("+number+")"));
    }

    @Then("^Quantity of item is bigger than previous and equals (\\d+)$")
    public void checkNewQuantityBigger(int number){
        assertTrue(testItemQuantity<number);
        assertTrue(driver.findElement(By.className("qty-input")).getAttribute("value").equals(Integer.toString(number)));
    }

    @And("^Total price is calculated correctly$")
    public void totalPriceCalculation(){
        double unitPrice = Double.parseDouble(driver.findElement(By.className("product-unit-price")).getText());
        int quantity = Integer.parseInt(driver.findElement(By.className("qty-input")).getAttribute("value"));
        double actualTotal = Double.parseDouble(driver.findElement(By.cssSelector(".order-total strong")).getText());
        assertEquals((unitPrice*quantity), actualTotal);
    }
}
