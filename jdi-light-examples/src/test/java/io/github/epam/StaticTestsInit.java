package io.github.epam;

import com.epam.jdi.light.driver.WebDriverFactory;
import com.epam.jdi.light.elements.base.OutputTemplates;
import io.github.com.StaticSite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.light.elements.init.PageFactory.initElements;
import static com.epam.jdi.light.logger.LogLevels.INFO;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static io.github.com.StaticSite.homePage;

public class StaticTestsInit {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        logger.setLogLevel(INFO);
        OutputTemplates.STEP_TEMPLATE = "{action}";
        /*ACTION_FAILED = (el, ex) ->
            ex + LINE_BREAK + "Screenshot: " + takeScreen();*/
        initElements(StaticSite.class);
        homePage.open();
        logger.toLog("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    public static void tearDown() {
        WebDriverFactory.close();
    }
}
