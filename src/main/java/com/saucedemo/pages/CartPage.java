package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get cart item count")
    public int getCartItemCount() {
        return cartItems.size();
    }

    @Step("Click checkout")
    public CartPage clickCheckout() {
        click(checkoutButton);
        return this;
    }

    @Step("Continue shopping")
    public InventoryPage continueShopping() {
        click(continueShoppingButton);
        return new InventoryPage(driver);
    }

    public boolean isLoaded() {
        return isDisplayed(checkoutButton);
    }
}
