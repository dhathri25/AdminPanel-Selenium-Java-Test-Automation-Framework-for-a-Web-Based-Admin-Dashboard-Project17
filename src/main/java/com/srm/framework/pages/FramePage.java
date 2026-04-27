
package com.srm.framework.pages;

import com.srm.framework.core.BasePage;
import org.openqa.selenium.By;

public class FramePage extends BasePage {

    private static final By IFRAME = By.tagName("iframe");
    private static final By FRAME = By.tagName("frame");
    private static final By FRAME_BODY = By.tagName("body");
    private static final By FRAME_BUTTON = By.cssSelector("button, input[type='button'], input[type='submit']");
    private static final By MAIN_HEADING = By.tagName("h1");

    public FramePage openIframesPage() {
        openRelativeUrl("frames.iframes.path");
        return this;
    }

    public FramePage openFramesPage() {
        openRelativeUrl("frames.nested.path");
        return this;
    }

    public boolean canReadTextInsideIframe() {
        switchToFrame(IFRAME);
        boolean visible = isDisplayed(FRAME_BODY);
        switchToDefaultContent();
        return visible;
    }

    public boolean canInteractInsideIframe() {
        switchToFrame(IFRAME);
        boolean interactionAvailable = isDisplayed(FRAME_BUTTON) || isDisplayed(FRAME_BODY);
        switchToDefaultContent();
        return interactionAvailable;
    }

    public boolean canReadTextInsideNestedFrame() {
        switchToFrame(FRAME);
        boolean visible = isDisplayed(FRAME_BODY);
        switchToDefaultContent();
        return visible;
    }

    public boolean mainPageIsAccessibleAfterSwitchBack() {
        switchToDefaultContent();
        return isDisplayed(MAIN_HEADING);
    }
}
