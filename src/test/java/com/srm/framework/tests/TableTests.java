
package com.srm.framework.tests;

import com.srm.framework.core.BaseTest;
import com.srm.framework.pages.TablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TableTests extends BaseTest {

    @Test(description = "Verify table headers are present on the static table page")
    public void shouldDisplayStaticTableHeaders() {
        TablePage tablePage = new TablePage().openStaticTable();

        Assert.assertTrue(tablePage.isTableDisplayed(), "Static table was not visible.");
        Assert.assertTrue(tablePage.getHeaders().contains("Name"), "Name header was not found.");
        Assert.assertTrue(tablePage.getHeaders().contains("Amount"), "Amount header was not found.");
    }

    @Test(description = "Verify row data changes after refreshing the dynamic table data")
    public void shouldRefreshDynamicTableRows() {
        TablePage tablePage = new TablePage().openDynamicTable();
        String originalFirstRow = tablePage.getFirstRowText();

        tablePage.refreshDynamicTable("Automation Table",
                "[{\"name\":\"Selenium\",\"age\":11},{\"name\":\"TestNG\",\"age\":7}]");

        String updatedFirstRow = tablePage.getFirstRowText();
        Assert.assertNotEquals(updatedFirstRow, originalFirstRow,
                "The dynamic table first row did not change after refreshing the data.");
        Assert.assertTrue(tablePage.pageContainsTableText("Selenium"),
                "Updated dynamic table content was not rendered.");
    }
}
