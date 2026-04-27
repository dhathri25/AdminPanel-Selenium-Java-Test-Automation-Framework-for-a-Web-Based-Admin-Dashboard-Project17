
package com.srm.framework.tests;

import com.srm.framework.core.BaseTest;
import com.srm.framework.model.FormData;
import com.srm.framework.pages.FormPage;
import com.srm.framework.utils.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FormTests extends BaseTest {

    @DataProvider(name = "htmlFormData")
    public Object[][] htmlFormData() {
        return JsonDataReader.getFormData("testdata/form-data.json");
    }

    @Test(dataProvider = "htmlFormData", description = "Submit the HTML form using multiple data sets")
    public void shouldSubmitHtmlFormWithValidData(FormData formData) {
        FormPage formPage = new FormPage().openHtmlForm();

        formPage.submitValidHtmlForm(formData);

        Assert.assertTrue(formPage.outputContains(formData.getUsername()),
                "Submitted username was not found in the response.");
        Assert.assertTrue(formPage.outputContains(formData.getDropdown()),
                "Selected dropdown value was not found in the response.");
        Assert.assertTrue(formPage.outputContains(formData.getRadio()),
                "Selected radio value was not found in the response.");
    }

    @Test(description = "Submit the HTML form with empty values and verify the response still renders")
    public void shouldHandleEmptyHtmlFormSubmission() {
        FormPage formPage = new FormPage().openHtmlForm();

        formPage.submitEmptyHtmlForm();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("testpages.eviltester.com"),
                "The browser navigated away from the AUT during empty form submission.");
        Assert.assertFalse(getDriver().getPageSource().isBlank(),
                "The page source was unexpectedly blank after empty form submission.");
    }
}
