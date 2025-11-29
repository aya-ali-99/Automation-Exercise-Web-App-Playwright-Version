package automationexercises.pages.components;


import automationexercises.pages.*;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertThat;

public class NavigationBarComponent {

    private Page page;

    public NavigationBarComponent(Page page) {
        this.page = page;
    }

    // Locators (Playwright)
    private final Locator homeButton = page.locator("//a[.=' Home']");

    private final Locator productsButton = page.locator("a[href='/products']");

    private final Locator cartButton = page.locator("//a[.=' Cart']");

    private final Locator logoutButton = page.locator("//a[.=' Logout']");

    private final Locator signupLoginButton = page.locator("//a[.=' Signup / Login']");

    private final Locator testCasesButton = page.locator("//a[.=' Test Cases']");

    private final Locator deleteAccountButton = page.locator("//a[.=' Delete Account']");

    private final Locator apiButton = page.locator("//a[.=' API Testing']");

    private final Locator videoTutorials = page.locator("//a[.=' Video Tutorials']");

    private final Locator contactUsButton = page.locator("//a[.=' Contact us']");

    private final Locator homePageLabel = page.getByText("FEATURES ITEMS");

    private final Locator userLabel = page.locator("b");



    // Actions

    @Step("Navigate to the Home Page")
    public NavigationBarComponent navigate(){
        page.navigate(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on Home Button")
    public NavigationBarComponent clickOnHomeButton(){
        homeButton.click();
        return this;
    }

    @Step("Click on Products Button")
    public ProductsPage clickOnProductsButton(){
        productsButton.click();
        return new ProductsPage(page);
    }

    @Step("Click on Cart Button")
    public CartPage clickOnCartButton() {
        cartButton.click();
        return new CartPage(page);
    }

    @Step("Click on Logout Button")
    public SignupLoginPage clickOnLogoutButton(){
        logoutButton.click();
        return new SignupLoginPage(page);
    }

    @Step("Click on Signup/Login Button")
    public SignupLoginPage clickOnSignupLoginButton(){
        signupLoginButton.click();
        return new SignupLoginPage(page);
    }

    @Step("Click on Test Cases Button")
    public TestCasesPage clickOnTestCasesButton(){
        testCasesButton.click();
        return new TestCasesPage(page);
    }

    @Step("Click on Delete Account Button")
    public DeleteAccountPage clickOnDeleteAccountButton(){
        deleteAccountButton.click();
        return new DeleteAccountPage(page);
    }

    @Step("Click on ContactUs Button")
    public ContactUsPage clickOnContactUsButton(){
        contactUsButton.click();
        return new ContactUsPage(page);
    }


    // Validations

    @Step("Verify Home Page Label")
    public NavigationBarComponent verifyHomePage(){
        Assertions.assertTrue(homePageLabel.isVisible(),
                "The Home Page label is not visible after navigation.");
        return this;
    }

    @Step("Verify User is Logged in")
    public NavigationBarComponent verifyUserLabel(String expectedUser){
        String actualName = userLabel.textContent();
        LogsManager.info("Verifying logged in user label. Actual: " + actualName
                + " | Expected: Logged in as " + expectedUser);
        Assertions.assertEquals(expectedUser, actualName,
                "Logged in user label does not match expected value. Expected: "
                        + expectedUser + ", Actual: " + actualName);
        return this;
    }

    @Step("Verify that user is logged out into Login Page")
    public SignupLoginPage verifyLogoutButtonNotVisible() {
        Assertions.assertTrue(logoutButton.isHidden(),
                "The Logout button is not visible.");
        return new SignupLoginPage(page);
    }
}
