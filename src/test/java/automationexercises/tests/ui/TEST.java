package automationexercises.tests.ui;

import automationexercises.apis.UserManagementAPI;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.TimeManager;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;

import automationexercises.base.BaseTest;

import automationexercises.pages.*;
import org.junit.jupiter.api.Test;


public class TEST extends BaseTest {

    String timestamp = TimeManager.getSimpleTimestamp();
    JsonReader testData = new JsonReader("register-data");

    @Test
    @Story("UI Verifications")
    @Description("")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aya")
    public void testCaseForVerifications() {
//        new NavigationBarComponent(page).navigate()
//                .clickOnHomeButton()
//                .verifyHomePage();
    }


}
