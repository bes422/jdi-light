package com.epam.jdi.light.elements.composite;

import com.epam.jdi.light.common.CheckTypes;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.common.PageChecks;
import com.epam.jdi.light.elements.base.DriverBase;
import com.epam.jdi.light.elements.interfaces.INamed;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.tools.CacheValue;
import com.epam.jdi.tools.func.JAction1;
import com.epam.jdi.tools.map.MapArray;

import java.text.MessageFormat;
import java.util.function.Supplier;

import static com.epam.jdi.light.common.CheckTypes.*;
import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.common.PageChecks.EVERY_PAGE;
import static com.epam.jdi.light.common.PageChecks.NEW_PAGE;
import static com.epam.jdi.light.driver.WebDriverFactory.*;
import static com.epam.jdi.light.elements.base.OutputTemplates.*;
import static com.epam.jdi.light.elements.pageobjects.annotations.WebAnnotationsUtil.getUrlFromUri;
import static com.epam.jdi.light.logger.LogLevels.*;
import static com.epam.jdi.light.settings.TimeoutSettings.PAGE_TIMEOUT;
import static com.epam.jdi.light.settings.TimeoutSettings.TIMEOUT;
import static com.epam.jdi.light.settings.WebSettings.DOMAIN;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.tools.StringUtils.msgFormat;
import static com.epam.jdi.tools.switcher.SwitchActions.*;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Roman Iovlev on 25.03.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public class WebPage extends DriverBase implements INamed {
    public String url = "";
    public String title = "";

    public String checkUrl;
    public CheckTypes checkUrlType = CONTAINS;
    public CheckTypes checkTitleType = CheckTypes.NONE;
    public <T> Form<T> asForm() {
        return new Form<>().setPageObject(this).setName(getName()+" Form");
    }

    private static ThreadLocal<String> currentPage = new ThreadLocal<>();
    public static String getCurrentPage() { return currentPage.get(); }
    public static void setCurrentPage(WebPage page) {
        currentPage.set(page.getName());
    }

    public WebPage() { }
    public WebPage(String url) { this.url = url; }
    public static void openUrl(String url) {
        new WebPage(url).open();
    }

    @JDIAction(level = DEBUG)
    public static String getUrl() {
        return getDriver().getCurrentUrl();
    }

    @JDIAction(level = DEBUG)
    public static String getTitle() {
        return getDriver().getTitle();
    }

    public void updatePageData(Url urlAnnotation, Title titleAnnotation) {
        if (urlAnnotation != null) {
            url = urlAnnotation.value();
            checkUrl = urlAnnotation.template();
            checkUrlType = urlAnnotation.validate();
            if (isBlank(checkUrl)) {
                if (checkUrlType != MATCH)
                    checkUrl = url;
                else throw exception("In order to validate MATCH for page '%s', please specify 'template' in @Url",
                        getName());
            } else if (checkUrlType == null) checkUrlType = MATCH;
            if (!url.contains("://"))
                url = getUrlFromUri(url);
        } else  { if (isBlank(url)) url = DOMAIN; }
        if (titleAnnotation != null) {
            title = titleAnnotation.value();
            checkTitleType = titleAnnotation.validate();
        }
    }

    public StringCheckType url() {
        return new StringCheckType(driver()::getCurrentUrl, checkUrl, "url");
    }

    public StringCheckType title() {
        return new StringCheckType(driver()::getTitle, title, "title");
    }

    /**
     * Opens url specified for page
     */
    @JDIAction("Open '{name}'(url={0})")
    private void open(String url) {
        CacheValue.reset();
        driver().navigate().to(url);
        setCurrentPage(this);
    }
    public void open(Object... params) {
        open(getUrlWithParams(params));
    }
    private String getUrlWithParams(Object... params) {
        return params == null || params.length == 0
                ? url
                : url.contains("%s")
                    ? String.format(url, params)
                    : MessageFormat.format(url, params);
    }

    /**
     * Check that page opened
     */
    @JDIAction("Check that '{name}' is opened (url {checkUrlType} '{checkUrl}'; title {checkTitleType} '{title}')")
    public void checkOpened() {
        if (!hasRunDrivers())
            throw exception("Page '%s' is not opened: Driver is not run", toString());
        String result = Switch(checkUrlType).get(
                Value(CheckTypes.NONE, ""),
                Value(EQUALS, t -> !url().check() ? "Url '%s' doesn't equal to '%s'" : ""),
                Value(MATCH, t -> !url().match() ? "Url '%s' doesn't match to '%s'" : ""),
                Value(CONTAINS, t -> !url().contains() ? "Url '%s' doesn't contains '%s'" : "")
        );
        if (isNotBlank(result))
            throw exception("Page '%s' is not opened: %s", getName(), format(result, driver().getCurrentUrl(), checkUrl));
        result = Switch(checkTitleType).get(
                Value(CheckTypes.NONE, ""),
                Value(EQUALS, t -> !title().check() ? "Title '%s' doesn't equal to '%s'" : ""),
                Value(MATCH, t -> !title().match() ? "Title '%s' doesn't match to '%s'" : ""),
                Value(CONTAINS, t -> !title().contains() ? "Title '%s' doesn't contains '%s'" : "")
        );
        if (isNotBlank(result))
            throw exception("Page '%s' is not opened: %s", getName(), format(result, driver().getTitle(), title));
        setCurrentPage(this);
    }

    @JDIAction(level = DEBUG)
    public boolean isOpened() {
        if (!hasRunDrivers())
            return false;
        boolean result = Switch(checkUrlType).get(
                Value(CheckTypes.NONE, t -> true),
                Value(EQUALS, t -> url().check()),
                Value(MATCH, t -> url().match()),
                Value(CONTAINS, t -> url().contains()),
                Else(false)
        );
        if (!result) return false;
        result = Switch(checkTitleType).get(
                Value(CheckTypes.NONE, t -> true),
                Value(EQUALS, t -> title().check()),
                Value(MATCH, t -> title().match()),
                Value(CONTAINS, t -> title().contains()),
                Else(false)
        );
        if (result)
            setCurrentPage(this);
        return result;
    }

    public void shouldBeOpened() {
        openePage(url);
    }
    public void shouldBeOpened(Object... params) {
        openePage(getUrlWithParams(params));
    }
    @JDIAction("'{name}'(url={0}) should be opened")
    private void openePage(String url) {
        if (isOpened()) return;
        open(url);
        checkOpened();
    }
    /**
     * Reload current page
     */
    @JDIAction("Reload current page")
    public static void refresh() {
        getDriver().navigate().refresh();
    }
    public static void reload() { refresh(); }

    /**
     * Go back to previous page
     */
    @JDIAction("Go back to previous page")
    public static void back() {
        getDriver().navigate().back();
    }


    /**
     * Go forward to next page
     */
    @JDIAction("Go forward to next page")
    public static void forward() {
        getDriver().navigate().forward();
    }

    @JDIAction(level = DEBUG)
    public static void zoom(double factor) {
        jsExecute("document.body.style.transform = 'scale(' + arguments[0] + ')';" +
                        "document.body.style.transformOrigin = '0 0';", factor);
    }
    @JDIAction
    public static String getHtml() {
        return getDriver().getPageSource();
    }

    @JDIAction(level = DEBUG)
    private static void scroll(int x, int y) {
        jsExecute("window.scrollBy("+x+","+y+")");
    }
    @JDIAction
    public static void scrollToTop() {
        jsExecute("window.scrollTo(0,0)");
    }
    @JDIAction
    public static void scrollToBottom() {
        jsExecute("window.scrollTo(0,document.body.scrollHeight)");
    }

    @JDIAction("Scroll screen down on '{0}'")
    public static void scrollDown(int value) {
        scroll(0,value);
    }
    @JDIAction("Scroll screen up on '{0}'")
    public static void  scrollUp(int value) {
        scroll(0,-value);
    }
    @JDIAction("Scroll screen to the right on '{0}'")
    public static void  scrollRight(int value) {
        scroll(value,0);
    }
    @JDIAction("Scroll screen to the left on '{0}'")
    public static void scrollLeft(int value) {
        scroll(-value,0);
    }

    private static MapArray<String, WebPage> pages = new MapArray<>();
    public static void addPage(WebPage page) {
        pages.update(page.getName(), page);
    }
    public static <T extends WebPage> T getPage(String value) {
        WebPage page = pages.get(value);
        return (T) (page == null ? pages.get(value + " Page") : page);
    }

    @Override
    public String toString() {
        return Switch(logger.getLogLevel()).get(
            Case(l -> l == STEP,
                l -> msgFormat(PRINT_PAGE_STEP, this)),
            Case(l -> l == INFO,
                l -> msgFormat(PRINT_PAGE_INFO, this)),
            Default(msgFormat(PRINT_PAGE_DEBUG, this))
        );
    }

    public class StringCheckType {
        private Supplier<String> actual;
        private String equals;
        private String what;

        StringCheckType(Supplier<String> actual, String equals, String what) {
            this.actual = actual;
            this.equals = equals;
            this.what = what;
        }

        /**
         * Check that current page url/title equals to expected url/title
         */
        public boolean check() {
            String value = actual.get();
            logger.toLog(format("Check that page %s(%s) equals to '%s'", what, value, equals));
            return equals == null
                    || equals.equals("")
                    || value.equals(equals);
        }

        /**
         * Check that current page url/title matches to expected url/title-matcher
         */
        public boolean match() {
            String value = actual.get();
            logger.toLog(format("Check that page %s(%s) matches to '%s'", what, value, equals));
            return equals == null
                    || equals.equals("")
                    || value.matches(equals);
        }

        /**
         * Check that current page url/title contains expected url/title-matcher
         */
        public boolean contains() {
            String value = actual.get();
            logger.toLog(format("Check that page %s(%s) contains '%s'", what, value, equals));
            return equals == null
                    || equals.equals("")
                    || value.contains(equals);
        }
    }

    public static PageChecks CHECK_AFTER_OPEN = PageChecks.NONE;
    public static JAction1<WebPage> BEFORE_NEW_PAGE = page -> {
        if (CHECK_AFTER_OPEN == NEW_PAGE)
            page.checkOpened();
        logger.toLog("Page: " + page.getName());
        TIMEOUT.set(PAGE_TIMEOUT.get());
    };
    public static JAction1<WebPage> BEFORE_EACH_PAGE = page -> {
        if (CHECK_AFTER_OPEN == EVERY_PAGE)
            page.checkOpened();
    };
}
