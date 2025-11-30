package automationexercises.tests.ui;

import automationexercises.apis.UserManagementAPI;
import automationexercises.base.BaseTest;
import automationexercises.pages.SignupLoginPage;

import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Automation Exercise ")
@Feature("UI User Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class LoginTest extends BaseTest {
        String timeStamp = TimeManager.getSimpleTimestamp();
        JsonReader testData = new JsonReader("login-data");

        // Tests

        @Test
        @Story("Valid User Login")
        @Description("Verify user can login with valid data")
        public void validLoginTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();
                new SignupLoginPage(page).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + timeStamp + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword"))
                                .clickLoginButton().navigationBar
                                .verifyUserLabel(testData.getJsonData("loginName"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("Invalid User Login")
        @Description("Verify user can't login with invalid email")
        public void inValidLoginUsingInvalidEmailTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();
                new SignupLoginPage(page).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword"))
                                .clickLoginButton()
                                .verifyLoginErrorMessage(testData.getJsonData("messages.error"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("Invalid User Login")
        @Description("Verify user can't login with invalid password")
        public void inValidLoginUsingInvalidPasswordTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();
                new SignupLoginPage(page).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword") + timeStamp)
                                .clickLoginButton()
                                .verifyLoginErrorMessage(testData.getJsonData("messages.error"));

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();
        }

        @Test
        @Story("User Logout")
        @Description("Verify user can logout")
        public void logoutTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("loginName"),
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"))
                                .verifyUserIsCreatedSuccessfully();

                new SignupLoginPage(page).navigate()
                                .enterLoginEmail(testData.getJsonData("loginEmail") + timeStamp + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("loginPassword"))
                                .clickLoginButton().navigationBar
                                .verifyUserLabel(testData.getJsonData("loginName"))
                                .clickOnLogoutButton().navigationBar
                                .verifyLogoutButtonNotVisible()
                                .verifySignupLabelVisible();

                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("loginEmail") + timeStamp + "@gmail.com",
                                testData.getJsonData("loginPassword"))
                                .verifyUserIsDeletedSuccessfully();

        }

}
