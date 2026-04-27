
package com.srm.framework.pages;

import com.srm.framework.core.BasePage;
import org.openqa.selenium.By;

public class DynamicPage extends BasePage {

    private static final By REDIRECT_TRIGGER = By.xpath("//*[self::a or self::button][contains(normalize-space(),'2 seconds')]");
    private String redirectStartUrl;

    public DynamicPage openDynamicButtonsPage() {
        openRelativeUrl("dynamic.buttons.path");
        return this;
    }

    public DynamicPage openRedirectPage() {
        openRelativeUrl("dynamic.redirects.path");
        return this;
    }

    public DynamicPage clickDynamicButton() {
        executeScript(
                "const candidates = [...document.querySelectorAll('button,input[type=\"button\"],input[type=\"submit\"]')];"
                        + "const target = candidates.find(el => el.offsetParent !== null && !el.disabled);"
                        + "if(target){ target.click(); return true; } return false;");
        return this;
    }

    public boolean actionResultIsVisible() {
        return wait.until(driver -> !driver.getPageSource().isBlank());
    }

    public DynamicPage triggerRedirect() {
        redirectStartUrl = getCurrentUrl();
        clickWithJavaScript(REDIRECT_TRIGGER);
        return this;
    }

    public boolean landedOnExpectedRedirectPath() {
        return wait.until(driver -> redirectStartUrl != null && !driver.getCurrentUrl().equals(redirectStartUrl));
    }
}
