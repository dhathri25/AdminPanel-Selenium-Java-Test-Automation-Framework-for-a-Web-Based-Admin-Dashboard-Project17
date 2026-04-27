
package com.srm.framework.pages;

import com.srm.framework.core.BasePage;
import com.srm.framework.model.FormData;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormPage extends BasePage {

    private static final By USERNAME = By.name("username");
    private static final By PASSWORD = By.name("password");
    private static final By COMMENTS = By.name("comments");
    private static final By FILENAME = By.name("filename");
    private static final By CHECKBOX_GROUP = By.cssSelector("input[type='checkbox']");
    private static final By RADIO_GROUP = By.cssSelector("input[type='radio']");
    private static final By MULTI_SELECT = By.name("multipleselect[]");
    private static final By DROPDOWN = By.name("dropdown");
    private static final By SUBMIT_BUTTON = By.cssSelector("input[type='submit'], button[type='submit']");

    public FormPage openHtmlForm() {
        openRelativeUrl("forms.html.path");
        return this;
    }

    public FormPage submitValidHtmlForm(FormData formData) {
        type(USERNAME, formData.getUsername());
        type(PASSWORD, formData.getPassword());
        type(COMMENTS, formData.getComments());
        type(FILENAME, resolveUploadPath(formData.getFileName()));
        selectCheckboxes(formData.getCheckboxes());
        selectRadio(formData.getRadio());
        selectMultipleValues(formData.getMultiSelect());
        selectByVisibleText(DROPDOWN, formData.getDropdown());
        clickWithJavaScript(SUBMIT_BUTTON);
        return this;
    }

    public FormPage submitEmptyHtmlForm() {
        clickWithJavaScript(SUBMIT_BUTTON);
        return this;
    }

    public boolean outputContains(String expectedText) {
        return pageContainsText(expectedText);
    }

    private void selectCheckboxes(List<String> targetValues) {
        List<WebElement> checkboxes = driver.findElements(CHECKBOX_GROUP);
        for (WebElement checkbox : checkboxes) {
            String value = checkbox.getAttribute("value");
            if (targetValues.contains(value) && !checkbox.isSelected()) {
                executeScript(
                        "arguments[0].checked = true;"
                                + "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                        checkbox);
            }
        }
    }

    private void selectRadio(String targetValue) {
        for (WebElement radio : driver.findElements(RADIO_GROUP)) {
            if (targetValue.equalsIgnoreCase(radio.getAttribute("value"))) {
                executeScript(
                        "arguments[0].checked = true;"
                                + "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                        radio);
                return;
            }
        }
        throw new IllegalArgumentException("Radio value was not found: " + targetValue);
    }

    private void selectMultipleValues(List<String> targetValues) {
        Select select = new Select(waitForVisible(MULTI_SELECT));
        select.deselectAll();
        for (String targetValue : targetValues) {
            select.selectByVisibleText(targetValue);
        }
    }

    private String resolveUploadPath(String fileName) {
        try {
            return Path.of(FormPage.class.getClassLoader()
                    .getResource("testdata/uploads/" + fileName)
                    .toURI()).toAbsolutePath().toString();
        } catch (NullPointerException | URISyntaxException exception) {
            throw new IllegalStateException("Unable to resolve upload file: " + fileName, exception);
        }
    }
}
