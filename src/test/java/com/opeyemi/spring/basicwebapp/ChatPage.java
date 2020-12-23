package com.opeyemi.spring.basicwebapp;

import com.opeyemi.spring.basicwebapp.model.Messages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(className = "chatMessageUsername")
    private WebElement chatMessageUserName;

    @FindBy(className = "chatMessageText")
    private WebElement chatMessageText;


    public ChatPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);

    }

    public void sendChatMessage(String text) {
        this.messageField.sendKeys(text);
        this.submitButton.click();
    }

    public Messages getFirstMessage() {
        Messages messages = new Messages();
        messages.setUsername(chatMessageUserName.getText());
        messages.setMessageText(chatMessageText.getText());
        return messages;

    }

}
