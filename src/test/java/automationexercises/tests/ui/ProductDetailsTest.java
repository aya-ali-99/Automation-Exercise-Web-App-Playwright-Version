package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.ProductsPage;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Automation Exercise Website")
@Feature("Product Management")
@Owner("Aya")
public class ProductDetailsTest extends BaseTest {
        JsonReader testData = new JsonReader("product-details-data");

        // Tests

        @Test
        @Story("Product Details")
        @Severity(SeverityLevel.CRITICAL)
        @Description("Verify that product details are displayed correctly without login")
        public void verifyProductDetailsWithoutLoginTC() {
                new ProductsPage(page)
                                .navigate()
                                .clickOnViewProduct(testData.getJsonData("product.name"))
                                .validateProductDetails(
                                                testData.getJsonData("product.name"),
                                                testData.getJsonData("product.price"));
        }

        @Test
        @Story("Product Reviews")
        @Severity(SeverityLevel.NORMAL)
        @Description("Verify that a success message is displayed after submitting a review")
        public void verifyProductReviewSuccessMessageWithoutLoginTC() {
                new ProductsPage(page)
                                .navigate()
                                .clickOnViewProduct(testData.getJsonData("product.name"))
                                .addReview(
                                                testData.getJsonData("review.name"),
                                                testData.getJsonData("review.email"),
                                                testData.getJsonData("review.review"))
                                .validateReviewSuccessMessage(testData.getJsonData("messages.review"));
        }

        @Test
        @Story("Product Quantity")
        @Severity(SeverityLevel.CRITICAL)
        @Description("Verify that the correct product quantity is displayed in the cart")
        public void verifyProductQuantityInCartWithoutLoginTC() {
                new ProductsPage(page)
                                .navigate()
                                .clickOnViewProduct(testData.getJsonData("product.name"))
                                .addQuantity(testData.getJsonData("addQuantity"))
                                .clickOnAddToCartButton()
                                .clickOnViewCartButton()
                                .verifyProductQuantityInCart(testData.getJsonData("product.name"),
                                                testData.getJsonData("addQuantity"));
        }

}
