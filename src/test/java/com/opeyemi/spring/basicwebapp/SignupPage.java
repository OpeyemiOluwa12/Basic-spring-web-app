package com.opeyemi.spring.basicwebapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement userNameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "success-msg")
    private WebElement successMessageField;

    @FindBy(id = "error-msg")
    private WebElement errorMessageField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void signUp(String firstName, String lastName, String userName, String password){
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.userNameField.sendKeys(userName);
        this.passwordField.sendKeys(password);
        this.submitButton.click();

    }
}
