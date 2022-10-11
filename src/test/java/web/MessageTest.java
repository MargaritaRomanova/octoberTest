package web;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
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
        LoginPage authPage = new LoginPage();
        authPage.pressMailButtonIfNotPressed();
        PasswordPage passwordPage = authPage.authLogin();
        passwordPage.authPassword();
        Thread.sleep(5000);
        MailPage mailPage = passwordPage.navigateTo("https://mail.yandex.ru");
        mailPage.addSearchFilterAndPressSearchBtn("Simbirsoft Тестовое задание");
        Thread.sleep(2000);
        int countMessageBeforeNewMessage = mailPage.getCountMessage();
        NewMessagePage newMessagePage = mailPage.pressWriteBtn();
        newMessagePage.writeAndSendMessage("simbirsoft.testtestov@yandex.ru",
                "Simbirsoft Тестовое задание. Романова", countMessageBeforeNewMessage + "message(s)");
        Thread.sleep(5000);
        mailPage = newMessagePage.refreshPage();
        mailPage.pressSearchButton();
        int countMessageAfterNewMessage = mailPage.getCountMessage();
        assertEquals(countMessageBeforeNewMessage + 1, countMessageAfterNewMessage,
                "message did not send");
    }

    @AfterClass
    static public void tearDown() {
        driver.quit();
    }
}
