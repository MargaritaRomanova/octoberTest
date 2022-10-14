package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailPage extends BasePage {

    public MailPage() {
        super();
    }

    @FindBy(xpath = "//input[@placeholder='Поиск']")
    WebElement searchField;

    @FindBy(xpath = "//button[@title='расширенный поиск']")
    WebElement extendedSearchBtn;

    @FindBy(xpath = "//button[.//span[text()='Папки']]")
    WebElement foldersBtn;

    @FindBy(xpath = "//div[./span[@class='menu__text' and text()='Входящие']]")
    WebElement textIncomingInFolders;

    @FindBy(xpath = "//button[.//span[text()='Ещё']]")
    WebElement moreBtn;

    @FindBy(xpath = "//div[./span[@class='menu__text']/span[text()='В теме письма']]")
    WebElement topicInMoreBtn;

    @FindBy(xpath = "//button[contains(@class, 'search-input__form-button')]")
    WebElement searchBtn;

    @FindBy(xpath = "//span[@class='mail-MessagesSearchInfo-Title']")
    WebElement info;

    @FindBy(xpath = "//div[@class='mail-MessagesSearchInfo_Summary']")
    WebElement searchInfo;

    @FindBy(xpath = "//a[contains(@class, 'ComposeButton')]")
    WebElement writeMessageBtn;

    @Step("Добавление фильтров и Нажатие кнопки Поиск")
    public void addSearchFilterAndPressSearchBtn(String title) {
        searchField.click();
        searchField.sendKeys(title);
        extendedSearchBtn.click();

        foldersBtn.click();
        textIncomingInFolders.click();

        moreBtn.click();
        topicInMoreBtn.click();

        searchBtn.click();
        saveScreenshot(driver);
    }

    @Step("Нажатие кнопки Написать")
    public NewMessagePage pressWriteBtn() {
        writeMessageBtn.click();
        saveScreenshot(driver);
        return PageFactory.initElements(driver, NewMessagePage.class);
    }

    public int getCountMessage() {
        int count = 0;
        if (!searchInfo.getText().contains("ничего не нашлось. Показаны результаты поиска во всех папках.")) {
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
}
