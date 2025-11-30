package automationexercises.pages;

import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Paths;

public class ContactUsPage {

    private Page page;

    // Variables
    private final String contactUsEndPoint = "/contact_us";

    // Locators
    private final Locator contactUsLabel;
    private final Locator nameInput;
    private final Locator emailInput;
    private final Locator subjectInput;
    private final Locator messageInput;
    private final Locator submitBtn;
    private final Locator chooseFileBtn;
    private final Locator successMessage;
    private final Locator homeBtn;
    private final Locator getInTouchLabel;

    public ContactUsPage(Page page) {
        this.page = page;

        // Initialize locators
        this.contactUsLabel = page.locator(".col-sm-12 > .title");
        this.nameInput = page.locator("[name='name']");
        this.emailInput = page.locator("[name='email']");
        this.subjectInput = page.locator("[name='subject']");
        this.messageInput = page.locator("[name='message']");
        this.submitBtn = page.locator("[data-qa=\"submit-button\"]");
        this.chooseFileBtn = page.locator("[name='upload_file']");
        this.successMessage = page.locator(".contact-form .alert-success");
        this.homeBtn = page.locator("#form-section > .btn-success");
        this.getInTouchLabel = page.locator("//h2[text()='Get In Touch']");
    }

    // Actions

    @Step("Navigate to Contact Us page")
    public ContactUsPage navigate() {
        page.navigate(PropertyReader.getProperty("baseUrlWeb") + contactUsEndPoint);
        LogsManager.info("Navigated to Contact Us page");
        return this;
    }

    @Step("Fill Contact Us form")
    public ContactUsPage fillContactUsForm(String name, String email, String subject, String message){
        nameInput.fill(name);
        emailInput.fill(email);
        subjectInput.fill(subject);
        messageInput.fill(message);
        return this;
    }

    @Step("Upload file in Contact Us page")
    public ContactUsPage uploadFileInContactUsPage(String filePath){
        chooseFileBtn.setInputFiles(Paths.get(filePath));
        return this;
    }

    @Step("Click on submit button")
    public ContactUsPage clickSubmitBtn(){

        page.onceDialog(dialog -> {
            LogsManager.info("Alert appeared with message: " + dialog.message());
            dialog.accept();
            LogsManager.info("Accepted alert dialog");
        });
        submitBtn.click();
        return this;
    }

    @Step("Click on home button")
    public ContactUsPage clickHomeBtn(){
        homeBtn.click();
        return this;
    }

    // Validations

    @Step("Verify Contact Us page")
    public ContactUsPage verifyContactUsPage(){
        Assertions.assertFalse(contactUsLabel.isVisible(),
                "Contact Us page is not displayed");
        return this;
    }

    @Step("Verify success message")
    public ContactUsPage verifySuccessMessage(){
        Assertions.assertEquals("Success! Your details have been submitted successfully."
                , successMessage.textContent()
                ,"Success message is not displayed");
        return this;
    }
}


