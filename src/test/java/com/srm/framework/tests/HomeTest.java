
package com.srm.framework.tests;

import com.srm.framework.core.BaseTest;
import com.srm.framework.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test(description = "Verify the AUT landing page is reachable and behaves like the framework home dashboard")
    public void verifyHomePage() {
        HomePage homePage = new HomePage().open();

        Assert.assertTrue(homePage.getHeading().contains("Software Testing Practice Pages"),
                "Home page heading did not match the expected AUT title.");
        Assert.assertTrue(homePage.isPracticePagesLinkVisible(),
                "Practice Pages link was not visible on the home page.");
    }
}
