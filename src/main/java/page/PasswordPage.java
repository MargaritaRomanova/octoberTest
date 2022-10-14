package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import util.PropertyReader;

public class PasswordPage extends BasePage {

    public PasswordPage() {
        super();
    }

    @FindBy(name = "passwd")
    WebElement passwordField;

    @FindBy(xpath = "//button[.//span[text()= 'Войти']]")
    WebElement signButton;

    private void fillPasswordField() {
        passwordField.sendKeys(PropertyReader.getPassword());
        Assert.assertFalse(passwordField.getAttribute("value").isEmpty(), "password field is empty");
        Assert.assertEquals(passwordField.getAttribute("value"),
                PropertyReader.getPassword(), "password field is filled with a different value");
    }

    private void pressSignButton(){
        signButton.click();
    }

    @Step("Авторизация пользователя на странице Пароль")
    public MailPage authPassword(){
        fillPasswordField();
        pressSignButton();
        saveScreenshot(driver);
        return PageFactory.initElements(driver, MailPage.class);
    }
}
