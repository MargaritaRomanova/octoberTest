package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailPage extends BasePage {

    public MailPage() {
        super();
    }

    private static final String FOLDERS_BUTTON = "//button[.//span[text()='Папки']]";
    private static final String TEXT_INCOMING_IN_FOLDERS = "//div[./span[@class='menu__text' and text()='Входящие']]";
    private static final String MORE_BUTTON = "//button[.//span[text()='Ещё']]";
    private static final String TOPIC_IN_MORE_BUTTON = "//div[./span[@class='menu__text']/span[text()='В теме письма']]";
    private static final String SEARCH_FIELD = "//input[@placeholder='Поиск']";
    private static final String SEARCH_BUTTON = "//button[.//span[text()= 'Найти']]";
    private static final String EXTENDED_SEARCH_BUTTON = "//button[@title='расширенный поиск']";
    private static final String INFO = "//span[@class='mail-MessagesSearchInfo-Title']";
    private static final String SEARCH_INFO = "//div[@class='mail-MessagesSearchInfo_Summary']";
    private static final String WRITE_MESSAGES_BUTTON = "//a[@role='button'][.//span[text()='Написать']]";

    private void addFilterIncoming() {
        WebElement foldersBtn = driver.findElement(By.xpath(FOLDERS_BUTTON));
        foldersBtn.click();
        WebElement textIncomingInFolders = driver.findElement(By.xpath(TEXT_INCOMING_IN_FOLDERS));
        textIncomingInFolders.click();
    }

    private void addFilterTopic() {
        WebElement moreBtn = driver.findElement(By.xpath(MORE_BUTTON));
        moreBtn.click();
        WebElement topicInMoreBtn = driver.findElement(By.xpath(TOPIC_IN_MORE_BUTTON));
        topicInMoreBtn.click();
    }

    @Step("Добавление фильтра Входящие и Добавление фильтра По теме")
    public void addSearchFilterAndPressSearchBtn(String title) {
        WebElement searchField = driver.findElement(By.xpath(SEARCH_FIELD));
        searchField.click();
        searchField.sendKeys(title);

        WebElement extendedSearchBtn = driver.findElement(By.xpath(EXTENDED_SEARCH_BUTTON));
        extendedSearchBtn.click();
        addFilterIncoming();
        addFilterTopic();

        WebElement searchBtn = driver.findElement(By.xpath(SEARCH_BUTTON));
        searchBtn.click();
        saveScreenshot(driver);
    }

    @Step("Нажатие кнопки Написать")
    public NewMessagePage pressWriteBtn() {
        WebElement writeMessageBtn = driver.findElement(By.xpath(WRITE_MESSAGES_BUTTON));
        writeMessageBtn.click();
        saveScreenshot(driver);
        return new NewMessagePage();
    }

    public int getCountMessage() {
        int count = 0;
        WebElement searchInfo = driver.findElement(By.xpath(SEARCH_INFO));
        if (!searchInfo.getText().contains("ничего не нашлось. Показаны результаты поиска во всех папках.")) {
            WebElement info = driver.findElement(By.xpath(INFO));
            if (info.getText().contains("одно письмо")) {
                count = 1;
            } else {
                Pattern p = Pattern.compile("(\\d+)");
                Matcher matcher = p.matcher(info.getText());
                if (matcher.find()) {
                    count = Integer.parseInt(matcher.group());
                } else {
                    Assert.fail("can not find count of messages");
                }
            }
        }
        return count;
    }

    @Step("Нажатие кнопки Поиск")
    public void pressSearchButton() {
        WebElement searchBtn = driver.findElement(By.xpath(SEARCH_BUTTON));
        if (!searchBtn.getText().isEmpty()) {
            searchBtn.click();
            saveScreenshot(driver);
        } else {
            Assert.fail("need fill search field");
        }
    }
}
