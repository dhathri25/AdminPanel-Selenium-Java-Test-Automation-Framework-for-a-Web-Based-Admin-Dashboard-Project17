
package com.srm.framework.tests;

import com.srm.framework.core.BaseTest;
import com.srm.framework.pages.FramePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameTests extends BaseTest {

    @Test(description = "Switch into an iframe and verify content is accessible")
    public void shouldAccessIframeContent() {
        FramePage framePage = new FramePage().openIframesPage();

        Assert.assertTrue(framePage.canReadTextInsideIframe(),
                "Iframe body content was not accessible.");
        Assert.assertTrue(framePage.canInteractInsideIframe(),
                "Expected iframe content was not interactable.");
    }

    @Test(description = "Switch into a frame page and verify nested frame content is accessible")
    public void shouldAccessNestedFrameContent() {
        FramePage framePage = new FramePage().openFramesPage();

        Assert.assertTrue(framePage.canReadTextInsideNestedFrame(),
                "Nested frame content was not accessible.");
    }

    @Test(description = "Return to the default content and verify the main page is accessible again")
    public void shouldReturnToDefaultContent() {
        FramePage framePage = new FramePage().openIframesPage();

        framePage.canReadTextInsideIframe();

        Assert.assertTrue(framePage.mainPageIsAccessibleAfterSwitchBack(),
                "Main page content was not accessible after switching back from the frame.");
    }
}
