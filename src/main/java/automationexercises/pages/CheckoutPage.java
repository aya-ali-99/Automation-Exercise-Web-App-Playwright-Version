package automationexercises.pages;

import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public class CheckoutPage {

        private Page page;

        // Variables
        private final String checkoutEndPoint = "/checkout";

        // Locators
        private final Locator commentField;
        private final Locator placeOrderBtn;

        // Delivery address locators
        private final Locator deliveryName;
        private final Locator deliveryCompany;
        private final Locator deliveryAddress1;
        private final Locator deliveryAddress2;
        private final Locator deliveryCityStateZip;
        private final Locator deliveryCountry;
        private final Locator deliveryPhone;

        // Billing address locators
        private final Locator billingName;
        private final Locator billingCompany;
        private final Locator billingAddress1;
        private final Locator billingAddress2;
        private final Locator billingCityStateZip;
        private final Locator billingCountry;
        private final Locator billingPhone;

        public CheckoutPage(Page page) {
                this.page = page;

                // Initialize general locators
                this.commentField = page.locator(".form-control");
                this.placeOrderBtn = page.locator("[href='/payment']");

                // Initialize delivery address locators
                this.deliveryName = page.locator(
                                "//ul[@id='address_delivery']/li[@class='address_firstname address_lastname']");
                this.deliveryCompany = page.locator(
                                "//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][1]");
                this.deliveryAddress1 = page.locator(
                                "//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][2]");
                this.deliveryAddress2 = page.locator(
                                "//ul[@id='address_delivery']/li[@class='address_address1 address_address2'][3]");
                this.deliveryCityStateZip = page.locator(
                        "#address_delivery [class=\"address_city address_state_name address_postcode\"]");
                this.deliveryCountry = page.locator("//ul[@id='address_delivery']/li[@class='address_country_name']");
                this.deliveryPhone = page.locator("//ul[@id='address_delivery']/li[@class='address_phone']");

                // Initialize billing address locators
                this.billingName = page
                                .locator("//ul[@id='address_invoice']/li[@class='address_firstname address_lastname']");
                this.billingCompany = page.locator(
                                "//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][1]");
                this.billingAddress1 = page.locator(
                                "//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][2]");
                this.billingAddress2 = page.locator(
                                "//ul[@id='address_invoice']/li[@class='address_address1 address_address2'][3]");
                this.billingCityStateZip = page.locator(
                                "#address_invoice [class=\"address_city address_state_name address_postcode\"]");
                this.billingCountry = page.locator("//ul[@id='address_invoice']/li[@class='address_country_name']");
                this.billingPhone = page.locator("//ul[@id='address_invoice']/li[@class='address_phone']");
        }

        // Actions

        @Step("Navigate to checkout page")
        public CheckoutPage navigate() {
                page.navigate(PropertyReader.getProperty("baseUrlWeb") + checkoutEndPoint);
                LogsManager.info("Navigated to checkout page");
                return this;
        }

        @Step("Enter comment")
        public CheckoutPage enterComment(String comment) {
                commentField.fill(comment);
                return this;
        }

        @Step("Click on place order button")
        public PaymentPage clickPlaceOrderBtn() {
                placeOrderBtn.click();
                return new PaymentPage(page);
        }

        // Validations
        @Step("Verify delivery address")
        public CheckoutPage verifyDeliveryAddress(String title, String fName, String lName, String company,
                        String address1, String address2,
                        String city, String state, String zip, String country, String phone) {
                Assert.assertEquals(deliveryName.innerText(), (title + ". " + fName + " " + lName),
                                " Delivery Name is not matched");
                Assert.assertEquals(deliveryCompany.innerText(), company, " Delivery Company is not matched");
                Assert.assertEquals(deliveryAddress1.innerText(), address1, " Delivery Address1 is not matched");
                Assert.assertEquals(deliveryAddress2.innerText(), address2, " Delivery Address2 is not matched");
                Assert.assertEquals(deliveryCityStateZip.innerText(), city+" "+state+" "+zip,
                                " Delivery City State Zip is not matched");
                Assert.assertEquals(deliveryCountry.innerText(), country, " Delivery Country is not matched");
                Assert.assertEquals(deliveryPhone.innerText(), phone, " Delivery Phone is not matched");
                return this;
        }

        @Step("Verify billing address")
        public CheckoutPage verifyBillingAddress(String title, String fName, String lName, String company,
                        String address1, String address2,
                        String city, String state, String zip, String country, String phone) {
                Assert.assertEquals(billingName.innerText(), (title + ". " + fName + " " + lName),
                                " Billing Name is not matched");
                Assert.assertEquals(billingCompany.innerText(), company, " Billing Company is not matched");
                Assert.assertEquals(billingAddress1.innerText(), address1, " Billing Address1 is not matched");
                Assert.assertEquals(billingAddress2.innerText(), address2, " Billing Address2 is not matched");
                Assert.assertEquals(billingCityStateZip.innerText(), city+" "+state+" "+zip,
                                " Billing City State Zip is not matched");
                Assert.assertEquals(billingCountry.innerText(), country, " Billing Country is not matched");
                Assert.assertEquals(billingPhone.innerText(), phone, " Billing Phone is not matched");
                return this;
        }
}
