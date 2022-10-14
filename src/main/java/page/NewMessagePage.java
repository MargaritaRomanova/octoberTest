package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewMessagePage extends BasePage {

    public NewMessagePage() {
        super();
    }

    @FindBy(xpath = "//div[@title='Кому']")
    WebElement addressField;

    @FindBy(xpath = "//span[text()='Тема']/following::input")
    WebElement topicField;

    @FindBy(xpath = "//div[@title='Напишите что-нибудь']")
    WebElement messageField;

    @FindBy(xpath = "//button[.//div[@class='ComposeSendButton-Text']]")
    WebElement sendMessageBtn;

    @Step("Заполнение полей и отправка нового сообщения")
    public void writeAndSendMessage(String address, String topic, String message) {
        addressField.sendKeys(address);
        topicField.sendKeys(topic);
        messageField.sendKeys(message);
        sendMessageBtn.click();
        saveScreenshot(driver);
    }

    @Step("Обновление страницы")
    public MailPage refreshPage() {
        refresh();
        saveScreenshot(driver);
        return PageFactory.initElements(driver, MailPage.class);
    }
}
