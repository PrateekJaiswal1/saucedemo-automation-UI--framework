package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add first item to cart")
    public InventoryPage addFirstItemToCart() {
        WebElement addButton = inventoryItems.get(0)
                .findElement(By.tagName("button"));
        click(addButton);
        return this;
    }

    @Step("Go to cart")
    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isLoaded() {
        return isDisplayed(cartIcon);
    }
}
