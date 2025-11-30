package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.pages.components.SubscriptionComponent;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Automation Exercise ")
@Feature("Subscription")
@Owner("Aya")
public class SubscriptionTest extends BaseTest {
        JsonReader testData = new JsonReader("subscription-data");

        // Tests
        @Test
        @Story("Subscription with valid Email")
        @Description("Verify that a user can successfully subscribe to the newsletter from Home Page")
        @Severity(SeverityLevel.NORMAL)
        public void SubscriptionFromHomePageTC() {
                new NavigationBarComponent(page)
                                .clickOnHomeButton();
                new SubscriptionComponent(page)
                                .fillSubscriptionForm(testData.getJsonData("email"))
                                .clickSubmitSubscriptionBtn()
                                .verifySuccessMessage(testData.getJsonData("message"));
        }

        // Tests
        @Test
        @Story("Subscription with valid Email")
        @Description("Verify that a user can successfully subscribe to the newsletter from Cart Page")
        @Severity(SeverityLevel.NORMAL)
        public void SubscriptionFromCartPageTC() {
                new NavigationBarComponent(page)
                                .clickOnCartButton();
                new SubscriptionComponent(page)
                                .fillSubscriptionForm(testData.getJsonData("email"))
                                .clickSubmitSubscriptionBtn()
                                .verifySuccessMessage(testData.getJsonData("message"));
        }

}
