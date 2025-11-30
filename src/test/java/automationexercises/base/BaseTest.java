package automationexercises.base;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import automationexercises.listeners.TestNGListeners;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners({ TestNGListeners.class })
public class BaseTest {
    protected Page page;

    @BeforeSuite
    @Step("Initialize Playwright browser")
    public void setUpAll() {
        PlaywrightManager.start();
    }

    @BeforeMethod
    @Step("Create new page context for test")
    public void setUp(ITestResult result) {
        page = PlaywrightManager.getPage();

        // Add test metadata
        String testName = result.getName();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Allure.parameter("Test Name", testName);
        // Allure.parameter("Test Start Time", timestamp);
        // Allure.parameter("Test Class",
        // testInfo.getTestClass().map(Class::getName).orElse("Unknown"));
        // Allure.parameter("Test Method", testInfo.getTestMethod().map(m ->
        // m.getName()).orElse("Unknown"));
    }

    @AfterMethod
    @Step("Close page and capture final state")
    public void tearDown(ITestResult result) {
        if (page != null) {
            // Capture screenshot before closing (useful for both passed and failed tests)
            try {
                byte[] screenshot = page.screenshot();
                String screenshotName = result.getName() + " - Final State";
                Allure.addAttachment(screenshotName, "image/png",
                        new ByteArrayInputStream(screenshot), ".png");
            } catch (Exception e) {
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }

            // Add completion timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Allure.parameter("Test End Time", timestamp);

            PlaywrightManager.closePage();
        }
    }

    @AfterSuite
    @Step("Stop Playwright and cleanup resources")
    public void tearDownAll() {
        PlaywrightManager.stop();
    }

    public Page getPage() {
        return page;
    }
}
