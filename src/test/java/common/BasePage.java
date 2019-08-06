package common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.util.List;

public class BasePage extends PageGenerator {
    public AppiumDriver driver;
    WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 60, 5000);
    }

    //If we need we can use custom wait in BasePage and all page classes


    //Click Method by using JAVA Generics (You can use both By or Webelement)
    public <T> void click(T elementAttr) {
        WebElement webElement = getWebElement(elementAttr);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.click();
        Utils.sleep(2);
    }

    //Write Text by using JAVA Generics (You can use both By or Webelement)
    public <T> void writeText(T elementAttr, String text) {
        WebElement webElement = getWebElement(elementAttr);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
        Utils.sleep(2);
    }

    public <T> void clearText(T elementAttr) {
        WebElement webElement = getWebElement(elementAttr);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        Utils.sleep(2);
    }

    //Read Text by using JAVA Generics (You can use both By or Webelement)
    public <T> String readText(T elementAttr) {
        WebElement webElement = getWebElement(elementAttr);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    public <T> boolean isElementVisible(T elementAttr) {

        try {
            if (elementAttr instanceof WebElement) {
                return !ExpectedConditions.invisibilityOf((WebElement) elementAttr).apply(driver);
            } else {
                By by = (By) elementAttr;
                return !ExpectedConditions.invisibilityOfElementLocated(by).apply(driver);
            }
        } catch (Exception ignore) {
            return false;
        }

    }

    public <T> void waitForElementVisible(T elementAttr) {

        if (elementAttr instanceof WebElement) {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        } else {
            By by = (By) elementAttr;
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
    }

    public <T> void waitForElementVisible(T elementAttr, long timeOutInSeconds, long sleepInMillis) {

        WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutInSeconds, sleepInMillis);

        if (elementAttr instanceof WebElement) {
            webDriverWait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        } else {
            By by = (By) elementAttr;
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
    }


    public <T> void waitForElementDisappear(T elementAttr) {

        if (elementAttr instanceof WebElement) {
            wait.until(ExpectedConditions.invisibilityOf((WebElement) elementAttr));
        } else {
            By by = (By) elementAttr;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
    }

    public <T> void waitForElementDisappear(T elementAttr, long timeOutInSeconds, long sleepInMillis) {

        WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeOutInSeconds, sleepInMillis);

        if (elementAttr instanceof WebElement) {
            webDriverWait.until(ExpectedConditions.invisibilityOf((WebElement) elementAttr));
        } else {
            By by = (By) elementAttr;
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
    }

    public <T> void scrollTo(T elementAttr) {
        WebElement webElement = getWebElement(elementAttr);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        Utils.sleep(2);
    }

    //Close popup if exists
    public void handlePopup(By by) throws InterruptedException {
        List<WebElement> popup = driver.findElements(by);
        if (!popup.isEmpty()) {
            popup.get(0).click();
            Thread.sleep(200);
        }
    }

    protected <T> WebElement getWebElement(T elementAttr) {
        WebElement webElement;

        if (elementAttr instanceof IOSElement) {
            webElement = ((IOSElement) elementAttr);
        } else if (elementAttr instanceof AndroidElement) {
            webElement = ((AndroidElement) elementAttr);
        } else {
            By by = (By) elementAttr;
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            webElement = driver.findElement(by);
        }

        return webElement;
    }
}
