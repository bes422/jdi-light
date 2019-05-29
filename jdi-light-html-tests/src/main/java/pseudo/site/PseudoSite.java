package pseudo.site;

import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.UI;
import pseudo.site.pages.*;
import pseudo.site.section.CustomSection;
import pseudo.site.section.ExtendedSection;


public class PseudoSite {
    public static Header pseudoHeader;
    @UI(".customSectionUI")
    public static CustomSection customSectionUI;
    public static CustomSection customSection;
    @UI(".extendedSectionUI")
    public static ExtendedSection extendedSectionUI;
    public static ExtendedSection extendedSection;

    @Url("pagewithurl.com")
    public static PageWithURL pageWithURL;

    @Url("/pagewithurl.com")
    public static PageWithURL slashPageWithURL;

    @Title("Page with Title")
    public static PageWithTitle pageWithTitle;

    @Url("pagewithboth.com") @Title("Page with both")
    public static PageWithBoth pageWithBoth;

    public static PageWithoutBoth pageWithoutBoth;
}
