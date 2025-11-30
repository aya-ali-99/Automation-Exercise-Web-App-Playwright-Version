package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.ProductsPage;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Automation Exercise ")
@Feature("UI Cart Management")
@Story("Cart Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class CartTest extends BaseTest {
        JsonReader testData = new JsonReader("cart-data");

        // Tests
        @Test
        @Description("Verify product details in cart without login")
        public void verifyProductDetailsInCartWithoutLogin() {
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

        @Test
        @Description("Verify product is removed successfully from cart")
        public void verifyProductIsRemovedSuccessfullyFromCart() {
                new ProductsPage(page)
                                .navigate()
                                .clickOnAddToCart(testData.getJsonData("product.name"))
                                .clickOnContinueShopping()
                                .clickOnAddToCart(testData.getJsonData("product2.name"))
                                .clickOnViewCart()
                                .verifyProductDetailsInCart(
                                                testData.getJsonData("product.name"),
                                                testData.getJsonData("product.price"),
                                                testData.getJsonData("product.quantity"),
                                                testData.getJsonData("product.total"))
                                .removeProductFromCart(testData.getJsonData("product.name"))
                                .verifyProductIsRemovedFromCart(testData.getJsonData("product.name"));
        }

}
