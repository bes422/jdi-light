package com.epam.jdi.light.ui.html.asserts;

import org.hamcrest.Matcher;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */
public interface TextAreaAssert extends TextAssert {
    HtmlAssertion rowsCount(Matcher<Integer> condition);
    HtmlAssertion colsCount(Matcher<Integer> condition);
    HtmlAssertion minlength(Matcher<Integer> condition);
    HtmlAssertion maxlength(Matcher<Integer> condition);

}
