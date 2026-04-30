package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.xpath("//h3[@data-test='error']");

    // Actions
    public void login(String user, String pass) {
        sendKeys(username, user);
        sendKeys(password, pass);
        click(loginBtn);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }
}