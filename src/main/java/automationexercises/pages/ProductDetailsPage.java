package automationexercises.pages;

import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class ProductDetailsPage {

    private Page page;

    // Variables
    private String productDetailsPage = "/product_details/1";

    // Locators
    private final Locator productName;
    private final Locator productPrice;
    private final Locator addToCartButton;
    private final Locator quantityInput;
    private final Locator reviewName;
    private final Locator reviewEmail;
    private final Locator reviewTextArea;
    private final Locator submitReviewButton;
    private final Locator reviewSuccessMessage;
    private final Locator viewCartBtn;

    public ProductDetailsPage(Page page) {
        this.page = page;

        // Initialize locators
        this.productName = page.locator(".product-information h2");
        this.productPrice = page.locator(".product-information span span");
        this.addToCartButton = page.locator(".product-information button");
        this.quantityInput = page.locator("#quantity");
        this.reviewName = page.locator("#name");
        this.reviewEmail = page.locator("#email");
        this.reviewTextArea = page.getByPlaceholder("Add Review Here!");
        this.submitReviewButton = page.locator("//*[@id='button-review']");
        this.reviewSuccessMessage = page.locator(".alert-success span");
        this.viewCartBtn = page.locator(".modal-content u");
    }

    // Actions

    @Step("Navigate to products details")
    public ProductDetailsPage navigate() {
        page.navigate(PropertyReader.getProperty("baseUrlWeb") + productDetailsPage);
        LogsManager.info("Navigated to product details page");
        return this;
    }

    @Step("Add product to cart")
    public ProductDetailsPage clickOnAddToCartButton(){
        addToCartButton.click();
        return this;
    }

    @Step("Add Review")
    public ProductDetailsPage addReview(String name, String email, String review){
        reviewName.fill(name);
        reviewEmail.fill(email);
        reviewTextArea.fill(review);
        submitReviewButton.click();
        return this;
    }

    @Step("Add quantity")
    public ProductDetailsPage addQuantity(String quantity){
        quantityInput.fill(quantity);
        return this;
    }

    @Step("Click on view cart button")
    public CartPage clickOnViewCartButton(){
        viewCartBtn.click();
        return new CartPage(page);
    }

    // Verifications
    @Step("Verify product details")
    public ProductDetailsPage validateProductDetails(String productName, String productPrice){
        String actualProductName = this.productName.textContent();
        String actualProductPrice = this.productPrice.textContent();
        LogsManager.info("Actual product name: " + actualProductName +
                ", actual price: " + actualProductPrice);
        Assertions.assertEquals(productName, actualProductName,
                "Product name does not match");
        Assertions.assertEquals(productPrice, actualProductPrice,
                "Product price does not match");
        return this;
    }

    @Step("Verify review success message")
    public ProductDetailsPage validateReviewSuccessMessage(String SuccessMessage){
        String actualSuccessMessage = reviewSuccessMessage.textContent();
        LogsManager.info("Actual review success message: " + actualSuccessMessage);
        Assertions.assertEquals(SuccessMessage, actualSuccessMessage,
                "Review success message does not match");
        return this;
    }

}
