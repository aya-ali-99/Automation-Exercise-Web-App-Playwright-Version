package automationexercises.tests.ui;

import automationexercises.base.BaseTest;
import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.dataReader.JsonReader;
import io.qameta.allure.*;

import org.junit.jupiter.api.Test;


@Epic("Automation Exercise ")
@Feature("UI Contact Us Form")
@Owner("Aya")
public class ContactUsTest extends BaseTest {
    JsonReader testData = new JsonReader("contactus-data");

    // Tests
    @Test
    @Story("Valid Contact form submission")
    @Description("Verify that a user can successfully submit the contact form with a file attachment")
    @Severity(SeverityLevel.NORMAL)
    public void ContactUsFromWithUploadingFileTC(){
        new NavigationBarComponent(page)
                .clickOnContactUsButton()
                .fillContactUsForm(testData.getJsonData("name"),
                        testData.getJsonData("email"),
                        testData.getJsonData("subject"),
                        testData.getJsonData("message"))
                .uploadFileInContactUsPage(testData.getJsonData("filePath"))
                .clickSubmitBtn()
                .verifySuccessMessage();

    }
    @Test
    @Story("Valid Contact form submission")
    @Description("Verify that a user can successfully submit the contact form without a file attachment")
    @Severity(SeverityLevel.NORMAL)
    public void ContactUsFromWithoutUploadingFileTC(){
        new NavigationBarComponent(page)
                .clickOnContactUsButton()
                .fillContactUsForm(testData.getJsonData("name"),
                        testData.getJsonData("email"),
                        testData.getJsonData("subject"),
                        testData.getJsonData("message"))
                .clickSubmitBtn()
                .verifySuccessMessage();
    }

}
