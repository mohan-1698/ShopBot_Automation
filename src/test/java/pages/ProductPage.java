package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By menuBtn = By.id("react-burger-menu-btn");
    private By logoutBtn = By.id("logout_sidebar_link");

    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");

    private By addToCartButtons = By.xpath("//button[text()='Add to cart']");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");

    // Logout
    public void clickMenu() {
        click(menuBtn);
    }

    public void clickLogout() {
        click(logoutBtn);
    }

    // Product list
    public List<String> getProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();

        for (WebElement e : elements) {
            names.add(e.getText());
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<WebElement> elements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement e : elements) {
            String priceText = e.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    // Sorting
    public void selectSortOption(String value) {
        org.openqa.selenium.support.ui.Select select =
                new org.openqa.selenium.support.ui.Select(driver.findElement(sortDropdown));
        select.selectByValue(value);
    }

    // Product detail
    public void clickFirstProduct() {
        driver.findElements(productNames).get(0).click();
    }

    public String getProductDetailName() {
        return driver.findElement(By.className("inventory_details_name")).getText();
    }

    public String getProductDetailPrice() {
        return driver.findElement(By.className("inventory_details_price")).getText();
    }

    // Cart actions
    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    public void addSecondProductToCart() {
        driver.findElements(addToCartButtons).get(1).click();
    }

    public int getCartBadgeCount() {
        String count = driver.findElement(cartBadge).getText();
        return Integer.parseInt(count);
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    // Problem user methods
    public boolean areImagesBroken() {

        List<WebElement> images = driver.findElements(By.className("inventory_item_img"));

        for (WebElement img : images) {
            String src = img.findElement(By.tagName("img")).getAttribute("src");

            if (src == null || src.contains("sl-404")) {
                return true;
            }
        }
        return false;
    }

    public int getCartCountSafe() {

        List<WebElement> badge = driver.findElements(cartBadge);

        if (badge.size() == 0) {
            return 0;
        }

        return Integer.parseInt(badge.get(0).getText());
    }
}