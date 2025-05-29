package stepDefinitions;
import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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
        WebElement wishlistTable = driver.findElement(By.cssSelector("table.cart"));

        WebElement cartTable = driver.findElement(By.cssSelector("table.cart"));
        String tableText = cartTable.getText();

        assertTrue("Wishlist does not contain 'Smartphone'", tableText.contains("Smartphone"));

    }


    @And("User adds the following items to the wishlist:")
    public void user_adds_items_to_wishlist(io.cucumber.datatable.DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            String urlPart = product.toLowerCase().replace(" ", "-").replaceAll("[^a-z0-9\\-]", "");
            driver.get("https://demowebshop.tricentis.com/" + urlPart);
            WebElement button = driver.findElement(By.cssSelector("input[value='Add to wishlist']"));
            button.click();
        }
    }

    @And("User selects the following items to remove:")
    public void user_selects_items_to_remove(io.cucumber.datatable.DataTable dataTable) {
        List<String> itemsToRemove = dataTable.asList();

        for (String itemName : itemsToRemove) {
            try {
                WebElement productRow = driver.findElement(
                        By.xpath("//td[@class='product']/a[contains(text(), '" + itemName + "')]/ancestor::tr")
                );
                WebElement removeCheckbox = productRow.findElement(By.name("removefromcart"));
                if (!removeCheckbox.isSelected()) {
                    removeCheckbox.click();
                }
            } catch (Exception e) {
                System.out.println("Could not find item to remove: " + itemName);
            }
        }
    }
    @When("User opens the wishlist page")
    public void open_wishlist_page() {
        driver.findElement(By.linkText("Wishlist")).click();
    }


    @When("User selects an item in wishlist to add to cart")
    public void user_selects_item_to_add_to_cart() {
        WebElement checkbox = driver.findElement(By.name("addtocart"));
        checkbox.click();
    }

    @And("User clicks the Add to Cart button")
    public void user_clicks_add_to_cart() {
        WebElement addToCartBtn = driver.findElement(By.name("addtocartbutton"));
        addToCartBtn.click();
    }

    @Then("The item should appear in the shopping cart")
    public void verify_item_in_cart() {

        driver.findElement(By.className("ico-cart")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart")));
        List<WebElement> items = driver.findElements(By.cssSelector(".cart td.product"));
        assertFalse(items.isEmpty());
    }
    @When("User selects the following items to add to cart:")
    public void user_selects_multiple_items_to_add_to_cart(List<String> products) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for (String product : products) {
            WebElement row = driver.findElement(By.xpath("//td[@class='product']/a[contains(text(),\"" + product + "\")]/ancestor::tr"));
            WebElement checkbox = row.findElement(By.name("addtocart"));
            checkbox.click();
        }
    }

    @And("User clicks the Add to Cart button from wishlist")
    public void user_clicks_add_to_cart_from_wishlist() {
        WebElement addToCartBtn = driver.findElement(By.name("addtocartbutton"));
        addToCartBtn.click();
    }

    @Then("The selected items should appear in the shopping cart:")
    public void selected_items_should_be_in_cart(io.cucumber.datatable.DataTable dataTable) {
        driver.findElement(By.linkText("Shopping cart")).click();

        List<String> expectedItems = dataTable.asList();
        String pageSource = driver.getPageSource();

        for (String item : expectedItems) {
            assertTrue("Item not found in cart: " + item, pageSource.contains(item));
        }
    }


    @And("User selects the following items for cart via update button:")
    public void select_items_to_add_to_cart(io.cucumber.datatable.DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            WebElement row = driver.findElement(By.xpath("//td[@class='product']/a[contains(text(),\"" + product + "\")]/ancestor::tr"));
            WebElement checkbox = row.findElement(By.name("addtocart"));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }


    @When("User logs out")
    public void user_logs_out() {
        driver.findElement(By.linkText("Log out")).click();
    }


    @Then("The wishlist should contain the following items:")
    public void wishlist_should_contain_items(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedItems = dataTable.asList();

        for (String item : expectedItems) {
            List<WebElement> matches = driver.findElements(
                    By.xpath("//td[@class='product']/a[contains(text(), \"" + item + "\")]")
            );
            boolean isPresent = matches.stream().anyMatch(WebElement::isDisplayed);
            assertTrue("Expected item not found in wishlist: " + item, isPresent);
        }
    }




}
