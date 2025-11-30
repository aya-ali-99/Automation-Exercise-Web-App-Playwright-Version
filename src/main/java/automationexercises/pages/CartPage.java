package automationexercises.pages;

import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public class CartPage {

    private Page page;

    // Variables
    private String cartPage = "/view_cart";

    // Static Locators
    private final Locator proceedToCheckOutBtn;
    private final Locator checkOutModal;
    private final Locator checkOutLabel;
    private final Locator registerLoginBtn;
    private final Locator continueOnCart;
    private final Locator emptyCartMessage;

    public CartPage(Page page) {
        this.page = page;

        // Initialize static locators
        this.proceedToCheckOutBtn = page.locator(".col-sm-6 a").first();
        this.checkOutModal = page.locator(".modal-dialog");
        this.checkOutLabel = page.locator(".modal-dialog .modal-title");
        this.registerLoginBtn = page.locator(".modal-body u");
        this.continueOnCart = page.locator(".modal-footer button");
        this.emptyCartMessage = page.locator("#empty_cart");
    }

    // Dynamic Locators (as methods)
    private Locator getProductName(String productName) {
        return page.locator("(//h4/a[.='" + productName + "'])[1]");
    }

    private Locator productPrice(String productName) {
        return page.locator("(//h4/a[.='" + productName + "']//following::td[@class='cart_price']/p)[1]");
    }

    private Locator productQuantity(String productName) {
        return page.locator("(//h4/a[.='" + productName + "']//following::td[@class='cart_quantity']/button)[1]");
    }

    private Locator productTotal(String productName) {
        return page.locator("(//h4/a[.='" + productName + "']//following::td[@class='cart_total']/p)[1]");
    }

    private Locator removeProductDL(String productName) {
        return page.locator("(//h4 /a[.='" + productName + "'] //following::td[@class='cart_delete'] /a)[1]");
    }

    // Actions

    @Step("Navigate to view cart page")
    public CartPage navigate() {
        page.navigate(PropertyReader.getProperty("baseUrlWeb") + cartPage);
        LogsManager.info("Navigated to cart page");
        return this;
    }

    @Step("Click on Proceed to Checkout button")
    public CheckoutPage clickOnProceedToCheckoutButton() {
        proceedToCheckOutBtn.click();
        return new CheckoutPage(page);
    }

    @Step("Click on Proceed to Checkout button")
    public CartPage clickOnProceedToCheckoutButtonWithoutLogin() {
        proceedToCheckOutBtn.click();
        return this;
    }

    @Step("Remove product from the cart")
    public CartPage removeProductFromCart(String productName) {
        removeProductDL(productName).click();
        page.waitForTimeout(1000);
        return this;
    }

    @Step("Click on continue on cart button")
    public CartPage clickOnContinueOnCartButton() {
        continueOnCart.click();
        return this;
    }

    @Step("Click on register/login button")
    public SignupLoginPage clickOnRegisterLoginButton() {
        registerLoginBtn.click();
        return new SignupLoginPage(page);
    }

    // Verifications
    @Step("Verify product details in cart")
    public CartPage verifyProductDetailsInCart(String productName, String productPrice, String productQuantity,
            String productTotal) {
        String actualProductName = getProductName(productName).innerText();
        String actualProductPrice = productPrice(productName).innerText();
        String actualProductQuantity = productQuantity(productName).innerText();
        String actualProductTotal = productTotal(productName).innerText();
        LogsManager.info("Actual product name:" + productName
                + ", actual product price:" + productPrice
                + ", actual product quantity:" + productQuantity
                + ", actual product total:" + productTotal);

        Assert.assertEquals(actualProductName, productName, "Product name does not match");
        Assert.assertEquals(actualProductPrice, productPrice, "Product price does not match");
        Assert.assertEquals(actualProductQuantity, productQuantity, "Product quantity does not match");
        Assert.assertEquals(actualProductTotal, productTotal, "Product total does not match");

        return this;
    }

    @Step("Verify product quantity in cart")
    public CartPage verifyProductQuantityInCart(String productName, String productQuantity) {
        String actualProductQuantity = productQuantity(productName).innerText();
        Assert.assertEquals(actualProductQuantity,
                productQuantity, "Product quantity does not match");
        return this;
    }

    @Step("Verify check out label")
    public CartPage verifyCheckOutLabel() {
        String actualCheckOutLabel = checkOutLabel.innerText();
        Assert.assertEquals(actualCheckOutLabel,
                "Checkout", "Check out label does not match");
        return this;
    }

    @Step("Verify product is removed from cart")
    public CartPage verifyProductIsRemovedFromCart(String productName) {
        Assert.assertFalse(getProductName(productName).isVisible(),
                "Product is not removed from cart");
        return this;
    }
}
