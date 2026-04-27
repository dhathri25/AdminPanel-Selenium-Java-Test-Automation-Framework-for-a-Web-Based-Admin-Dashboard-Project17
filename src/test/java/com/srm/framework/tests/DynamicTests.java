

package com.srm.framework.tests;

import com.srm.framework.core.BaseTest;
import com.srm.framework.pages.DynamicPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicTests extends BaseTest {

    @Test(description = "Interact with a dynamically refreshed button and verify output becomes visible")
    public void shouldInteractWithDynamicButton() {
        DynamicPage dynamicPage = new DynamicPage().openDynamicButtonsPage();

        dynamicPage.clickDynamicButton();

        Assert.assertTrue(dynamicPage.actionResultIsVisible(),
                "Dynamic action result was not visible after clicking the button.");
    }

    @Test(description = "Trigger the JavaScript redirect and verify the destination URL changes")
    public void shouldFollowRedirect() {
        DynamicPage dynamicPage = new DynamicPage().openRedirectPage();

        dynamicPage.triggerRedirect();

        Assert.assertTrue(dynamicPage.landedOnExpectedRedirectPath(),
                "The browser did not land on the expected redirect destination.");
    }
}
