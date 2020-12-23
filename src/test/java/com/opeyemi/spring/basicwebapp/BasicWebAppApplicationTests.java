package com.opeyemi.spring.basicwebapp;

import com.opeyemi.spring.basicwebapp.model.Messages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasicWebAppApplicationTests {

    @LocalServerPort
    public Integer port;

    public static WebDriver webDriver;

    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        webDriver.quit();
        webDriver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    void name() {
    }

    @Test
    public void testUerSignupLoginAndChat() {
        String username = "OpeyemiOluwa";
        String password = "12345";
        String message = "Hello dear";

        webDriver.get(baseURL+"/signup");
        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.signUp("Opeyemi", "Idris", username, password);

        webDriver.get(baseURL+"/login");
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(username, password);

        ChatPage chatPage = new ChatPage(webDriver);
        chatPage.sendChatMessage(message);

        Messages messages = chatPage.getFirstMessage();

       assertEquals(username, messages.getUsername());
       assertEquals(message, messages.getMessageText());
    }

}
