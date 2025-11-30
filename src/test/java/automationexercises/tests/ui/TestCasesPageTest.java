package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.components.NavigationBarComponent;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;


@Epic("Automation Exercise ")
@Feature("UI Test Cases Page")
@Owner("Aya")
public class TestCasesPageTest extends BaseTest {

    @Test
    @Story("Test cases page access")
    @Description("Verify that a user can successfully navigate to the Test Cases page")
    @Severity(SeverityLevel.CRITICAL)
    public void TestCasesPageTest(){
        new NavigationBarComponent(page)
                .clickOnTestCasesButton()
                .verifyTestCasesPage();
    }


}
