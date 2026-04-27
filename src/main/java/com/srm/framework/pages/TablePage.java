
package com.srm.framework.pages;

import com.srm.framework.core.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TablePage extends BasePage {

    private static final By TABLE = By.tagName("table");
    private static final By HEADER_CELLS = By.cssSelector("table th");
    private static final By BODY_ROWS = By.xpath("//table//tr[td]");
    public TablePage openStaticTable() {
        openRelativeUrl("tables.static.path");
        return this;
    }

    public TablePage openDynamicTable() {
        openRelativeUrl("tables.dynamic.path");
        return this;
    }

    public List<String> getHeaders() {
        return waitForAllVisible(HEADER_CELLS).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isBlank())
                .toList();
    }

    public String getFirstRowText() {
        return waitForAllVisible(BODY_ROWS).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isBlank())
                .findFirst()
                .orElse("");
    }

    public boolean isTableDisplayed() {
        return isDisplayed(TABLE);
    }

    public TablePage refreshDynamicTable(String caption, String jsonData) {
        executeScript("createTableFromJson(arguments[0], arguments[1], 'automation-table');", jsonData, caption);
        return this;
    }

    public boolean pageContainsTableText(String expectedText) {
        return pageContainsText(expectedText);
    }
}
