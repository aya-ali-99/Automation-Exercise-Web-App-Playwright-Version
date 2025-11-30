package automationexercises.pages.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class SubscriptionComponent {

    private Page page;

    // Locators
    private final Locator subscriptionEmail;
    private final Locator submitSubscriptionBtn;
    private final Locator successMessage;

    public SubscriptionComponent(Page page) {
        this.page = page;

        // Initialize locators
        this.subscriptionEmail = page.locator("#susbscribe_email"); // Note: keeping the typo as it might be in the actual HTML
        this.submitSubscriptionBtn = page.locator("#subscribe");
        this.successMessage = page.locator("#success-subscribe > div");
    }

    // Actions
    @Step("Fill Subscription Form")
    public SubscriptionComponent fillSubscriptionForm(String email){
        subscriptionEmail.fill(email);
        return this;
    }

    @Step("Click on submit subscription button")
    public SubscriptionComponent clickSubmitSubscriptionBtn(){
        submitSubscriptionBtn.click();
        return this;
    }

    // Validations

    @Step("Verify subscription success message")
    public SubscriptionComponent verifySuccessMessage(String expectedMsg){
        Assertions.assertEquals(expectedMsg,
                successMessage.textContent(),
                "Subscription success message is not displayed");
        return this;
    }
}
