package automationexercises.tests.ui;

import automationexercises.apis.UserManagementAPI;
import automationexercises.base.BaseTest;
import automationexercises.base.PlaywrightManager;
import automationexercises.pages.*;
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

@Epic("Automation Exercise Website")
@Feature("E2E Scenarios")
@Owner("Aya")
public class E2eScenarioOne extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();
    JsonReader testData = new JsonReader("checkout-data");

    // Tests

    // Place order scenario 1: register & login before checkout

    @Test(groups = {"scenario_1"})
    @Story("Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Register a new account for the order")
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

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"registerNewAccountTC"})
    @Story("Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login to the newly created account")
    public void loginToAccountTC() {
        new SignupLoginPage(page).navigate()
                .enterLoginEmail(testData.getJsonData("email") + timeStamp + "@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton().navigationBar
                .verifyUserLabel(testData.getJsonData("name"));
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"loginToAccountTC", "registerNewAccountTC"})
    @Story("Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add a product to the cart")
    public void addProductToCartTC() {
        new ProductsPage(page)
                .navigate()
                .clickOnCategoryChoice(
                        testData.getJsonData("categoryWomen.categoryName"))
                .clickOnWomenTopsChoice()
                .verifyCategoryProductsAreDisplayed(
                        testData.getJsonData("categoryWomen.categoryLabel"))
                .clickOnAddToCart(testData.getJsonData("product.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnContinueShopping()
                .clickOnAddToCart(testData.getJsonData("product2.name"))
                .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                .clickOnViewCart()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"))
                .verifyProductDetailsInCart(
                        testData.getJsonData("product2.name"),
                        testData.getJsonData("product2.price"),
                        testData.getJsonData("product2.quantity"),
                        testData.getJsonData("product2.total"));
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartTC", "loginToAccountTC",
            "registerNewAccountTC"})
    @Story("Place Order: Register before Checkout")
    @Description("Remove product from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void removeProductFromCartTC() {
        new CartPage(page)
                .navigate()
                .removeProductFromCart(testData.getJsonData("product2.name"))
                .verifyProductIsRemovedFromCart(testData.getJsonData("product2.name"));
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"removeProductFromCartTC", "addProductToCartTC", "loginToAccountTC",
                    "registerNewAccountTC"})
    @Story("Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Proceed to checkout")
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

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"removeProductFromCartTC", "checkoutTC",
                    "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Complete payment")
    public void paymentTC() {
        new CheckoutPage(page)
                .clickPlaceOrderBtn()
                .fillCardInfo(
                        testData.getJsonData("card.cardName"),
                        testData.getJsonData("card.cardNumber"),
                        testData.getJsonData("card.cvc"),
                        testData.getJsonData("card.exMonth"),
                        testData.getJsonData("card.exYear"))
                .clickPayAndConfirmBtn()
                .verifySuccessMessage()
                .clickDownloadInvoiceBtn();
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"removeProductFromCartTC", "paymentTC", "checkoutTC",
                    "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place Order: Register before Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the downloaded invoice file")
    public void verifyInvoiceFileTC() {
        new PaymentPage(page)
                .clickDownloadInvoiceBtn()
                .verifyDownloadInvoiceFile(
                        testData.getJsonData("invoiceName"));
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"removeProductFromCartTC", "verifyInvoiceFileTC", "paymentTC", "checkoutTC",
                    "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place Order: Register before Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete the user account")
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
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
