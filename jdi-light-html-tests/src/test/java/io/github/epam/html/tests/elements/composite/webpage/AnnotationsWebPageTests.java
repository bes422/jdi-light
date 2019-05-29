package io.github.epam.html.tests.elements.composite.webpage;

import com.epam.jdi.light.elements.composite.WebPage;
import io.github.epam.TestsInit;
import org.testng.Assert;
import org.testng.annotations.Test;
import pseudo.site.dataproviders.WebPage.WebPageDataProvider;


public class AnnotationsWebPageTests extends TestsInit {

    @Test(dataProvider = "annotationsWebPageDataProvider", dataProviderClass = WebPageDataProvider.class)
    public void annotationsWebPageTest(WebPage webPage, String expectedURL, String expectedTitle) {
        Assert.assertEquals(webPage.url, expectedURL);
        Assert.assertEquals(webPage.title, expectedTitle);
    }
}