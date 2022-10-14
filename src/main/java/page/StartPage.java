package page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends BasePage {

    public StartPage(){
        super();
    }

    @FindBy(xpath = "//button[.//span[text()='Войти в Почту']]")
    WebElement loginButton;

    @Step("Нажать кнопку Войти в Почту")
    public LoginPage pressLoginButton(){
        loginButton.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
}
