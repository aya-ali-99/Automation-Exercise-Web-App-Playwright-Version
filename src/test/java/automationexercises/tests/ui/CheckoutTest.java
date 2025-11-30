package automationexercises.tests.ui;

import automationexercises.apis.UserManagementAPI;
import automationexercises.base.BaseTest;
import automationexercises.base.PlaywrightManager;
import automationexercises.pages.CartPage;
import automationexercises.pages.ProductsPage;
import automationexercises.pages.SignupLoginPage;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Epic("Automation Exercise ")
@Feature("UI Checkout Management")
@Story("Checkout Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class CheckoutTest extends BaseTest {
        String timeStamp = TimeManager.getSimpleTimestamp();
        JsonReader testData = new JsonReader("checkout-data");

        // Tests

        @Test
        @Description("Verify user can register with valid data")
        public void registerNewAccountTC() {
                new UserManagementAPI().createRegisterUserAccount(
                                testData.getJsonData("name"),
                                (testData.getJsonData("email") + timeStamp + "@gmail.com"),
                                testData.getJsonData("password"),
                                testData.getJsonData("titleFemale"),
                                testData.getJsonData("day"),
                                testData.getJsonData("month"),
                                testData.getJsonData("year"),
                                testData.getJsonData("firstName"),
                                testData.getJsonData("lastName"),
                                testData.getJsonData("companyName"),
                                testData.getJsonData("address1"),
                                testData.getJsonData("address2"),
                                testData.getJsonData("country"),
                                testData.getJsonData("zipcode"),
                                testData.getJsonData("state"),
                                testData.getJsonData("city"),
                                testData.getJsonData("mobileNumber"))
                                .verifyUserIsCreatedSuccessfully();
        }

        @Test(dependsOnMethods = { "registerNewAccountTC" })
        @Description("Verify user can login with valid data")
        public void loginToAccountTC() {
                new SignupLoginPage(page).navigate()
                                .enterLoginEmail(testData.getJsonData("email") + timeStamp + "@gmail.com")
                                .enterLoginPassword(testData.getJsonData("password"))
                                .clickLoginButton().navigationBar
                                .verifyUserLabel(testData.getJsonData("name"));
        }

        @Test(dependsOnMethods = { "loginToAccountTC", "registerNewAccountTC" })
        @Description("Verify user can add product to cart")
        public void addProductToCartTC() {
                new ProductsPage(page)
                                .navigate()
                                .clickOnAddToCart(testData.getJsonData("product.name"))
                                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                                .clickOnViewCart()
                                .verifyProductDetailsInCart(
                                                testData.getJsonData("product.name"),
                                                testData.getJsonData("product.price"),
                                                testData.getJsonData("product.quantity"),
                                                testData.getJsonData("product.total"));
        }

        @Test(dependsOnMethods = { "addProductToCartTC", "loginToAccountTC",
                        "registerNewAccountTC" })
        @Description("Verify user can checkout with login")
        public void checkoutTC() {
                new CartPage(page)
                                .navigate()
                                .clickOnProceedToCheckoutButton()
                                .verifyDeliveryAddress(
                                                testData.getJsonData("titleFemale"),
                                                testData.getJsonData("firstName"),
                                                testData.getJsonData("lastName"),
                                                testData.getJsonData("companyName"),
                                                testData.getJsonData("address1"),
                                                testData.getJsonData("address2"),
                                                testData.getJsonData("city"),
                                                testData.getJsonData("state"),
                                                testData.getJsonData("zipcode"),
                                                testData.getJsonData("country"),
                                                testData.getJsonData("mobileNumber"))
                                .verifyBillingAddress(
                                                testData.getJsonData("titleFemale"),
                                                testData.getJsonData("firstName"),
                                                testData.getJsonData("lastName"),
                                                testData.getJsonData("companyName"),
                                                testData.getJsonData("address1"),
                                                testData.getJsonData("address2"),
                                                testData.getJsonData("city"),
                                                testData.getJsonData("state"),
                                                testData.getJsonData("zipcode"),
                                                testData.getJsonData("country"),
                                                testData.getJsonData("mobileNumber"));

        }

        @Test(dependsOnMethods = { "checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC" })
        @Description("Delete account")
        public void deleteAccountTC() {
                new UserManagementAPI().deleteUserAccount(
                                testData.getJsonData("email") + timeStamp + "@gmail.com",
                                testData.getJsonData("password"))
                                .verifyUserIsDeletedSuccessfully();
        }


        // Configurations
        @AfterMethod
        @Step("Close page and capture final state")
        public void tearDown(ITestResult result) {
                if (page != null) {
                        // Capture screenshot before closing (useful for both passed and failed tests)
                        try {
                                byte[] screenshot = page.screenshot();
                                String screenshotName = result.getName() + " - Final State";
                                Allure.addAttachment(screenshotName, "image/png",
                                        new ByteArrayInputStream(screenshot), ".png");
                        } catch (Exception e) {
                                System.err.println("Failed to capture screenshot: " + e.getMessage());
                        }

                        // Add completion timestamp
                        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        Allure.parameter("Test End Time", timestamp);
                }
        }

        @AfterSuite
        @Step("Stop Playwright and cleanup resources")
        public void tearDownAll() {
                PlaywrightManager.closePage();
                PlaywrightManager.stop();
        }
}
