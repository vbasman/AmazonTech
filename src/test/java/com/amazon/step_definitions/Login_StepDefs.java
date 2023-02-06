
package com.amazon.step_definitions;

import com.amazon.pages.LoginPage;
import com.amazon.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class Login_StepDefs {
    LoginPage loginPage = new LoginPage();

    public Login_StepDefs() {
    }

    @Given("I am a user of amazon.com")
    public void i_am_a_user_of_amazon_com() {
    }

    @When("I log in using valid credentials Then I should be logged in")
    public void i_log_in_using_valid_credentials_then_i_should_be_logged_in() {
    }

    @When("I log in using invalid credentials Then I should not be logged in")
    public void i_log_in_using_invalid_credentials_then_i_should_not_be_logged_in() {
        Driver.getDriver().get("https://www.amazon.de/");
        this.loginPage.cookiesAccept.click();
        this.loginPage.navLink.click();
        this.loginPage.userEmail.sendKeys(new CharSequence[]{"jeffbezos@gmail.com"});
        this.loginPage.continueBtn.click();
        this.loginPage.password.sendKeys(new CharSequence[]{"12345"});
        this.loginPage.signInBtn.click();
    }
}