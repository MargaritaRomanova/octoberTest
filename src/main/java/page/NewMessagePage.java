package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewMessagePage extends BasePage {

    public NewMessagePage() {
        super();
    }

    private static final String ADDRESS_FIELD = "//div[@title='Кому']";
    private static final String TOPIC_FIELD = "//span[text()='Тема']/following::input";
    private static final String MESSAGE_FIELD = "//div[@title='Напишите что-нибудь']";
    private static final String SEND_MESSAGE_BUTTON = "//button[.//span[text()='Отправить']]";

    @Step("Заполнение полей для нового сообщения")
    public void writeAndSendMessage(String address, String topic, String message) {
        WebElement addressField = driver.findElement(By.xpath(ADDRESS_FIELD));
        addressField.sendKeys(address);

        WebElement topicField = driver.findElement(By.xpath(TOPIC_FIELD));
        topicField.sendKeys(topic);

        WebElement messageField = driver.findElement(By.xpath(MESSAGE_FIELD));
        messageField.sendKeys(message);

        WebElement sendMessageBtn = driver.findElement(By.xpath(SEND_MESSAGE_BUTTON));
        sendMessageBtn.click();
        saveScreenshot(driver);
    }

    @Step("Обновление страницы")
    public MailPage refreshPage() {
        refresh();
        saveScreenshot(driver);
        return new MailPage();
    }
}
