package stepDefinitions;
import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class WishlistSteps {
   private WebDriver driver;
public WishlistSteps() {
    this.driver = Hooks.driver;
}
    @Given("User is logged in")
    public void user_is_logged_in(){
        driver.get("https://demowebshop.tricentis.com/login");
        driver.findElement(By.id("Email")).sendKeys("test88881823@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("test9121923123");
        driver.findElement(By.cssSelector("input.button-1.login-button")).click();
    }

    @When("User adds a product to the wishlist")
    public void user_adds_product_to_wishlist() {
        driver.get("https://demowebshop.tricentis.com/smartphone");
        driver.findElement(By.id("add-to-wishlist-button-43")).click();
    }

    @When("User navigates to the wishlist page")
    public void user_navigates_to_wishlist() {
        driver.findElement(By.linkText("Wishlist")).click();
    }

    @Then("The wishlist should display the added product")
    public void wishlist_should_display_product() {
        WebElement product = driver.findElement(By.cssSelector(".wishlist-content"));
        assertTrue(product.getText().contains("Smartphone")); //
    }

    @Then("The page should load without error")
    public void page_should_load_without_error() {
        String title = driver.getTitle();
        assertNotNull(title);
        assertFalse(title.contains("Error"));
    }
}
