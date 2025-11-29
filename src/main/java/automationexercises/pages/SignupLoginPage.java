package automationexercises.pages;

import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class SignupLoginPage {

    private Page page;
    public NavigationBarComponent navigationBar;
    private final String signupLoginEndPoint = "/login";

    public SignupLoginPage(Page page) {
        this.page = page;
        this.navigationBar = new NavigationBarComponent(page);
    }

    // Locators (Playwright)
    private final Locator loginEmail = page.locator("[data-qa='login-email']");
    private final Locator loginPassword = page.locator("[data-qa='login-password']");
    private final Locator loginButton = page.locator("[data-qa='login-button']");
    private final Locator signupName = page.locator("[data-qa='signup-name']");
    private final Locator signupEmail = page.locator("[data-qa='signup-email']");
    private final Locator signupButton = page.locator("[data-qa='signup-button']");
    private final Locator signupLabel = page.locator(".signup-form > h2");
    private final Locator loginError = page.locator(".login-form p");
    private final Locator registerError = page.locator(".signup-form p");

    // Actions

    @Step("Navigate to Register/Login page")
    public SignupLoginPage navigate(){
        page.navigate(PropertyReader.getProperty("baseUrlWeb") + signupLoginEndPoint);
        return this;
    }

    @Step("Enter email {email} in login field")
    public SignupLoginPage enterLoginEmail(String email){
        loginEmail.fill(email);
        return this;
    }

    @Step("Enter password {password} in login field")
    public SignupLoginPage enterLoginPassword(String password){
        loginPassword.fill(password);
        return this;
    }

    @Step("Click login button")
    public SignupLoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    @Step("Enter name {name} in signup field")
    public SignupLoginPage enterSignupName(String name){
        signupName.fill(name);
        return this;
    }

    @Step("Enter email {email} in signup field")
    public SignupLoginPage enterSignupEmail(String email){
        signupEmail.fill(email);
        return this;
    }

    @Step("Click signup button")
    public SignupLoginPage clickSignupButton(){
        signupButton.click();
        return this;
    }

    // Validations

    @Step("Verify signup label is visible")
    public SignupLoginPage verifySignupLabelVisible(){
        Assertions.assertTrue(signupLabel.isVisible(),
                "The Signup label is not visible.");
        return this;
    }

    @Step("Verify login error message is {errorExpected}")
    public SignupLoginPage verifyLoginErrorMessage(String errorExpected){
        String errorActual = loginError.textContent();
        LogsManager.info("Verifying login error message. Actual: " + errorActual
                + ", Expected: " + errorExpected);
        Assertions.assertEquals(errorExpected, errorActual,
                "Login error message is not as expected. Expected: "
                        + errorExpected + ", Actual: " + errorActual);
        return this;
    }

    @Step("Verify register error message is {errorExpected}")
    public SignupLoginPage verifyRegisterErrorMessage(String errorExpected){
        String errorActual = registerError.textContent();
        LogsManager.info("Verifying register error message. Actual: " + errorActual
                + ", Expected: " + errorExpected);
        Assertions.assertEquals(errorExpected, errorActual,
                "Register error message is not as expected. Expected: "
                        + errorExpected + ", Actual: " + errorActual);
        return this;
    }

    @Step("Verify Email field validation message appears")
    public SignupLoginPage verifyEmailFieldValidationMessageAppears(){
        // Check if the email field has HTML5 validation triggered
        String validationMessage = (String) signupEmail.evaluate("el => el.validationMessage");
        Assertions.assertNotNull(validationMessage,
                "Email field validation message did not appear.");
        Assertions.assertFalse(validationMessage.isEmpty(),
                "Email field validation message is empty.");
        return this;
    }

    @Step("Verify Name field validation message appears")
    public SignupLoginPage verifyNameFieldValidationMessageAppears(){
        // Check if the name field has HTML5 validation triggered
        String validationMessage = (String) signupName.evaluate("el => el.validationMessage");
        Assertions.assertNotNull(validationMessage,
                "Name field validation message did not appear.");
        Assertions.assertFalse(validationMessage.isEmpty(),
                "Name field validation message is empty.");
        return this;
    }
}