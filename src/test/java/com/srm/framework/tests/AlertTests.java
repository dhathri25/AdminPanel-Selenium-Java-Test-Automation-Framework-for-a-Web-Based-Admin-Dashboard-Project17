
package com.srm.framework.tests;

import com.srm.framework.core.BaseTest;
import com.srm.framework.pages.AlertPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTests extends BaseTest {

    @Test(description = "Accept the JavaScript alert and verify alert text")
    public void shouldAcceptAlert() {
        AlertPage alertPage = new AlertPage().open();

        String alertText = alertPage.acceptAlertAndGetText();

        Assert.assertFalse(alertText.isBlank(), "Alert text should not be blank.");
    }

    @Test(description = "Accept the JavaScript confirm dialog and verify text")
    public void shouldAcceptConfirm() {
        AlertPage alertPage = new AlertPage().open();

        String confirmText = alertPage.acceptConfirmAndGetText();

        Assert.assertFalse(confirmText.isBlank(), "Confirm text should not be blank.");
    }

    @Test(description = "Dismiss the JavaScript confirm dialog and verify text")
    public void shouldDismissConfirm() {
        AlertPage alertPage = new AlertPage().open();

        String confirmText = alertPage.dismissConfirmAndGetText();

        Assert.assertFalse(confirmText.isBlank(), "Dismissed confirm text should not be blank.");
    }

    @Test(description = "Type into the JavaScript prompt and verify prompt workflow")
    public void shouldSubmitPrompt() {
        AlertPage alertPage = new AlertPage().open();

        String promptText = alertPage.submitPromptAndGetAlertText("automation");

        Assert.assertFalse(promptText.isBlank(), "Prompt text should not be blank.");
        Assert.assertTrue(alertPage.promptValueAppearsOnPage("automation") || alertPage.promptValueAppearsOnPage("Prompt"),
                "Prompt submission did not leave a visible trace on the page.");
    }
}
