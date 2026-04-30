package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By cartItems = By.className("inventory_item_name");
    private By removeButton = By.xpath("//button[text()='Remove']");
    private By continueShopping = By.id("continue-shopping");

    public int getCartItemCount() {
        List items = driver.findElements(cartItems);
        return items.size();
    }

    public void removeItem() {
        click(removeButton);
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItems).size() == 0;
    }

    public void clickContinueShopping() {
        click(continueShopping);
    }
}