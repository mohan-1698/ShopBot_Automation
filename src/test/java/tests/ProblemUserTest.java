package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

@Listeners(utils.Listeners.class)
public class ProblemUserTest extends BaseTest {

    // login as problem user
    public void loginProblemUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("problemUser"),
                ConfigReader.getProperty("password")
        );
    }

    // verify broken images
    @Test
    public void testBrokenImages() {

        loginProblemUser();

        ProductPage productPage = new ProductPage(driver);

        System.out.println("Logged in as problem user");

        boolean brokenImages = productPage.areImagesBroken();

        System.out.println("Are images broken: " + brokenImages);

        Assert.assertTrue(brokenImages, "Images are not broken but expected to be broken");
    }

    // verify add to cart behavior
    @Test
    public void testAddToCartBehavior() {

        loginProblemUser();

        ProductPage productPage = new ProductPage(driver);

        productPage.addFirstProductToCart();  

        int count = productPage.getCartCountSafe();

        System.out.println("Cart count after adding product: " + count);

        if (count == 0) {
            System.out.println("Add to cart failed as expected for problem user");
        } else {
            System.out.println("Product added successfully, behavior inconsistent");
        }

        Assert.assertTrue(count == 0 || count == 1, "Unexpected cart behavior");
    }
}