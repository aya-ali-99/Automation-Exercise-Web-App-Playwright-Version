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
public class E2eScenarioTwo extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();
    JsonReader testData = new JsonReader("checkout-data");

    // Tests


    // Place order scenario 2: register & login before checkout

    @Test(groups = {"scenario_2"})
    @Story("Place Order: Register after Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add product to cart before login")
    public void addProductToCartBeforeLoginTC() {
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

    @Test(groups = {"scenario_2"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC"})
    @Story("Place Order: Register after Checkout")
    @Description("Remove product from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void removeProductFromCartTC() {
        new CartPage(page)
                .navigate()
                .removeProductFromCart(testData.getJsonData("product2.name"))
                .verifyProductIsRemovedFromCart(testData.getJsonData("product2.name"));
    }

    @Test(groups = {"scenario_2"},
            dependsOnMethods = {"removeProductFromCartTC",
                    "addProductToCartBeforeLoginTC"})
    @Story("Place Order: Register after Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Proceed to checkout without login")
    public void checkoutBeforeLoginTC() {
        new CartPage(page)
                .clickOnProceedToCheckoutButtonWithoutLogin()
                .clickOnRegisterLoginButton()
                .verifySignupLabelVisible();
    }

    @Test(groups = {"scenario_2"},
            dependsOnMethods = {"removeProductFromCartTC",
                    "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC"})
    @Story("Place Order: Register after Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Sign up with valid data during checkout")
    public void validSignUpTC() {
        timeStamp += 1;
        new SignupLoginPage(page)
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email") + timeStamp + "@gmail.com")
                .clickSignupButton();
        new SignupPage(page)
                .enterPassword(testData.getJsonData("password"))
                .selectBirtDate(testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"))
                .selectNewsletter()
                .selectSpecialOffers()
                .selectTitle(testData.getJsonData("titleFemale"))
                .enterFirstName(testData.getJsonData("firstName"))
                .enterLastName(testData.getJsonData("lastName"))
                .enterCompany(testData.getJsonData("companyName"))
                .enterAddress1(testData.getJsonData("address1"))
                .enterAddress2(testData.getJsonData("address2"))
                .selectCountry(testData.getJsonData("country"))
                .enterState(testData.getJsonData("state"))
                .enterCity(testData.getJsonData("city"))
                .enterZipcode(testData.getJsonData("zipcode"))
                .enterMobileNumber(testData.getJsonData("mobileNumber"))
                .clickCreateAccountButton()
                .verifyAccountCreatedLabel();

    }

    @Test(groups = {"scenario_2"},
            dependsOnMethods = {"removeProductFromCartTC",
                    "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC"})
    @Story("Place Order: Register after Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Proceed to checkout after registering")
    public void checkoutAfterLoginTC() {
        new CartPage(page)
                .navigate()
                .verifyProductDetailsInCart(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"),
                        testData.getJsonData("product.quantity"),
                        testData.getJsonData("product.total"))
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

    @Test(groups = {"scenario_2"},
            dependsOnMethods = {"removeProductFromCartTC",
                    "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC",
                    "checkoutAfterLoginTC"})
    @Story("Place Order: Register after Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Complete payment for scenario 2")
    public void paymentScenario2TC() {
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

    @Test(groups = {"scenario_2"},
            dependsOnMethods = {"removeProductFromCartTC",
                    "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC",
                    "checkoutAfterLoginTC", "paymentScenario2TC"})
    @Story("Place Order: Register after Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify invoice file for scenario 2")
    public void verifyInvoiceFileScenario2TC() {
        new PaymentPage(page)
                .clickDownloadInvoiceBtn()
                .verifyDownloadInvoiceFile(
                        testData.getJsonData("invoiceName"));
    }

    @Test(groups = {"scenario_2"},
            dependsOnMethods = {"removeProductFromCartTC",
                    "addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC",
                    "checkoutAfterLoginTC", "paymentScenario2TC", "verifyInvoiceFileScenario2TC"})
    @Story("Place Order: Register after Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete account for scenario 2")
    public void deleteAccountScenario2TC() {
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
