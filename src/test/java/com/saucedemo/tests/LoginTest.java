package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.ConfigReader;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Login")
public class LoginTest extends BaseTest {

    @Test
    @Description("Valid user should land on inventory page")
    public void testValidLogin() {
        InventoryPage inventoryPage = new LoginPage(driver)
                .loginAs(
                        ConfigReader.get("standard.user"),
                        ConfigReader.get("password")
                );

        Assert.assertTrue(inventoryPage.isLoaded(),
                "Inventory page should be loaded after login");
    }

    @Test
    @Description("Locked out user should see error message")
    public void testLockedUserLogin() {
        String error = new LoginPage(driver)
                .enterUsername(ConfigReader.get("locked.user"))
                .enterPassword(ConfigReader.get("password"))
                .clickLoginExpectingFailure()
                .getErrorMessage();

        Assert.assertTrue(error.contains("locked out"),
                "Error message should mention locked out");
    }

    @Test
    @Description("Empty credentials should show error")
    public void testEmptyLogin() {
        String error = new LoginPage(driver)
                .enterUsername("")
                .enterPassword("")
                .clickLoginExpectingFailure()
                .getErrorMessage();

        Assert.assertFalse(error.isEmpty(),
                "Error message should be shown for empty credentials");
    }
}
