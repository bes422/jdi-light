package io.github.epam.html.tests.suspend.simple;

import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.github.com.StaticSite.html5Page;
import static io.github.com.StaticSite.suspendHtml5Page;
import static io.github.com.pages.HtmlElementsPage.disabledTextArea;
import static io.github.com.pages.HtmlElementsPage.textArea;
import static io.github.epam.html.tests.elements.BaseValidations.baseValidation;
import static io.github.epam.html.tests.site.steps.States.shouldBeLoggedIn;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

public class SuspendHtmlPageTextAreaTests extends TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        suspendHtml5Page.shouldBeOpened();
        textArea.clear();
    }
    String text = "TextArea";

    @Test
    public void focusTest() {
        textArea.focus();
    }

    @Test
    public void isValidationTest() {
        textArea.is().enabled();
        textArea.setText(text);
        textArea.is().text(is(text));
        textArea.is().text(containsString("Area"));
        disabledTextArea.is().disabled();
    }

    @Test
    public void assertValidationTest() {
        textArea.setText(text);
        textArea.assertThat().text(is(text));
    }

    @Test
    public void rowsTest() {
        assertEquals(textArea.rows(), 3);
        assertEquals(textArea.cols(), 33);
        assertEquals(textArea.minlength(), 10);
        assertEquals(textArea.maxlength(), 200);

        textArea.is().rowsCount(is(3));
        textArea.is().colsCount(is(33));
        textArea.is().minlength(is(10));
        textArea.is().maxlength(is(200));
    }

    @Test
    public void baseValidationTest() {
        baseValidation(textArea);
    }


}
