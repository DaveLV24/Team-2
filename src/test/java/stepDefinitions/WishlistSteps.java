package stepDefinitions;
import hooks.Hooks;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    @When("User removes an item from the wishlist")
    public void user_removes_item_from_wishlist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name^='removefromcart']")));
        WebElement removeCheckbox = driver.findElement(By.cssSelector("input[name^='removefromcart']"));
        removeCheckbox.click();
        WebElement updateButton = driver.findElement(By.name("updatecart"));
        updateButton.click();
    }

    @Then("The wishlist should be updated and not contain the removed item")
    public void wishlist_should_be_updated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isWishlistEmpty = driver.getPageSource().contains("The wishlist is empty!");
        boolean isItemRemoved = driver.findElements(By.cssSelector("td.product")).isEmpty();

        assertTrue("Wishlist was not updated after removing the item", isWishlistEmpty || isItemRemoved);
    }

    @And("User adds the following items to the wishlist:")
    public void user_adds_items_to_wishlist(io.cucumber.datatable.DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            String urlPart = product.toLowerCase().replace(" ", "-").replaceAll("[^a-z0-9\\-]", "");
            driver.get("https://demowebshop.tricentis.com/" + urlPart);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Add to wishlist']")));
            button.click();
        }
    }

    @And("User selects the following items to remove:")
    public void select_items_to_remove(io.cucumber.datatable.DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            WebElement row = driver.findElement(By.xpath("//td[@class='product']/a[contains(text(),\"" + product + "\")]/ancestor::tr"));
            row.findElement(By.name("removefromcart")).click();
        }
    }
    @When("User opens the wishlist page")
    public void open_wishlist_page() {
        driver.findElement(By.linkText("Wishlist")).click();
    }
    @And("User updates the wishlist")
    public void update_wishlist() {
        driver.findElement(By.name("updatecart")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//td[@class='product']/a[contains(text(), 'Rockabilly')]")
        ));
    }



}
