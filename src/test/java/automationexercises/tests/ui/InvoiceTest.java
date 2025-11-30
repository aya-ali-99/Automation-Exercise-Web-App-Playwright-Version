package automationexercises.tests.ui;


import automationexercises.apis.UserManagementAPI;
import automationexercises.base.BaseTest;
import automationexercises.pages.*;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;


@Epic("Automation Exercise ")
@Feature("UI Invoice Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class InvoiceTest extends BaseTest {
    String timeStamp = TimeManager.getSimpleTimestamp();
    JsonReader testData = new JsonReader("checkout-data");

    // Tests

    // Place order scenario 1: register & login before checkout

    @Test(groups = {"scenario_1"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Register new account")
    public void registerNewAccountTC(){
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        (testData.getJsonData("email")+ timeStamp +"@gmail.com"),
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
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Login to account")
    public void loginToAccountTC(){
        new SignupLoginPage(page).navigate()
                .enterLoginEmail(testData.getJsonData("email")+ timeStamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserLabel(testData.getJsonData("name"));
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Add product to cart")
    public void addProductToCartTC(){
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


    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Checkout")
    public void checkoutTC(){
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
                        testData.getJsonData("mobileNumber")
                )
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
                        testData.getJsonData("mobileNumber")
                );

    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Payment")
    public void paymentTC(){
        new CheckoutPage(page)
                .clickPlaceOrderBtn()
                .fillCardInfo(
                        testData.getJsonData("card.cardName"),
                        testData.getJsonData("card.cardNumber"),
                        testData.getJsonData("card.cvc"),
                        testData.getJsonData("card.exMonth"),
                        testData.getJsonData("card.exYear")
                )
                .clickPayAndConfirmBtn()
                .verifySuccessMessage()
                .clickDownloadInvoiceBtn();
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"paymentTC", "checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Verify invoice file")
    public void verifyInvoiceFileTC(){
        new PaymentPage(page)
                .clickDownloadInvoiceBtn()
                .verifyDownloadInvoiceFile(
                        testData.getJsonData("invoiceName")
                );
    }

    @Test(groups = {"scenario_1"},
            dependsOnMethods = {"verifyInvoiceFileTC", "paymentTC", "checkoutTC", "addProductToCartTC", "loginToAccountTC", "registerNewAccountTC"})
    @Story("Place order scenario 1: register & login before checkout")
    @Description("Delete account")
    public void deleteAccountTC(){
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email")+ timeStamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserIsDeletedSuccessfully();
    }


    // Place order scenario 2: register & login before checkout


    @Test(groups = {"scenario_2"}, dependsOnGroups = {"scenario_1"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Add product to cart")
    public void addProductToCartBeforeLoginTC(){
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


    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Checkout without login")
    public void checkoutBeforeLoginTC(){
        new CartPage(page)
                .navigate()
                .clickOnProceedToCheckoutButtonWithoutLogin()
                .clickOnRegisterLoginButton()
                .verifySignupLabelVisible();
    }


    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Sign up with valid data")
    public void validSignUpTC(){
        timeStamp+=1;
        new SignupLoginPage(page)
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ timeStamp+"@gmail.com")
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
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Checkout after registering")
    public void checkoutAfterLoginTC(){
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
                        testData.getJsonData("mobileNumber")
                )
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
                        testData.getJsonData("mobileNumber")
                );
    }


    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC", "validSignUpTC", "checkoutAfterLoginTC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Payment")
    public void paymentScenario2TC(){
        new CheckoutPage(page)
                .clickPlaceOrderBtn()
                .fillCardInfo(
                        testData.getJsonData("card.cardName"),
                        testData.getJsonData("card.cardNumber"),
                        testData.getJsonData("card.cvc"),
                        testData.getJsonData("card.exMonth"),
                        testData.getJsonData("card.exYear")
                )
                .clickPayAndConfirmBtn()
                .verifySuccessMessage()
                .clickDownloadInvoiceBtn();
    }

    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC"
                    , "validSignUpTC", "checkoutAfterLoginTC", "paymentScenario2TC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Verify invoice file")
    public void verifyInvoiceFileScenario2TC(){
        new PaymentPage(page)
                .clickDownloadInvoiceBtn()
                .verifyDownloadInvoiceFile(
                        testData.getJsonData("invoiceName")
                );
    }

    @Test(groups = {"scenario_2"},
            dependsOnGroups = {"scenario_1"},
            dependsOnMethods = {"addProductToCartBeforeLoginTC", "checkoutBeforeLoginTC"
                    , "validSignUpTC", "checkoutAfterLoginTC", "paymentScenario2TC"
    , "verifyInvoiceFileScenario2TC"})
    @Story("Place order scenario 2: register & login after checkout")
    @Description("Delete account")
    public void deleteAccountScenario2TC(){
        new UserManagementAPI().deleteUserAccount(
                        testData.getJsonData("email")+ timeStamp+"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserIsDeletedSuccessfully();
    }

}
