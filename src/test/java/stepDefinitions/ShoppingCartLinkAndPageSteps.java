package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.an.E;
import io.cucumber.java.en.*;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

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
    public void updateShoppingCartButtonPress() {
        driver.findElement(By.className("update-cart-button")).click();
    }

    @Then("^Message can be seen: \"Your Shopping Cart is empty!\"$")
    public void checkShoppingCartEmpty(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("order-summary-content")));
        assertTrue(driver.findElement(By.className("order-summary-content")).isDisplayed());
        assertTrue(driver.findElement(By.className("order-summary-content")).getText().equals("Your Shopping Cart is empty!"));
    }

    @And("^Shopping cart link value is (\\d+)$")
    public void linkValueShoppingCart(int number){
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

    @Given("^Multiple test products with given names and quantities are added to cart:$")
    public void addingMultipleItemsToShoppingCart(Map<String,Integer> items){
        for(Map.Entry<String, Integer> item : items.entrySet()){
            driver.get("https://demowebshop.tricentis.com/"+item.getKey());
            insertItemQuantity(item.getValue());
            addToCartButtonPress();
        }
    }

    @When("^I input new quantity values for products in cart:$")
    public void insertingMultipleQuantities(List<String> quantities){
        List<WebElement> items =  driver.findElements(By.className("cart-item-row"));
        for(WebElement item : items){
            item.findElement(By.className("qty-input")).clear();
            item.findElement(By.className("qty-input")).sendKeys(quantities.get(items.indexOf(item)));
        }
    }

    @Then("^Quantities of items are bigger than initial values:$")
    public void checkingMultipleValuesIncreased(List<Integer> oldQuantities){
        List<WebElement> items =  driver.findElements(By.className("cart-item-row"));
        for(WebElement item : items){
            assertTrue(Integer.parseInt(item.findElement(By.className("qty-input")).getAttribute("value"))>oldQuantities.get(items.indexOf(item)));
        }
    }

    @And("^Total with multiple values is calculated correctly$")
    public void totalPriceWithMultipleItemsCalculation(){
        //These variables are not necessary they just improve readability
        double unitPrice;
        int quantity;
        double expectedTotal=0;
        double actualTotal = Double.parseDouble(driver.findElement(By.cssSelector(".order-total strong")).getText());
        List<WebElement> items =  driver.findElements(By.className("cart-item-row"));
        for(WebElement item : items){
            unitPrice = Double.parseDouble(item.findElement(By.className("product-unit-price")).getText());
            quantity = Integer.parseInt(item.findElement(By.className("qty-input")).getAttribute("value"));
            expectedTotal+=unitPrice*quantity;
        }
        assertEquals(expectedTotal, actualTotal, 0.01);
    }

    @And("^Shopping cart link value equals sum of all quantities$")
    public void linkValueMultipleItemsShoppingCart(){
        List<WebElement> items =  driver.findElements(By.className("cart-item-row"));
        int sum=0;
        for(WebElement item : items){
            sum+=Integer.parseInt(item.findElement(By.className("qty-input")).getAttribute("value"));
        }
        linkValueShoppingCart(sum);
    }

    @Then("^Quantities of items are smaller than initial values:$")
    public void checkingMultipleValuesDecreased(List<Integer> oldQuantities){
        List<WebElement> items =  driver.findElements(By.className("cart-item-row"));
        for(WebElement item : items){
            assertTrue(Integer.parseInt(item.findElement(By.className("qty-input")).getAttribute("value"))<oldQuantities.get(items.indexOf(item)));
        }
    }

    @When("^I click remove checkbox beside the product$")
    public void clickingRemoveCheckboxSingularItem(){
        driver.findElement(By.cssSelector("[name='removefromcart']")).click();
    }

    @When("^I click remove checkbox beside each the product$")
    public void clickingRemoveCheckboxEveryItem(){
        List<WebElement> items =  driver.findElements(By.className("cart-item-row"));
        for(WebElement item : items){
            item.findElement(By.cssSelector("[name='removefromcart']")).click();
        }
    }

    @When("^I click the remove checkbox beside these items:$")
    public void clickingRemoveCheckboxOnSomeItems(List<String> itemUrlEnds){
        for(String itemName : itemUrlEnds){
            //Variable not needed, added for readability
            WebElement actualItem = driver.findElement(By.xpath("//*[contains(@href, '"+itemName+"') and @class='product-name']/ancestor::tr"));
            actualItem.findElement(By.cssSelector("[name='removefromcart']")).click();
        }
    }

    @Then("^Removed items are no longer part of shopping cart:$")
    public void checkMultipleItemsRemoved(List<String> itemUrlEnds){
        WebElement shoppingCart = driver.findElement(By.className("cart"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        for(String itemName : itemUrlEnds){
            assertTrue(shoppingCart.findElements(By.xpath("//*[contains(@href, '"+itemName+"') and @class='product-name']/ancestor::tr")).isEmpty());
        }
    }
}
