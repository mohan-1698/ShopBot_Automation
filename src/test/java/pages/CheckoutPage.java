package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By checkoutBtn = By.id("checkout");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");

    private By productName = By.className("inventory_item_name");
    private By productPrice = By.className("inventory_item_price");
    private By totalPrice = By.className("summary_total_label");

    private By finishBtn = By.id("finish");
    private By confirmationMsg = By.className("complete-header");

    public void clickCheckout() {
        click(checkoutBtn);
        System.out.println("Clicked checkout button");
    }

    public void enterDetails(String fname, String lname, String zip) {

        sendKeys(firstName, fname);
        sendKeys(lastName, lname);
        sendKeys(postalCode, zip);

        System.out.println("Entered checkout details");

        click(continueBtn);
    }

    public String getProductName() {
        String name = getText(productName);
        return name;
    }

    public String getProductPrice() {
        String price = getText(productPrice);
        return price;
    }

    public String getTotalPrice() {
        String total = getText(totalPrice);
        return total;
    }

    public void clickFinish() {
        click(finishBtn);
        System.out.println("Clicked finish button");
    }

    public String getConfirmationMessage() {
        return getText(confirmationMsg);
    }
}