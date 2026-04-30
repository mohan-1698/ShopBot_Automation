package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(utils.Listeners.class)
public class ProductTest extends BaseTest {

    // 🔹 Helper method: Login (from config)
    public void login() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
    }

    // 1. Verify product list is displayed
    @Test
    public void testProductListDisplayed() {

        login();

        ProductPage productPage = new ProductPage(driver);
        List<String> products = productPage.getProductNames();

        // Print products
        System.out.println("Total Products Found: " + products.size());
        System.out.println("Product List: " + products);

        // Verification
        Assert.assertTrue(products.size() > 0, "No products displayed");
    }

    //2. Sort by Name (A-Z)
    @Test
    public void testSortByName() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectSortOption("az");

        List<String> actualList = productPage.getProductNames();

        List<String> sortedList = new ArrayList<>(actualList);
        Collections.sort(sortedList);

        // Verify first item
        String actualFirst = actualList.get(0);
        String expectedFirst = sortedList.get(0);

        System.out.println("Actual First Product: " + actualFirst);
        System.out.println("Expected First Product: " + expectedFirst);

        Assert.assertEquals(actualFirst, expectedFirst,
                "First product is not sorted correctly (A-Z)");
    }

    // ✅ 3. Sort by Price (Low-High)
    @Test
    public void testSortByPrice() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectSortOption("lohi");

        List<Double> actualPrices = productPage.getProductPrices();

        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(sortedPrices);

        // 🔹 Verify cheapest item
        Double actualFirst = actualPrices.get(0);
        Double expectedFirst = sortedPrices.get(0);

        System.out.println("Actual Cheapest Price: " + actualFirst);
        System.out.println("Expected Cheapest Price: " + expectedFirst);

        Assert.assertEquals(actualFirst, expectedFirst,
                "Cheapest product is not displayed first");
    }

    // ✅ 4. Product detail validation
    @Test
    public void testProductDetails() {

        login();

        ProductPage productPage = new ProductPage(driver);

        // 🔹 Get values from listing page
        String expectedName = productPage.getProductNames().get(0);
        String expectedPrice = "$" + productPage.getProductPrices().get(0);

        System.out.println("Listing Page Product Name: " + expectedName);
        System.out.println("Listing Page Product Price: " + expectedPrice);

        //Open product
        productPage.clickFirstProduct();

        //Get values from detail page
        String actualName = productPage.getProductDetailName();
        String actualPrice = productPage.getProductDetailPrice();

        System.out.println("Detail Page Product Name: " + actualName);
        System.out.println("Detail Page Product Price: " + actualPrice);

        //Verification
        Assert.assertEquals(actualName, expectedName, "Product name mismatch");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch");
    }
}