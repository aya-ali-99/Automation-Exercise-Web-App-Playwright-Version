package automationexercises.pages;

import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public class ProductsPage {

    private Page page;
    public NavigationBarComponent navigationBar;

    // Variables
    private String productPage = "/products";

    // Static Locators
    private final Locator searchField;
    private final Locator searchButton;
    private final Locator itemAddedLabel;
    private final Locator viewCartButton;
    private final Locator continueShoppingButton;
    private final Locator kidsDressChoice;
    private final Locator categoryLabel;
    private final Locator womenTopsChoice;

    public ProductsPage(Page page) {
        this.page = page;
        this.navigationBar = new NavigationBarComponent(page);

        // Initialize static locators
        this.searchField = page.locator("#search_product");
        this.searchButton = page.locator("#submit_search");
        this.itemAddedLabel = page.locator(".modal-body > p").first();
        this.viewCartButton = page.locator("p > [href='/view_cart']");
        this.continueShoppingButton = page.locator(".modal-footer > button");
        this.kidsDressChoice = page.locator("#Kids [href='/category_products/4']");
        this.categoryLabel = page.locator(".col-sm-9 .title");
        this.womenTopsChoice = page.locator("#Women [href='/category_products/2']");
    }

    // Dynamic Locators (as methods)
    private Locator productName(String productName) {
        return page.locator("//div[@class='features_items']//div[@class='overlay-content']/p[.='" + productName + "']");
    }

    private Locator categoryName(String categoryName) {
        return page.locator("[href='#" + categoryName + "']");
    }

    private Locator productPrice(String productName) {
        return page.locator("//div[@class='features_items']//div[@class='overlay-content']/p[.='" + productName
                + "']//preceding-sibling::h2");
    }

    private Locator hoverOnProduct(String productName) {
        return page.locator(
                "//div[@class='features_items']//div[@class='productinfo text-center']/p[.='" + productName + "']");
    }

    private Locator addToCartButton(String productName) {
        return page.locator("//div[@class='features_items']//div[@class='productinfo text-center']/p[.='" + productName
                + "']//following-sibling::a");
    }

    private Locator viewProduct(String productName) {
        return page.locator("//p[.='" + productName + "']//following::div[@class='choose'][1]");
    }

    // Actions
    @Step("Navigate to Products Page")
    public ProductsPage navigate() {
        page.navigate(PropertyReader.getProperty("baseUrlWeb") + productPage);
        return this;
    }

    @Step("Search for product {productName}")
    public ProductsPage searchForProduct(String productName) {
        searchField.fill(productName);
        searchButton.click();
        return this;
    }

    @Step("Click on add to cart for {productName}")
    public ProductsPage clickOnAddToCart(String productName) {
        hoverOnProduct(productName).hover();
        addToCartButton(productName).click();
        return this;
    }

    @Step("Click on view product for {productName}")
    public ProductDetailsPage clickOnViewProduct(String productName) {
        viewProduct(productName).click();
        return new ProductDetailsPage(page);
    }

    @Step("Click on view cart button")
    public CartPage clickOnViewCart() {
        viewCartButton.click();
        return new CartPage(page);
    }

    @Step("Click on continue shopping button")
    public ProductsPage clickOnContinueShopping() {
        continueShoppingButton.click();
        return this;
    }

    @Step("Click on {categoryChoice} category choice")
    public ProductsPage clickOnCategoryChoice(String categoryChoice) {
        categoryName(categoryChoice).click();
        return this;
    }

    @Step("Click on kids dress choice")
    public ProductsPage clickOnKidsDressChoice() {
        kidsDressChoice.click();
        return this;
    }

    @Step("Click on women tops choice")
    public ProductsPage clickOnWomenTopsChoice() {
        womenTopsChoice.click();
        return this;
    }

    // Validations
    @Step("Validate product details for {productName} with price {productPrice}")
    public ProductsPage validateProductDetails(String productName, String productPrice) {
        hoverOnProduct(productName).hover();
        String actualProductName = productName(productName).innerText();
        String actualProductPrice = productPrice(productName).innerText();
        LogsManager.info("Actual product name: " + actualProductName + ", with price: " + actualProductPrice);
        Assert.assertEquals(actualProductName, productName, "Product name does not match");
        Assert.assertEquals(actualProductPrice, productPrice, "Product price does not match");
        return this;
    }

    @Step("Validate item added label contains {expectedLabel}")
    public ProductsPage validateItemAddedLabel(String expectedLabel) {
        String actualLabel = itemAddedLabel.innerText();
        LogsManager.info("Actual item added label: " + actualLabel);
        Assert.assertEquals(actualLabel, expectedLabel, "Item added label does not match");
        return this;
    }

    @Step("Verify category products are displayed")
    public ProductsPage verifyCategoryProductsAreDisplayed(String label) {
        String actualLabel = categoryLabel.innerText();
        LogsManager.info("Actual kids dress category label: " + actualLabel);
        Assert.assertEquals(actualLabel, label, "Kids dress category label does not match");
        return this;
    }
}
