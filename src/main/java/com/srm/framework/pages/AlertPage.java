
package com.srm.framework.pages;

import com.srm.framework.core.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class AlertPage extends BasePage {

    private static final By ALERT_BUTTON = By.xpath("//button[contains(normalize-space(),'Show alert box')] | //input[@value='Show alert box']");
    private static final By CONFIRM_BUTTON = By.xpath("//button[contains(normalize-space(),'Show confirm box')] | //input[@value='Show confirm box']");
    private static final By PROMPT_BUTTON = By.xpath("//button[contains(normalize-space(),'Show prompt box')] | //input[@value='Show prompt box']");

    public AlertPage open() {
        openRelativeUrl("alerts.path");
        return this;
    }

    public String acceptAlertAndGetText() {
        clickWithJavaScript(ALERT_BUTTON);
        Alert alert = switchToAlert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public String acceptConfirmAndGetText() {
        clickWithJavaScript(CONFIRM_BUTTON);
        Alert alert = switchToAlert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public String dismissConfirmAndGetText() {
        clickWithJavaScript(CONFIRM_BUTTON);
        Alert alert = switchToAlert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }

    public String submitPromptAndGetAlertText(String value) {
        clickWithJavaScript(PROMPT_BUTTON);
        Alert alert = switchToAlert();
        String text = alert.getText();
        alert.sendKeys(value);
        alert.accept();
        return text;
    }

    public boolean promptValueAppearsOnPage(String value) {
        return pageContainsText(value);
    }
}
