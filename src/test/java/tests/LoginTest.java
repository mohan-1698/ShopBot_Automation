package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage; // ✅ ADD THIS
import utils.ExcelUtil;

@Listeners(utils.Listeners.class)
public class LoginTest extends BaseTest {

    // 🔹 Excel-based login test
    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedResult) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("success")) {

            // ✅ VALID LOGIN
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                    "Valid login failed");

        } else {

            // ❌ ERROR CASE (locked user / empty)
            String error = loginPage.getErrorMessage();

            Assert.assertTrue(error.length() > 0,
                    "Error message not displayed");

            if (username.equals("locked_out_user")) {
                String expectedError = "Epic sadface: Sorry, this user has been locked out.";
                Assert.assertEquals(error, expectedError,
                        "Locked user error message mismatch");
            }

            // ❌ Force fail → screenshot
            Assert.fail("Negative test case - capturing screenshot");
        }
    }

    // 🔹 Logout test (Statement 4)
    @Test
    public void testLogout() {

        LoginPage loginPage = new LoginPage(driver);

        // Step 1: Login
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);

        // Step 2: Open menu
        productPage.clickMenu();

        // Step 3: Click logout
        productPage.clickLogout();

        // Step 4: Verify redirected to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "Logout failed - not redirected to login page");
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {

        String path = System.getProperty("user.dir") + "/src/test/resources/logindata.xlsx";
        return ExcelUtil.getTestData(path, "Sheet1");
    }
}