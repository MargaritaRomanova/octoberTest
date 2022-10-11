package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.PropertyReader;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    private static final String MAIL_BUTTON = "//button[.//span[text()= 'Почта']]";
    private static final String LOGIN_NAME = "login";
    private static final String BUTTON = "//button[.//span[text()= 'Войти']]";

    public void pressMailButtonIfNotPressed() {
        WebElement mailButton = driver.findElement(By.xpath(MAIL_BUTTON));
        if (mailButton.getAttribute("aria-pressed").equals("false")) {
            mailButton.click();
        }
    }

    private void fillLoginField() {
        WebElement loginField = driver.findElement(By.name(LOGIN_NAME));
        if (!loginField.getText().isEmpty()){
            loginField.clear();
        }
        loginField.click();
        loginField.sendKeys(PropertyReader.getLogin());
        Assert.assertFalse(loginField.getAttribute("value").isEmpty(), "login field is empty");
        Assert.assertEquals(loginField.getAttribute("value"),
                PropertyReader.getLogin(), "login field is filled with a different value");
    }

    private void pressSignButton(){
        WebElement signButton = driver.findElement(By.xpath(BUTTON));
        signButton.click();
    }

    @Step("Авторизация пользователя на странице Логин")
    public PasswordPage authLogin(){
        fillLoginField();
        pressSignButton();
        saveScreenshot(driver);
        return new PasswordPage();
    }
}
