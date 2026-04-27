
package com.srm.framework.pages;

import com.srm.framework.config.ConfigReader;
import com.srm.framework.core.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private static final By PAGE_HEADING = By.tagName("h1");

    public HomePage open() {
        driver.get(ConfigReader.get("base.url"));
        return this;
    }

    public String getHeading() {
        return getText(PAGE_HEADING);
    }

    public boolean isPracticePagesLinkVisible() {
        return pageContainsText("Practice Pages");
    }
}
