package com.epam.jdi.light.elements.composite;

import com.epam.jdi.light.asserts.IsAssert;
import com.epam.jdi.light.elements.base.JDIBase;
import com.epam.jdi.light.elements.interfaces.PageObject;

/**
 * Created by Roman Iovlev on 29.03.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

public class Section extends JDIBase implements PageObject {

    //region Asserts
    public IsAssert is() {
        return new IsAssert(this);
    }
    public IsAssert assertThat() {
        return is();
    }
    public IsAssert has() {
        return is();
    }
    public IsAssert waitFor() {
        return is();
    }
    public IsAssert shouldBe() {
        return is();
    }
    //endregion
}
