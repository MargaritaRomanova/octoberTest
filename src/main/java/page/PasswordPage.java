package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import util.PropertyReader;

public class PasswordPage extends BasePage {

    public PasswordPage() {
        super();
    }

    private static final String PASSWORD_NAME = "passwd";
    private static final String BUTTON = "//button[.//span[text()= 'Войти']]";

    private void fillPasswordField() {
        WebElement passwordField = driver.findElement(By.name(PASSWORD_NAME));
        passwordField.click();
        passwordField.sendKeys(PropertyReader.getPassword());
        Assert.assertFalse(passwordField.getAttribute("value").isEmpty(), "password field is empty");
        Assert.assertEquals(passwordField.getAttribute("value"),
                PropertyReader.getPassword(), "password field is filled with a different value");
    }

    private void pressSignButton(){
        WebElement signButton = driver.findElement(By.xpath(BUTTON));
        signButton.click();
    }

    @Step("Авторизация пользователя на странице Пароль")
    public void authPassword(){
        fillPasswordField();
        pressSignButton();
        saveScreenshot(driver);
    }

    @Step("Переход на страницу")
    public MailPage navigateTo(String url){
        driver.navigate().to(url);
        saveScreenshot(driver);
        return new MailPage();
    }
}
