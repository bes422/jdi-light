package com.epam.jdi.light.elements.base;

import com.epam.jdi.light.common.JDIAction;
import org.hamcrest.Matcher;
import org.openqa.selenium.Alert;

import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public class Alerts {

    /**
     * Accept alert
     */
    @JDIAction
    public static void acceptAlert() {
        alert().accept();
    }

    /**
     * Dismiss alert
     */
    @JDIAction
    public static void dismissAlert() {
        alert().dismiss();
    }

    /**
     * Get alert text
     * @return String text
     */
    @JDIAction
    public static String getAlertText() {
        return alert().getText();
    }

    /**
     * Validate alert by matching passed value with alert text
     * @param text to compare
     */
    @JDIAction
    public static void validateAlert(Matcher<String> text) {
        assertThat(getAlertText(), text);
        acceptAlert();
    }

    /**
     * Input the specified text in the alert and accept it
     * @param text to compare
     */
    @JDIAction("Input '{0}' in alert and accept")
    public static void inputAndAcceptAlert(String text) {
        alert().sendKeys(text);
        alert().accept();
    }

    private static Alert alert() {
        return getDriver().switchTo().alert();
    }
}
