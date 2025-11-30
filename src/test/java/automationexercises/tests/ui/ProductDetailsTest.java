package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.ProductsPage;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;



@Epic("Automation Exercise ")
@Feature("UI Products Management")
@Story("Product Details")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class ProductDetailsTest extends BaseTest {
    JsonReader testData = new JsonReader("product-details-data");

    // Tests

    @Test
    @Description("Verify product details")
    public void verifyProductDetailsWithoutLoginTC(){
        new ProductsPage(page)
                .navigate()
                .clickOnViewProduct(testData.getJsonData("product.name"))
                .validateProductDetails(
                        testData.getJsonData("product.name"),
                        testData.getJsonData("product.price"));
    }


    @Test
    @Description("Verify product review success message")
    public void verifyProductReviewSuccessMessageWithoutLoginTC(){
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
    @Description("Verify product quantity in cart")
    public void verifyProductQuantityInCartWithoutLoginTC(){
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
