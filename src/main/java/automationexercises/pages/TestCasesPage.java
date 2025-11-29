package automationexercises.pages;

import automationexercises.utils.dataReader.PropertyReader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;

public class TestCasesPage {

    private Page page;

    // Variables
    private final String testCasesEndPoint = "/test_cases";

    // Locators
    private final Locator testCasesLabel;

    public TestCasesPage(Page page) {
        this.page = page;

        // Initialize locators
        this.testCasesLabel = page.locator(".col-sm-9 > h2 > b");
    }

    // Actions
    public TestCasesPage navigate(){
        page.navigate(PropertyReader.getProperty("baseUrlWeb") + testCasesEndPoint);
        return this;
    }

    // Validations
    public TestCasesPage verifyTestCasesPage(){
        Assertions.assertEquals("Test Cases",
                        testCasesLabel.textContent(),
                        "Test Cases page is not displayed");
        return this;
    }

}
