package web;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.*;
import util.DriverFactory;
import util.PropertyReader;

import static org.testng.Assert.assertEquals;

public class MessageTest {

    private static WebDriver driver;

    @BeforeClass
    static public void setUp() {
        driver = DriverFactory.getDriver(PropertyReader.getBrowser());
        driver.get(PropertyReader.getUrl());
        BasePage.setDriver(driver);
    }

    @Test
    @Description(value = "verify to send message")
    public void loginTest() throws InterruptedException {
        StartPage startPage = PageFactory.initElements(driver, StartPage.class);
        LoginPage authPage = startPage.pressLoginButton();
        authPage.pressMailButtonIfNotPressed();
        PasswordPage passwordPage = authPage.authLogin();
        MailPage mailPage = passwordPage.authPassword();
        mailPage.addSearchFilterAndPressSearchBtn("Simbirsoft Тестовое задание");
        int countMessageBeforeNewMessage = mailPage.getCountMessage();
        NewMessagePage newMessagePage = mailPage.pressWriteBtn();
        newMessagePage.writeAndSendMessage("simbirsoft.testtestov@yandex.ru",
                "Simbirsoft Тестовое задание. Романова", countMessageBeforeNewMessage + " message(s)");
        Thread.sleep(5000);
        mailPage = newMessagePage.refreshPage();
        int countMessageAfterNewMessage = mailPage.getCountMessage();
        assertEquals(countMessageBeforeNewMessage + 1, countMessageAfterNewMessage,
                "message did not send");
    }

    @AfterClass
    static public void tearDown() {
        driver.quit();
    }
}
