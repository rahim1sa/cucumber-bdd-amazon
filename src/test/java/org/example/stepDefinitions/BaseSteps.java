package org.example.stepDefinitions;

import org.example.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseSteps {
    protected final WebDriver webDriver =
            new WebDriverFactory().getWebDriver();

    @BeforeMethod
    protected void setWebDriver(){webDriver.manage().window().maximize();}
    @AfterMethod
    public void quite() {
        webDriver.close();
        webDriver.quit();
    }
    public void goBack() {webDriver.navigate().back();}
}
