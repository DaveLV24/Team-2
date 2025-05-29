package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TcSteps {
    private WebDriver driver;

    public TcSteps() {
        this.driver = Hooks.driver;
    }

    private final String email = "kirils.lebeds@gmail.com";
    private final String password = "1q2w3e";

    @Given("User is on the Wishlist page")
    public void userIsOnTheWishlistPage() {
        driver.get("https://demowebshop.tricentis.com/wishlist");
    }

   @When("User selects all products in the Wishlist")
    public void userSelectsAllProductsInWishlist() {
       WebElement wishlistContainer = driver.findElement(By.cssSelector("div.wishlist-content"));
       String wishlistText = wishlistContainer.getText();

       if (wishlistText.contains("The wishlist is empty!")) {
           System.out.println("Wishlist is already empty — no products to remove.");
           return;
       }

       List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[name='removefromcart']"));
       assertFalse("Expected items to remove, but none were found", checkboxes.isEmpty());

       for (WebElement checkbox : checkboxes) {
           if (!checkbox.isSelected()) {
               checkbox.click();
           }
       }
    }

    @When("User clicks the \"Update wishlist\" button")
    public void userClicksUpdateWishlistButton() {
        WebElement wishlistContainer = driver.findElement(By.cssSelector("div.wishlist-content"));
        String wishlistText = wishlistContainer.getText().trim();

        if (wishlistText.contains("The wishlist is empty!")) {
            System.out.println("Wishlist is empty — no need to click 'Update wishlist'.");
            return;
        }

        WebElement updateButton = driver.findElement(By.cssSelector("input[name='updatecart']"));
        updateButton.click();

        driver.navigate().refresh();
    }

    @Then("Wishlist should be empty after update")
    public void wishlistShouldBeEmptyAfterUpdate() {
        WebElement wishlistContent = driver.findElement(By.cssSelector("div.wishlist-content"));
        String wishlistText = wishlistContent.getText().toLowerCase();
        assertTrue("Wishlist is not empty after update", wishlistText.contains("the wishlist is empty"));
    }

    @Then("User logs out")
    public void userLogsOut() {
        WebElement logoutLink = driver.findElement(By.cssSelector("a.ico-logout"));
        logoutLink.click();

        assertTrue("User is still logged in after logout",
        driver.findElement(By.linkText("Log in")).isDisplayed());
    }

    @Given("User is logged in \\(or logs in if needed)")
    public void userIsLoggedInOrLogsInIfNeeded() throws InterruptedException {
        driver.get("https://demowebshop.tricentis.com");

        boolean isLoggedIn = driver.findElements(By.linkText("Log in")).isEmpty();

        if (!isLoggedIn) {
            driver.findElement(By.linkText("Log in")).click();

            WebElement emailInput = driver.findElement(By.id("Email"));
            WebElement passwordInput = driver.findElement(By.id("Password"));
            WebElement loginButton = driver.findElement(By.cssSelector("input.login-button"));

            emailInput.clear();
            emailInput.sendKeys(email);
            passwordInput.clear();
            passwordInput.sendKeys(password);
            loginButton.click();

            Thread.sleep(1000);

            assertTrue(driver.findElements(By.linkText("Log in")).isEmpty());

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));

            assertTrue(driver.findElements(By.linkText("Log out")).size() > 0);
        }
    }

    @Given("No items are added to the Wishlist")
    public void noItemsAreAddedToTheWishlist() {
        driver.get("https://demowebshop.tricentis.com/wishlist");

        WebElement wishlistTable = driver.findElement(By.cssSelector("div.wishlist-content"));
        String wishlistText = wishlistTable.getText();
        assertTrue("Wishlist is not empty", wishlistText.contains("The wishlist is empty"));
    }

    @When("User opens the homepage")
    public void userOpensTheHomepage() {
        driver.get("https://demowebshop.tricentis.com");
    }

    @Then("User should see the \"Wishlist\" link in the Top Menu")
    public void userShouldSeeTheWishlistLinkInTheTopMenu() {
        WebElement wishlistLink = driver.findElement(By.cssSelector("a.ico-wishlist"));
        assertTrue("Wishlist link is not visible", wishlistLink.isDisplayed());
    }

    @Then("The Wishlist counter should show \"0\"")
    public void theWishlistCounterShouldShow0() {
        WebElement counter = driver.findElement(By.cssSelector("span.wishlist-qty"));
        String countText = counter.getText();
        assertEquals("(0)", countText);
    }

    @Given("User is on a \"Blue and green Sneaker\" page")
    public void userAddsBlueAndGreenSneakers() {
        driver.get("https://demowebshop.tricentis.com/blue-and-green-sneaker");
    }

    @Given("User is on a \"Black and white diamond heart\" page")
    public void userAddsBlackWhiteDiamondHeart() {
        driver.get("https://demowebshop.tricentis.com/black-white-diamond-heart");
    }

    private int previousWishlistCount = 0;

    @When("User clicks the \"Add to Wish List\" button")
    public void userClicksAddToWishlistButton() throws InterruptedException {
        WebElement counter = driver.findElement(By.cssSelector("span.wishlist-qty"));
        String countText = counter.getText().replaceAll("[()]", "");
        previousWishlistCount = Integer.parseInt(countText);

        WebElement addToWishlistButton = driver.findElement(By.cssSelector("input[value='Add to wishlist']"));
        addToWishlistButton.click();
        Thread.sleep(1000);
    }

    @Given("User remembers the current Wishlist count")
    public void userRemembersTheCurrentWishlistCount() {
        WebElement counter = driver.findElement(By.cssSelector("span.wishlist-qty"));
        String countText = counter.getText().replaceAll("[()]", "");
        previousWishlistCount = Integer.parseInt(countText);
        System.out.println("Stored wishlist count: " + previousWishlistCount);
    }

    @Then("The Wishlist counter should increase by 1")
    public void wishlistCounterShouldIncreaseBy1() {
        driver.navigate().refresh();

        WebElement counter = driver.findElement(By.cssSelector("span.wishlist-qty"));
        String countText = counter.getText().replaceAll("[()]", "").trim();
        int currentCount = Integer.parseInt(countText);

        assertEquals("Wishlist counter did not increase by 1",
                previousWishlistCount + 1, currentCount);
        previousWishlistCount = currentCount;
    }

    public class ProductData {
        public String name;
        public String price;
        public int quantity;

        public ProductData(String name, String price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    private final Map<String, ProductData> savedProducts = new HashMap<>();

    @When("User opens product {string} and adds quantity {int} to the Wishlist")
    public void userOpensProductAndAddsQuantity(String productName, int quantity) throws InterruptedException {
        String url;
        String quantityFieldId;

        switch (productName.toLowerCase()) {
            case "blue and green sneaker":
                url = "https://demowebshop.tricentis.com/blue-and-green-sneaker";
                quantityFieldId = "addtocart_28_EnteredQuantity";
                break;
            case "black & white diamond heart":
            case "black and white diamond heart":
                url = "https://demowebshop.tricentis.com/black-white-diamond-heart";
                quantityFieldId = "addtocart_14_EnteredQuantity";
                break;
            case "smartphone":
                url = "https://demowebshop.tricentis.com/smartphone";
                quantityFieldId = "addtocart_43_EnteredQuantity";
                break;
            default:
                throw new IllegalArgumentException("Unknown product: " + productName);
        }

        driver.get(url);
        Thread.sleep(1000);

        WebElement nameElement = driver.findElement(By.cssSelector("h1[itemprop='name']"));
        String actualName = nameElement.getText().trim();

        WebElement priceElement = driver.findElement(By.cssSelector("span[itemprop='price']"));
        String price = priceElement.getText().trim();

        WebElement quantityInput = driver.findElement(By.id(quantityFieldId));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));

        WebElement addToWishlistButton = driver.findElement(By.cssSelector("input[value='Add to wishlist']"));
        addToWishlistButton.click();

        Thread.sleep(1000);

        savedProducts.put(actualName.toLowerCase(), new ProductData(actualName, price, quantity));
    }

    @Then("The Wishlist page should contain:")
    public void wishlistPageShouldContain(DataTable dataTable) {
        driver.get("https://demowebshop.tricentis.com/wishlist");

        List<Map<String, String>> expectedRows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> expected : expectedRows) {
            String expectedName = expected.get("Name").trim();
            String expectedQty = expected.get("Quantity").trim();

            ProductData expectedData = savedProducts.get(expectedName.toLowerCase());
            assertNotNull("Product data not saved: " + expectedName, expectedData);

            WebElement row = driver.findElement(By.xpath(
                    "//table[contains(@class,'cart')]//tr[td[@class='product']/a[text()=\"" + expectedName + "\"]]"));

            String actualPrice = row.findElement(By.cssSelector("span.product-unit-price")).getText().trim();
            String actualQty = row.findElement(By.cssSelector("input.qty-input")).getAttribute("value").trim();

            assertEquals("Price mismatch for product: " + expectedName, expectedData.price, actualPrice);
            assertEquals("Quantity mismatch for product: " + expectedName, expectedQty, actualQty);
        }
    }

    @Then("The Wishlist counter in the Top Menu should show {string}")
    public void wishlistCounterShouldShow(String expectedText) {
        WebElement counter = driver.findElement(By.cssSelector("span.wishlist-qty"));
        String actualText = counter.getText().replaceAll("[()]", "").trim();
        assertEquals(expectedText, actualText);
    }
}