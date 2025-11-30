package automationexercises.pages.components;

import automationexercises.pages.*;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public class NavigationBarComponent {

    private Page page;

    // Locators
    private final Locator homeButton;
    private final Locator productsButton;
    private final Locator cartButton;
    private final Locator logoutButton;
    private final Locator signupLoginButton;
    private final Locator testCasesButton;
    private final Locator deleteAccountButton;
    private final Locator apiButton;
    private final Locator videoTutorials;
    private final Locator contactUsButton;
    private final Locator homePageLabel;
    private final Locator userLabel;

    public NavigationBarComponent(Page page) {
        this.page = page;

        // Locators initialization
        this.homeButton = page.locator("//a[.=' Home']");
        this.productsButton = page.locator("a[href='/products']");
        this.cartButton = page.locator("//a[.=' Cart']");
        this.logoutButton = page.locator("//a[.=' Logout']");
        this.signupLoginButton = page.locator("//a[.=' Signup / Login']");
        this.testCasesButton = page.locator("//a[.=' Test Cases']");
        this.deleteAccountButton = page.locator("//a[.=' Delete Account']");
        this.apiButton = page.locator("//a[.=' API Testing']");
        this.videoTutorials = page.locator("//a[.=' Video Tutorials']");
        this.contactUsButton = page.locator("//a[.=' Contact us']");
        this.homePageLabel = page.getByText("FEATURES ITEMS");
        this.userLabel = page.locator("b");
    }

    // Actions

    @Step("Navigate to the Home Page")
    public NavigationBarComponent navigate() {
        page.navigate(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on Home Button")
    public NavigationBarComponent clickOnHomeButton() {
        homeButton.click();
        return this;
    }

    @Step("Click on Products Button")
    public ProductsPage clickOnProductsButton() {
        productsButton.click();
        return new ProductsPage(page);
    }

    @Step("Click on Cart Button")
    public CartPage clickOnCartButton() {
        cartButton.click();
        return new CartPage(page);
    }

    @Step("Click on Logout Button")
    public SignupLoginPage clickOnLogoutButton() {
        logoutButton.click();
        return new SignupLoginPage(page);
    }

    @Step("Click on Signup/Login Button")
    public SignupLoginPage clickOnSignupLoginButton() {
        signupLoginButton.click();
        return new SignupLoginPage(page);
    }

    @Step("Click on Test Cases Button")
    public TestCasesPage clickOnTestCasesButton() {
        testCasesButton.click();
        return new TestCasesPage(page);
    }

    @Step("Click on Delete Account Button")
    public DeleteAccountPage clickOnDeleteAccountButton() {
        deleteAccountButton.click();
        return new DeleteAccountPage(page);
    }

    @Step("Click on ContactUs Button")
    public ContactUsPage clickOnContactUsButton() {
        contactUsButton.click();
        return new ContactUsPage(page);
    }

    // Validations

    @Step("Verify Home Page Label")
    public NavigationBarComponent verifyHomePage() {
        Assert.assertTrue(homePageLabel.isVisible(),
                "The Home Page label is not visible after navigation.");
        return this;
    }

    @Step("Verify User is Logged in")
    public NavigationBarComponent verifyUserLabel(String expectedUser) {
        String actualName = userLabel.textContent();
        LogsManager.info("Verifying logged in user label. Actual: " + actualName
                + " | Expected: Logged in as " + expectedUser);
        Assert.assertEquals(actualName, expectedUser,
                "Logged in user label does not match expected value. Expected: "
                        + expectedUser + ", Actual: " + actualName);
        return this;
    }

    @Step("Verify that user is logged out into Login Page")
    public SignupLoginPage verifyLogoutButtonNotVisible() {
        Assert.assertTrue(logoutButton.isHidden(),
                "The Logout button is not visible.");
        return new SignupLoginPage(page);
    }
}