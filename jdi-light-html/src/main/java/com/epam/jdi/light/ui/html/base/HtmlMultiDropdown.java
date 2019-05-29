package com.epam.jdi.light.ui.html.base;

import com.epam.jdi.light.asserts.IHasAssert;
import com.epam.jdi.light.asserts.SelectAssert;
import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.light.elements.complex.Selector;
import com.epam.jdi.light.ui.html.complex.MultiDropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.jdi.light.driver.WebDriverByUtils.fillByTemplate;
import static com.epam.jdi.light.logger.LogLevels.DEBUG;
import static com.epam.jdi.light.ui.html.HtmlFactory.$;
import static com.epam.jdi.light.ui.html.HtmlFactory.$$;
import static com.epam.jdi.tools.EnumUtils.getEnumValues;
import static com.epam.jdi.tools.LinqUtils.*;
import static java.util.Arrays.asList;
import static org.jsoup.helper.StringUtil.isBlank;

public class HtmlMultiDropdown extends Selector<HtmlElement>
        implements MultiDropdown, IHasAssert<SelectAssert> {

    public HtmlMultiDropdown() { setInitClass(HtmlElement.class); }
    public HtmlMultiDropdown(WebElement el) { super(el); setInitClass(HtmlElement.class); }

    By expandArrow = By.cssSelector(".caret");
    By values = By.tagName("li");
    By valueTemplate = By.xpath(".//label[text()='%s']/../..");
    By value = By.cssSelector("button");
    By valuesConatiner = By.tagName("ul");

    HtmlElement root() { return $(By.xpath(".."),this).setName("root"); }
    HtmlElement expander() { return root().find(expandArrow).setName("expandArrow"); }
    HtmlElement valuesList() { return root().find(valuesConatiner).setName("valuesContainer"); }
    HtmlElement value(String name) {
        return root().find(fillByTemplate(valueTemplate, name)).setName("valueTemplate");
    }
    HtmlElement valueText() { return root().find(value).setName("value"); }
    List<HtmlElement> allValues() { return root().finds(values); }

    @JDIAction(level = DEBUG)
    private void expand() {
        if (valuesList().isHidden())
            expander().click();
    }

    /**
     * Selects values from parameters
     * @param names String var arg, elements with text to select
     */
    @Override
    @JDIAction("Select '{0}' for '{name}'")
    public void select(String... names) {
        expand();
        for (String name : names) {
            HtmlElement value = value(name);
            if (value.isEnabled())
                value.click();
        }
    }

    /**
     * Selects only particular elements
     * @param indexes String var arg, elements with text to select
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(int... indexes) {
        expand();
        for (int i = 1; i <= indexes.length; i++) {
            HtmlElement value = $$(values, this).get(indexes[i]);
            if (value.isEnabled())
                value.click();
        }
    }

    public void check(String names) {
        if (isBlank(names)) return;
        check(names.split(","));
    }
    
    /**
     * Selects only particular elements
     * @param names String var arg, elements with text to select
     */
    @JDIAction("Check '{0}' for '{name}'")
    public void check(String... names) {
        expand();
        List<String> listNames = map(names, String::trim);
        for (String name : values()) {
            HtmlElement value = value(name);
            if (value.isDisabled()) continue;
            boolean valueSelected = value.find("input").isSelected();
            if (valueSelected && !listNames.contains(name.trim())
                    || !valueSelected && listNames.contains(name.trim()))
                value.click();
        }
        label().click();
    }

    /**
     * Unselects only particular elements
     * @param names String var arg, elements with text to unselect
     */
    @JDIAction("Uncheck '{0}' for '{name}'")
    public void uncheck(String... names) {
        expand();
        List<String> listNames = asList(names);
        for (String name : values()) {
            HtmlElement value = value(name);
            if (value.isDisabled()) continue;
            if (value.isSelected() && listNames.contains(value.getText().trim())
                    || !value.isSelected() && !listNames.contains(value.getText().trim()))
                value.click();
        }
    }
    public <TEnum extends Enum> void check(TEnum... values) {
        check(getEnumValues(values));
    }

    public <TEnum extends Enum> void uncheck(TEnum... values) {
        uncheck(getEnumValues(values));
    }

    /**
     * Checks particular elements by index
     * @param indexes int var arg, ids to check
     */
    @JDIAction("Check '{0}' for '{name}'")
    public void check(int... indexes) {
        expand();
        List<Integer> listIndexes = toList(indexes);
        for (int i = 1; i <= values().size(); i++) {
            HtmlElement value = allValues().get(i-1);
            if (value.isDisabled()) continue;
            if (value.isSelected() && !listIndexes.contains(i)
                    || !value.isSelected() && listIndexes.contains(i))
                value.click();
        }
    }

    /**
     * Unchecks particular elements by index
     * @param indexes int var arg, ids to uncheck
     */
    @JDIAction("Uncheck '{0}' for '{name}'")
    public void uncheck(int... indexes) {
        expand();
        List<Integer> listIndexes = toList(indexes);
        for (int i = 1; i <= values().size(); i++) {
            HtmlElement value = allValues().get(i-1);
            if (value.isDisabled()) continue;
            if (value.isSelected() && listIndexes.contains(i)
                || !value.isSelected() && !listIndexes.contains(i))
                value.click();
        }
    }

    /**
     * Gets checked values in dropdown
     * @return List<String>
     */
    @JDIAction("Get '{name}' checked values")
    public List<String> checked() {
        return ifSelect(allValues(),
                HtmlElement::isSelected,
                HtmlElement::getText);
    }

    /**
     * Selects value in dropdown
     * @param value String var arg
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(String value) {
        select(new String[]{value});
    }

    /**
     * Selects value with index in dropdown
     * @param index int var arg
     */
    @JDIAction("Select '{0}' for '{name}'")
    public void select(int index) {
        select(new int[]{index});
    }

    /**
     * Gets a list of text from each values from dropdown
     * @return List<String>
     */
    @JDIAction("Get '{name}' list values")
    public List<String> values() {
        return map(allValues(), HtmlElement::getText);
    }

    /**
     * Gets a list of innerText from each values from dropdown
     * @return List<String>
     */
    @JDIAction("Get '{name}' values")
    public List<String> innerValues() {
        return map(allValues(), HtmlElement::innerText);
    }

    /**
     * Gets enabled values from dropdown
     * @return List<String>
     */
    @JDIAction("Get '{name}' enabled values")
    public List<String> listEnabled() {
        return ifSelect(allValues(),
                HtmlElement::isEnabled,
                HtmlElement::getText);
    }

    /**
     * Gets disabled values from dropdown
     * @return List<String>
     */
    @JDIAction("Get '{name}' disabled values")
    public List<String> listDisabled() {
        return ifSelect(allValues(),
                HtmlElement::isDisabled,
                HtmlElement::getText);
    }

    @Override
    public void setValue(String value) {
        check(value);
    }

    /**
     * Gets selected value
     * @return String
     */
    @Override
    @JDIAction("Get '{name}' selected value")
    public String selected() {
        return valueText().getText();
    }

    /**
     * Checks if a value is selected in a dropdown
     * @param value String to select
     * @return boolean
     */
    @JDIAction("Is '{0}' selected for '{name}'")
    public boolean selected(String value) {
        return selected().trim().equalsIgnoreCase(value.trim());
    }

    @Override
    public String getValue() {
        return selected();
    }

    public SelectAssert is() {
        return new SelectAssert(() -> this);
    }
    public SelectAssert assertThat() {
        return is();
    }
    public SelectAssert has() {
        return is();
    }
    public SelectAssert waitFor() {
        return is();
    }
    public SelectAssert shouldBe() {
        return is();
    }
}
