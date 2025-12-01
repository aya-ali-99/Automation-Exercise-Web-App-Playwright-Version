package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.components.NavigationBarComponent;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Automation Exercise Website")
@Feature("Test Cases Page")
@Owner("Aya")
public class TestCasesPageTest extends BaseTest {

    @Test
    @Story("Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a user can successfully navigate to the Test Cases page")
    public void TestCasesPageTest() {
        new NavigationBarComponent(page)
                .clickOnTestCasesButton()
                .verifyTestCasesPage();
    }

}
