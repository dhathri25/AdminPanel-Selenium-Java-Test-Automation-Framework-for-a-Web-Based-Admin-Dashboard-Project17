
package com.srm.framework.core;

import com.srm.framework.config.ConfigReader;
import com.srm.framework.driver.DriverFactory;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final FluentWait<WebDriver> fluentWait;

    protected BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getTimeoutInSeconds()));
        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(ConfigReader.getTimeoutInSeconds()))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    protected void openRelativeUrl(String propertyKey) {
        driver.get(ConfigReader.get("base.url") + ConfigReader.get(propertyKey));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> waitForAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected Alert switchToAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String value) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected void selectByVisibleText(By locator, String visibleText) {
        new Select(waitForVisible(locator)).selectByVisibleText(visibleText);
    }

    protected List<String> getSelectedOptions(By locator) {
        return new Select(waitForVisible(locator)).getAllSelectedOptions()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    protected String getText(By locator) {
        return waitForVisible(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (RuntimeException exception) {
            return false;
        }
    }

    protected boolean pageContainsText(String expectedText) {
        return wait.until(driver -> driver.getPageSource().contains(expectedText));
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void switchToFrame(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void clickWithJavaScript(By locator) {
        WebElement element = waitForVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected Object executeScript(String script, Object... arguments) {
        return ((JavascriptExecutor) driver).executeScript(script, arguments);
    }
}
