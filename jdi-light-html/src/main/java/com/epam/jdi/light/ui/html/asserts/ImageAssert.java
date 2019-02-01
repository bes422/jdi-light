package com.epam.jdi.light.ui.html.asserts;

import com.epam.jdi.light.asserts.CommonAssert;
import org.hamcrest.Matcher;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public interface ImageAssert extends CommonAssert<HtmlAssertion> {
    HtmlAssertion src(Matcher<String> condition);
    HtmlAssertion alt(Matcher<String> condition);

    HtmlAssertion height(Matcher<Integer> value);
    HtmlAssertion width(Matcher<Integer> value);
}