/* Author: Anna Rozvadovska */
package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;


public class ShoppingCartSteps {
    private WebDriver driver;

    public ShoppingCartSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^user is on the homepage$")
    public void userOnTheHomePage() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    @Given("^shopping cart is empty$")
    public void shoppingCartIsEmpty() {
        assertEquals("(" + 0 + ")",
                driver.findElement(By.xpath("//*[@class='cart-qty']")).getText());
    }

    @When("^user adds one featured item to the shopping cart$")
    public void oneItemToCart(){
        driver.findElement(By.xpath("//*[@data-productid='31']//input")).click();
    }

    @Then("^user navigates to the cart$")
    public void navigateToCart(){
        driver.get("https://demowebshop.tricentis.com/cart");
    }

    @Then("^product \"([^\"]*)\" is visible in the cart$")
    public void productVisibleInTheCart(String productName){
        assertEquals(productName, driver.findElement(By.className("product-name")).getText());
    }

    @Then("^cart count is (\\d+)$")
    public void cartCount(int count) {
        assertEquals("(" + count + ")",
                driver.findElement(By.xpath("//*[@class='cart-qty']")).getText());
    }

    @Then("^message \"([^\"]*)\" is displayed$")
    public void successMessage(String message){
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bar-notification")));

        assertEquals(message, driver.findElement(By.xpath("//*[@id='bar-notification']/p")).getText());
    }

    @Then("^user navigates to the product details page \"([^\"]*)\"$")
    public void navigateToProductDetails(String url) {
        driver.get(url);
    }

    @When("^user inputs \"([^\"]*)\" as a quantity$")
    public void userInputsQuantity(String quantity) {
        WebElement quantityField = driver.findElement(By.className("qty-input"));

        quantityField.clear();
        quantityField.sendKeys(quantity);
    }

    @When("^user clicks \"Add to cart\" button with id \"([^\"]*)\"$")
    public void clickAddToCart(String id) {
        driver.findElement(By.id(id)).click();
    }

    @Then("^product quantity is (\\d+)$")
    public void checkQuantity(int quantity) {
        assertEquals(String.valueOf(quantity), driver.findElement(By.className("qty-input")).getAttribute("value"));
    }

    @Then("products are present in the cart")
    public void productsPresentInTheCart(){
        List<WebElement> products = driver.findElements(By.className("product-name"));

        assertEquals("14.1-inch Laptop", products.get(0).getText());
        assertEquals("Computing and Internet", products.get(1).getText());
    }

    @When("^user fills in all required fields$")
    public void fillRequiredFields() {
        driver.findElement(By.id("product_attribute_16_3_6_18")).click();
    }
}
