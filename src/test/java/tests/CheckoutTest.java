package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ConfigReader;

@Listeners(utils.Listeners.class)
public class CheckoutTest extends BaseTest {

    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
    }

    @Test
    public void testCheckoutFlow() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.addFirstProductToCart();

        String expectedName = productPage.getProductNames().get(0);
        String expectedPrice = "$" + productPage.getProductPrices().get(0);

        System.out.println("Selected Product Name: " + expectedName);
        System.out.println("Selected Product Price: " + expectedPrice);

        productPage.goToCart();

        CartPage cartPage = new CartPage(driver);

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickCheckout();

        checkoutPage.enterDetails(
                ConfigReader.getProperty("firstname"),
                ConfigReader.getProperty("lastname"),
                ConfigReader.getProperty("zipcode")
        );

        System.out.println("Current URL after continue: " + driver.getCurrentUrl());

        String actualName = checkoutPage.getProductName();
        String actualPrice = checkoutPage.getProductPrice();
        String total = checkoutPage.getTotalPrice();

        System.out.println("Checkout Product Name: " + actualName);
        System.out.println("Checkout Product Price: " + actualPrice);
        System.out.println("Total Price: " + total);

        Assert.assertEquals(actualName, expectedName, "Product name mismatch");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch");

        checkoutPage.clickFinish();

        String confirmation = checkoutPage.getConfirmationMessage();

        System.out.println("Order Confirmation Message: " + confirmation);

        Assert.assertTrue(confirmation.contains("Thank you"), "Order not completed");
    }
}