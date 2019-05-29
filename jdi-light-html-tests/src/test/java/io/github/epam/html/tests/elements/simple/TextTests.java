package io.github.epam.html.tests.elements.simple;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.html5Page;
import static io.github.com.pages.HtmlElementsPage.jdiText;
import static io.github.epam.html.tests.elements.BaseValidations.baseValidation;
import static io.github.epam.html.tests.site.steps.States.shouldBeLoggedIn;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

public class TextTests extends TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        html5Page.shouldBeOpened();
    }
    String text = "Powerful Framework for UI Tests Automation. Suitable for any UI project: Web(Html5, Angular, React...), Mobile(Android IOs), Desktop(Win app) etc.";

    @Test
    public void getTextTest() {
        assertEquals(jdiText.getText(), text);
    }

    @Test
    public void getValueTest() {
        assertEquals(jdiText.getValue(), text);
    }

    @Test
    public void isValidationTest() {
        jdiText.is().enabled();
        jdiText.is().text(is(text));
        jdiText.is().text(containsString("Powerful Framework for UI"));
    }

    @Test
    public void assertValidationTest() {
        jdiText.assertThat().text(is(text));
    }

    @Test
    public void baseValidationTest() {
        baseValidation(jdiText);
    }
}
