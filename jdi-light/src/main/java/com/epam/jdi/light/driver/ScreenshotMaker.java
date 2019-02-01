package com.epam.jdi.light.driver;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.driver.WebDriverFactory.hasRunDrivers;
import static com.epam.jdi.light.driver.get.DriverData.LOGS_PATH;
import static com.epam.jdi.light.driver.get.DriverData.PROJECT_PATH;
import static com.epam.jdi.tools.PathUtils.mergePath;
import static com.epam.jdi.tools.Timer.nowTime;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.openqa.selenium.OutputType.FILE;

public class ScreenshotMaker {
    public static String SCREEN_PATH = LOGS_PATH + "\\screens";
    public static String SCREEN_NAME = "screen";
    public static String SCREEN_FILE_SUFFIX = ".jpg";

    public static String takeScreen() throws IOException {
        return new ScreenshotMaker().takeScreenshot();
    }

    public static String getPath() {
        if (isBlank((SCREEN_PATH))) return "";
        String result = SCREEN_PATH.replace("/", "\\");
        return result.contains(":")
            ? SCREEN_PATH
            : PROJECT_PATH + SCREEN_PATH;
    }
    public String takeScreenshot() {
        return takeScreenshot(SCREEN_NAME, "yyyy-MM-dd-HH-mm-ss");
    }
    public String takeScreenshot(String value) {
        return takeScreenshot(value, "yyyy-MM-dd-HH-mm-ss");
    }
    public String takeScreenshot(String name, String dateFormat) {
        if (!hasRunDrivers())
            throw exception("Can't do Screenshot. No Drivers run");
        String screensFilePath = getFileName(mergePath(
            getPath(), name + nowTime(dateFormat)));
        new File(screensFilePath).getParentFile().mkdirs();
        File screensFile = ((TakesScreenshot) getDriver()).getScreenshotAs(FILE);
        try {
            copyFile(screensFile, new File(screensFilePath));
        } catch (Exception ex) {
            throw exception("Failed to do screenshot: " + ex.getMessage());
        }
        return screensFilePath;
    }

    private String getFileName(String fileName) {
        int num = 1;
        String newName = fileName;
        while (new File(newName + SCREEN_FILE_SUFFIX).exists())
            newName = fileName + "_" + num++;
        return newName + SCREEN_FILE_SUFFIX;
    }
}