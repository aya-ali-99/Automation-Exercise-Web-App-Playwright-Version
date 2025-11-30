package automationexercises.pages;

import automationexercises.pages.components.NavigationBarComponent;
import automationexercises.utils.logs.LogsManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.testng.Assert;

public class SignupPage {

    private Page page;

    // Locators
    private final Locator titleMr;
    private final Locator titleMrs;
    private final Locator name;
    private final Locator email;
    private final Locator password;
    // Birthdate
    private final Locator day;
    private final Locator month;
    private final Locator year;
    // Checkboxes
    private final Locator newsletter;
    private final Locator specialOffers;
    // Personal Information
    private final Locator firstName;
    private final Locator lastName;
    private final Locator company;
    private final Locator address1;
    private final Locator address2;
    private final Locator country;
    private final Locator state;
    private final Locator city;
    private final Locator zipcode;
    private final Locator mobileNumber;
    private final Locator createAccountButton;
    private final Locator accountCreatedLabel;
    private final Locator continueButton;

    public SignupPage(Page page) {
        this.page = page;

        // Locators initialization
        this.titleMr = page.locator("#uniform-id_gender1");
        this.titleMrs = page.locator("#uniform-id_gender2");
        this.name = page.locator("[data-qa='name']");
        this.email = page.locator("[data-qa='email']");
        this.password = page.locator("#password");

        // Birthdate
        this.day = page.locator("#days");
        this.month = page.locator("#months");
        this.year = page.locator("#years");

        // Checkboxes
        this.newsletter = page.locator("#newsletter");
        this.specialOffers = page.locator("#optin");

        // Personal Information
        this.firstName = page.locator("#first_name");
        this.lastName = page.locator("#last_name");
        this.company = page.locator("#company");
        this.address1 = page.locator("#address1");
        this.address2 = page.locator("#address2");
        this.country = page.locator("#country");
        this.state = page.locator("#state");
        this.city = page.locator("#city");
        this.zipcode = page.locator("#zipcode");
        this.mobileNumber = page.locator("#mobile_number");

        this.createAccountButton = page.locator("[data-qa='create-account']");
        this.accountCreatedLabel = page.locator("p").first();
        this.continueButton = page.locator("[data-qa='continue-button']");
    }

    // Actions

    @Step("Select title {title}")
    public SignupPage selectTitle(String title) {
        switch (title.toLowerCase()) {
            case "mr":
                titleMr.click();
                break;
            case "mrs":
                titleMrs.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid title: " + title);
        }
        LogsManager.info("Selected title: " + title);
        return this;
    }

    @Step("Enter name {username}")
    public SignupPage enterName(String username) {
        name.fill(username);
        return this;
    }

    @Step("Enter email {userEmail}")
    public SignupPage enterEmail(String userEmail) {
        email.fill(userEmail);
        return this;
    }

    @Step("Enter password {userPassword}")
    public SignupPage enterPassword(String userPassword) {
        password.fill(userPassword);
        return this;
    }

    @Step("Select Birthdate {birthDay} | {birthMonth} | {birthYear}")
    public SignupPage selectBirtDate(String birthDay, String birthMonth, String birthYear) {
        day.selectOption(birthDay);
        month.selectOption(birthMonth);
        year.selectOption(birthYear);
        LogsManager.info("Selected birthdate: " + birthDay + "/" + birthMonth + "/" + birthYear);
        return this;
    }

    @Step("Select Newsletter")
    public SignupPage selectNewsletter() {
        newsletter.check();
        return this;
    }

    @Step("Select Special Offers")
    public SignupPage selectSpecialOffers() {
        specialOffers.check();
        return this;
    }

    @Step("Enter First Name {firstNameValue}")
    public SignupPage enterFirstName(String firstNameValue) {
        firstName.fill(firstNameValue);
        return this;
    }

    @Step("Enter Last Name {lastNameValue}")
    public SignupPage enterLastName(String lastNameValue) {
        lastName.fill(lastNameValue);
        return this;
    }

    @Step("Enter Company {companyValue}")
    public SignupPage enterCompany(String companyValue) {
        company.fill(companyValue);
        return this;
    }

    @Step("Enter Address 1 {address1Value}")
    public SignupPage enterAddress1(String address1Value) {
        address1.fill(address1Value);
        return this;
    }

    @Step("Enter Address 2 {address2Value}")
    public SignupPage enterAddress2(String address2Value) {
        address2.fill(address2Value);
        return this;
    }

    @Step("Select Country {countryValue}")
    public SignupPage selectCountry(String countryValue) {
        country.selectOption(countryValue);
        LogsManager.info("Selected country: " + countryValue);
        return this;
    }

    @Step("Enter State {stateValue}")
    public SignupPage enterState(String stateValue) {
        state.fill(stateValue);
        return this;
    }

    @Step("Enter City {cityValue}")
    public SignupPage enterCity(String cityValue) {
        city.fill(cityValue);
        return this;
    }

    @Step("Enter Zipcode {zipcodeValue}")
    public SignupPage enterZipcode(String zipcodeValue) {
        zipcode.fill(zipcodeValue);
        return this;
    }

    @Step("Enter Mobile Number {mobileNumberValue}")
    public SignupPage enterMobileNumber(String mobileNumberValue) {
        mobileNumber.fill(mobileNumberValue);
        return this;
    }

    @Step("Click Create Account Button")
    public SignupPage clickCreateAccountButton() {
        createAccountButton.click();
        return this;
    }

    @Step("Click Continue Button")
    public NavigationBarComponent clickContinueButton() {
        continueButton.click();
        return new NavigationBarComponent(page);
    }

    // Validations

    @Step("Verify Account Created Label is visible")
    public SignupPage verifyAccountCreatedLabel() {
        Assert.assertTrue(accountCreatedLabel.isVisible(),
                "The Account Created label is not visible.");
        return this;
    }

    @Step("Verify Account Not Created")
    public SignupPage verifyAccountNotCreated() {
        Assert.assertTrue(accountCreatedLabel.isHidden(),
                "The Account Created label should not be visible but it is.");
        return this;
    }
}