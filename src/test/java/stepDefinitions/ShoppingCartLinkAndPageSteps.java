package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class ShoppingCartLinkAndPageSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    private final int testItemQuantity = 4; // Change test items quantity
    public ShoppingCartLinkAndPageSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on test product page$")
    public void goToTestProductPage(){
        driver.get("https://demowebshop.tricentis.com/smartphone");
    }

    @Given("^Test item is added to shopping cart$")
    public void productAddedToShoppingCartForTests() throws Exception{
        insertItemQuantity(testItemQuantity);
        addToCartButtonPress();
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
        assertEquals((unitPrice*quantity), actualTotal, 0.01);
    }

    @Then("^Quantity of item is smaller than previous and equals (\\d+)$")
    public void checkNewQuantitySmaller(int number){
        assertTrue(testItemQuantity>number);
        assertTrue(driver.findElement(By.className("qty-input")).getAttribute("value").equals(Integer.toString(number)));
    }

    @And("^I click add to cart button$")
    public void addToCartButtonPress(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.className("add-to-cart-button")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("success")));
    }
}
