package com.epam.jdi.light.asserts;

import com.epam.jdi.tools.LinqUtils;
import com.epam.jdi.tools.PrintUtils;
import com.epam.jdi.tools.func.JFunc1;
import org.hamcrest.Matchers;

import java.util.List;

import static com.epam.jdi.light.asserts.BaseSelectorAssert.waitAssert;
import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.tools.LinqUtils.all;
import static com.epam.jdi.tools.PrintUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UIListAssert<E> {
    List<E> elements;

    public UIListAssert(List<E> elements) {
        this.elements = elements;
    }

    public UIListAssert<E> each(JFunc1<E, Boolean> condition) {
        waitAssert(() -> assertThat(all(elements, condition::execute), is(true)));
        return this;
    }
    public UIListAssert<E> any(JFunc1<E, Boolean> condition) {
        waitAssert(() -> assertThat(LinqUtils.any(elements, condition::execute), is(true)));
        return this;
    }

    public UIListAssert<E> onlyOne(JFunc1<E, Boolean> condition) {
        waitAssert(() -> assertThat(LinqUtils.single(elements, condition::execute), is(notNullValue())));
        return this;
    }
    public UIListAssert<E> noOne(JFunc1<E, Boolean> condition) {
        waitAssert(() -> assertThat(LinqUtils.first(elements, condition::execute), is(nullValue())));
        return this;
    }
    public UIListAssert<E> value(String expected) {
        waitAssert(() -> assertThat(print(elements, Object::toString), is(expected)));
        return this;
    }
}