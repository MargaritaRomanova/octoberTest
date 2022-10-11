package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver (Browser browser){
        switch (browser){
            case CHROME:
                File file = new File(PropertyReader.getChromeDriverPath());
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBinary(PropertyReader.getChromePath());
                driver = new ChromeDriver(chromeOptions);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }
}
