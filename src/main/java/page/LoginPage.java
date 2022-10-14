package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import util.PropertyReader;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    @FindBy(xpath = "//button[.//span[text()= 'Почта']]")
    WebElement mailButton;

    @FindBy(name = "login")
    WebElement loginField;

    @FindBy(xpath = "//button[.//span[text()= 'Войти']]")
    WebElement signButton;

    public void pressMailButtonIfNotPressed() {
        if (mailButton.getAttribute("aria-pressed").equals("false")) {
            mailButton.click();
        }
    }

    private void fillLoginField() {
        if (!loginField.getText().isEmpty()) {
            loginField.clear();
        }
        loginField.sendKeys(PropertyReader.getLogin());
        Assert.assertFalse(loginField.getAttribute("value").isEmpty(), "login field is empty");
        Assert.assertEquals(loginField.getAttribute("value"),
                PropertyReader.getLogin(), "login field is filled with a different value");
    }

    private void pressSignButton() {
        signButton.click();
    }

    @Step("Авторизация пользователя на странице Логин")
    public PasswordPage authLogin() {
        fillLoginField();
        pressSignButton();
        saveScreenshot(driver);
        return PageFactory.initElements(driver, PasswordPage.class);
    }
}
