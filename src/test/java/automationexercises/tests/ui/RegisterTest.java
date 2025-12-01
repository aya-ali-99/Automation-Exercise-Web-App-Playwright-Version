package automationexercises.tests.ui;

import automationexercises.apis.UserManagementAPI;
import automationexercises.base.BaseTest;
import automationexercises.pages.SignupLoginPage;
import automationexercises.pages.SignupPage;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Automation Exercise Website")
@Feature("User Management")
@Owner("Aya")
public class RegisterTest extends BaseTest {
        String timeStamp = TimeManager.getSimpleTimestamp();
        JsonReader testData = new JsonReader("register-data");

        // Tests

        @Test
        @Story("User Registration")
        @Severity(SeverityLevel.CRITICAL)
        @Description("Verify that a new user can sign up with valid data")
        public void validSignUpTC() {
                new SignupLoginPage(page).navigate()
                                .enterSignupName(testData.getJsonData("signupName"))
                                .enterSignupEmail(testData.getJsonData("signupEmail") + timeStamp + "@gmail.com")
                                .clickSignupButton();
                new SignupPage(page)
                                .enterPassword(testData.getJsonData("signupPassword"))
                                .selectBirtDate(testData.getJsonData("day"),
                                                testData.getJsonData("month"),
                                                testData.getJsonData("year"))
                                .selectNewsletter()
                                .selectSpecialOffers()
                                .selectTitle(testData.getJsonData("titleFemale"))
                                .enterFirstName(testData.getJsonData("firstName"))
                                .enterLastName(testData.getJsonData("lastName"))
                                .enterCompany(testData.getJsonData("company"))
                                .enterAddress1(testData.getJsonData("address1"))
                                .enterAddress2(testData.getJsonData("address2"))
                                .selectCountry(testData.getJsonData("country"))
                                .enterState(testData.getJsonData("state"))
                                .enterCity(testData.getJsonData("city"))
                                .enterZipcode(testData.getJsonData("zipcode"))
                                .enterMobileNumber(testData.getJsonData("mobileNumber"))
                                .clickCreateAccountButton()
                                .verifyAccountCreatedLabel();

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("signupEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("signupPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("User Registration")
        @Severity(SeverityLevel.NORMAL)
        @Description("Verify that a user cannot sign up with an existing email address")
        public void verifyErrorMsgWhenAccountCreatedBefore() {
                // Precondition: Create a user account
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("signupName"),
                                (testData.getJsonData("signupEmail") + timeStamp + "@gmail.com"),
                                testData.getJsonData("signupPassword"),
                                testData.getJsonData("titleFemale"),
                                testData.getJsonData("day"),
                                testData.getJsonData("month"),
                                testData.getJsonData("year"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"),
                                testData.getJsonData("company"),
                                testData.getJsonData("address1"),
                                testData.getJsonData("address2"),
                                testData.getJsonData("country"),
                                testData.getJsonData("zipcode"),
                                testData.getJsonData("state"),
                                testData.getJsonData("city"),
                                testData.getJsonData("mobileNumber"))
                                .verifyUserIsCreatedSuccessfully();
                new SignupLoginPage(page).navigate()
                                .enterSignupName(testData.getJsonData("signupName"))
                                .enterSignupEmail(testData.getJsonData("signupEmail") + timeStamp + "@gmail.com")
                                .clickSignupButton()
                                .verifyRegisterErrorMessage(testData.getJsonData("messages.error"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("signupEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("signupPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("User Registration")
        @Severity(SeverityLevel.NORMAL)
        @Description("Verify that a user cannot sign up with an empty email address")
        public void invalidSignUpWithEmptyEmailTC() {
                new SignupLoginPage(page).navigate()
                                .enterSignupName(testData.getJsonData("signupName"))
                                .enterSignupEmail(testData.getJsonData("InvalidData.emptyEmail"))
                                .clickSignupButton()
                                .verifyEmailFieldValidationMessageAppears();
        }

        @Test
        @Story("User Registration")
        @Severity(SeverityLevel.NORMAL)
        @Description("Verify that a user cannot sign up with an empty name")
        public void invalidSignUpWithEmptyNameTC() {
                new SignupLoginPage(page).navigate()
                                .enterSignupName(testData.getJsonData("InvalidData.emptyName"))
                                .enterSignupEmail(testData.getJsonData("signupEmail") + timeStamp + "@gmail.com")
                                .clickSignupButton()
                                .verifyNameFieldValidationMessageAppears();
        }

        @Test
        @Story("User Registration")
        @Severity(SeverityLevel.NORMAL)
        @Description("Verify that a user cannot sign up with an invalid email format")
        public void invalidSignUpWithWrongEmailTC() {
                new SignupLoginPage(page).navigate()
                                .enterSignupName(testData.getJsonData("signupName"))
                                .enterSignupEmail(testData.getJsonData("InvalidData.wrongEmail"))
                                .clickSignupButton()
                                .verifyEmailFieldValidationMessageAppears();
        }

}
