
package com.srm.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getExtentReports() {
        if (extentReports == null) {
            try {
                Path reportDirectory = Path.of("reports");
                Files.createDirectories(reportDirectory);
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportDirectory.resolve("AutomationReport.html").toString());
                sparkReporter.config().setReportName("Selenium Java Automation Framework");
                sparkReporter.config().setDocumentTitle("Automation Execution Report");

                extentReports = new ExtentReports();
                extentReports.attachReporter(sparkReporter);
                extentReports.setSystemInfo("Framework", "Selenium + TestNG + POM");
                extentReports.setSystemInfo("Project", "AdminPanel");
            } catch (Exception exception) {
                throw new IllegalStateException("Unable to initialize ExtentReports.", exception);
            }
        }
        return extentReports;
    }
}
