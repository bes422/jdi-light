package io.github.epam.html.tests.issues.issue69;

import com.epam.jdi.light.elements.composite.Section;
import com.epam.jdi.light.ui.html.common.Button;
import com.epam.jdi.tools.PrintUtils;

import static com.epam.jdi.tools.PrintUtils.print;
import static java.util.Arrays.asList;

public class Paragraph extends Section {
    public Button paragraph, paragraph2, paragraph3;

    @Override
    public String toString() {
        return print(asList(paragraph.getText(),
                paragraph2.getText(),
                paragraph3.getText()));
    }
}