package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.ProductsPage;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Automation Exercise Website")
@Feature("Cart Management")
@Owner("Aya")
public class CartTest extends BaseTest {
        JsonReader testData = new JsonReader("cart-data");

        // Tests
        @Test
        @Story("View Cart")
        @Severity(SeverityLevel.CRITICAL)
        @Description("Verify that product details are correctly displayed in the cart without login")
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
        @Story("Remove from Cart")
        @Severity(SeverityLevel.CRITICAL)
        @Description("Verify that a product can be successfully removed from the cart")
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
