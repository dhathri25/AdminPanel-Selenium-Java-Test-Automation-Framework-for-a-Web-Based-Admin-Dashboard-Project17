
package com.srm.framework.utils;

import com.srm.framework.driver.DriverFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {
    }

    public static String capture(String testName) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        Path screenshotDirectory = Path.of("screenshots");
        try {
            Files.createDirectories(screenshotDirectory);
            File source = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            Path target = screenshotDirectory.resolve(testName + "_" + timestamp + ".png");
            FileUtils.copyFile(source, target.toFile());
            return target.toAbsolutePath().toString();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to capture screenshot for test: " + testName, exception);
        }
    }
}
