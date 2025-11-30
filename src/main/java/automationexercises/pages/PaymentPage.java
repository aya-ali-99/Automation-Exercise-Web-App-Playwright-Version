package automationexercises.pages;

import automationexercises.utils.dataReader.PropertyReader;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class PaymentPage {

    private Page page;

    // Variables
    private final String paymentEndPoint = "/payment";

    // Locators
    private final Locator nameOnCard;
    private final Locator cardNumber;
    private final Locator cvc;
    private final Locator expiryMonth;
    private final Locator expiryYear;
    private final Locator payAndConfirmBtn;
    private final Locator successMessage;
    private final Locator downloadInvoiceBtn;


    public PaymentPage(Page page) {
        this.page = page;

        // Initialize locators
        this.nameOnCard = page.locator("[name='name_on_card']");
        this.cardNumber = page.locator("[name='card_number']");
        this.cvc = page.locator("[name='cvc']");
        this.expiryMonth = page.locator("[name='expiry_month']");
        this.expiryYear = page.locator("[name='expiry_year']");
        this.payAndConfirmBtn = page.locator("#submit");
        this.successMessage = page.locator("#form > div > div > div > p").first();
        this.downloadInvoiceBtn = page.locator("[class='btn btn-default check_out']");
    }

    // Actions

    @Step("Fill card information")
    public PaymentPage fillCardInfo(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        this.nameOnCard.fill(nameOnCard);
        this.cardNumber.fill(cardNumber);
        this.cvc.fill(cvc);
        this.expiryMonth.fill(expiryMonth);
        this.expiryYear.fill(expiryYear);
        return this;
    }

    @Step("Click on pay and confirm button")
    public PaymentPage clickPayAndConfirmBtn() {
        payAndConfirmBtn.click();
        return this;
    }

    @Step("Click on download invoice button")
    public PaymentPage clickDownloadInvoiceBtn() {
        downloadInvoiceBtn.click();
        return this;
    }

    // Validations
    @Step("Verify success message")
    public PaymentPage verifySuccessMessage() {
        String actualSucessMsg = successMessage.textContent();
        Assertions.assertEquals("Congratulations! Your order has been confirmed!",
                actualSucessMsg,
                "Success message does not match");
        return this;
    }

    @Step("Verify download invoice file")
    public PaymentPage verifyDownloadInvoiceFile(String invoiceName) {
        // Wait for the download to start
        Download download = page.waitForDownload(() -> {
            // Perform the action that initiates download
            downloadInvoiceBtn.click();
        });

        String userDir = System.getProperty("user.dir");
        String downloadPath = userDir + "\\src\\test\\resources\\downloads";

        download.saveAs(Paths.get(downloadPath ,"invoice.txt"));
        return this;
    }
}