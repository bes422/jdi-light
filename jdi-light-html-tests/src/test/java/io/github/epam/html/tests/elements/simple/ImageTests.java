package io.github.epam.html.tests.elements.simple;

import com.epam.jdi.light.elements.composite.WebPage;
import io.github.epam.TestsInit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.light.elements.base.WindowsManager.acceptAlert;
import static com.epam.jdi.light.elements.base.WindowsManager.getAlertText;
import static io.github.com.StaticSite.html5Page;
import static io.github.com.pages.HtmlElementsPage.jdiLogo;
import static io.github.epam.html.tests.elements.BaseValidations.baseValidation;
import static io.github.epam.html.tests.site.steps.Preconditions.shouldBeLoggedIn;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

public class ImageTests extends TestsInit {

    @BeforeMethod
    public void before() {
        shouldBeLoggedIn();
        html5Page.shouldBeOpened();
    }
    String text = "https://epam.github.io/JDI/images/jdi-logo.jpg";

    @Test
    public void getSrcTest() {
        assertEquals(jdiLogo.src(), text);
    }
    @Test
    public void getAltTest() {
        assertEquals(jdiLogo.alt(), "Jdi Logo 2");
    }

    @Test
    public void clickTest() {
        jdiLogo.click();
        assertEquals(getAlertText(), "JDI Logo");
        acceptAlert();
    }

    @Test
    public void isValidationTest() {
        WebPage.refresh();
        jdiLogo.is().src(containsString("jdi-logo.jpg"));
        jdiLogo.is().alt(is("Jdi Logo 2"));
        jdiLogo.assertThat().height(is(100));
        jdiLogo.assertThat().width(is(101));
    }

    @Test
    public void assertValidationTest() {
        jdiLogo.assertThat().alt(is("Jdi Logo 2"));
    }

    @Test
    public void baseValidationTest() {
        baseValidation(jdiLogo);
    }
}
