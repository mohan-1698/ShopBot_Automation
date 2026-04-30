package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import utils.ConfigReader;

@Listeners(utils.Listeners.class)
public class CartTest extends BaseTest {

    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
    }

    @Test(priority = 1)
    public void testAddSingleProduct() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.addFirstProductToCart();

        int count = productPage.getCartBadgeCount();

        System.out.println("Cart count after adding one product " + count);

        Assert.assertEquals(count, 1, "Cart count is not one");
    }

    @Test(priority = 2)
    public void testAddTwoProducts() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.addFirstProductToCart();
        productPage.addSecondProductToCart();

        int count = productPage.getCartBadgeCount();

        System.out.println("Cart count after adding two products " + count);

        Assert.assertEquals(count, 2, "Cart count is not two");
    }

    @Test(priority = 3)
    public void testRemoveProduct() {

        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        productPage.goToCart();

        CartPage cartPage = new CartPage(driver);

        int beforeRemove = cartPage.getCartItemCount();
        System.out.println("Cart items before remove " + beforeRemove);

        cartPage.removeItem();

        boolean isEmpty = cartPage.isCartEmpty();
        System.out.println("Is cart empty after remove " + isEmpty);

        Assert.assertTrue(isEmpty, "Item not removed from cart");
    }

    @Test(priority = 4)
    public void testCartPersistence() {

        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        productPage.goToCart();

        CartPage cartPage = new CartPage(driver);

        int initialCount = cartPage.getCartItemCount();
        System.out.println("Initial cart count before navigation: " + initialCount);

        cartPage.clickContinueShopping();

        productPage.goToCart();

        int finalCount = cartPage.getCartItemCount();
        System.out.println("Cart count after returning to cart: " + finalCount);

        if (initialCount == finalCount) {
            System.out.println("Cart persistence verified successfully");
        } else {
            System.out.println("Cart persistence failed");
        }

        Assert.assertEquals(finalCount, initialCount, "Cart items not persisted");
    }
}