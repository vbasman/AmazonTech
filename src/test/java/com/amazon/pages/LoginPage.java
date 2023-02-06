package com.amazon.pages;

import com.amazon.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "sp-cc-accept")
    public WebElement cookiesAccept;

    @FindBy(id = "nav-link-accountList")
    public WebElement navLink;

    @FindBy(id = "ap_email")
    public WebElement userEmail;

    @FindBy(id = "continue")
    public WebElement continueBtn;

    @FindBy(id = "ap_password")
    public WebElement password;

    @FindBy(id = "signInSubmit")
    public WebElement signInBtn;
}

