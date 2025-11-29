package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.ProductsPage;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;


@Epic("Automation Exercise ")
@Feature("UI Products Management")
@Story("Products Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Aya")
public class ProductsTest extends BaseTest {
    JsonReader testData = new JsonReader("products-data");

    // Tests

    @Test
    @Description("Verify searching for product without logging in")
    public void searchForProductWithoutLoginTC(){
        new ProductsPage(page)
                .navigate()
                .searchForProduct(testData.getJsonData("searchedProduct.name"))
                .validateProductDetails(
                        testData.getJsonData("searchedProduct.name"),
                        testData.getJsonData("searchedProduct.price"));
    }


    @Test
    @Description("Verify product added to cart without logging in")
    public void addProductToCartWithoutLoginTC(){
        new ProductsPage(page)
                .navigate()
                .clickOnAddToCart(testData.getJsonData("product1.name"))
                .validateItemAddedLabel(
                        testData.getJsonData("messages.cartAdded"));
    }

    @Test
    @Description("Verify category products are displayed")
    public void chooseKidsDressCategoryTC(){
        new ProductsPage(page)
                .navigate()
                .clickOnCategoryChoice(
                        testData.getJsonData("categoryKids.categoryName")
                )
                .clickOnKidsDressChoice()
                .verifyCategoryProductsAreDisplayed(
                        testData.getJsonData("categoryKids.categoryLabel")
                )
                .clickOnCategoryChoice(
                        testData.getJsonData("categoryWomen.categoryName")
                )
                .clickOnWomenTopsChoice()
                .verifyCategoryProductsAreDisplayed(
                        testData.getJsonData("categoryWomen.categoryLabel")
                );
    }


}
