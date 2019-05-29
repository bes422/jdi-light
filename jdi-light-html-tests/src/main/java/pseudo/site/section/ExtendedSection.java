package pseudo.site.section;

import com.epam.jdi.light.elements.base.UIElement;
import com.epam.jdi.light.elements.complex.Droplist;
import com.epam.jdi.light.elements.complex.UIList;
import com.epam.jdi.light.elements.complex.WebList;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.UI;
import com.epam.jdi.light.elements.pageobjects.annotations.simple.XPath;
import com.epam.jdi.light.ui.html.common.Button;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExtendedSection extends CustomSection {
    @FindBy(id = "webElementPackage")
    WebElement webElementPackage;
    @FindBy(id = "webElementPublic")
    public WebElement webElementPublic;
    @FindBy(id = "webElementPrivate")
    private WebElement webElementPrivate;
    @UI(".webElementPackageUI")
    WebElement webElementPackageUI;
    @UI(".webElementPublicUI")
    public WebElement webElementPublicUI;
    @Css(".webElementPackageCss")
    WebElement webElementPackageCss;
    @XPath("//*[@class='webElementPublicXPath']")
    public WebElement webElementPublicXPath;

    @FindBy(id = "listWebElementPackage")
    List<WebElement> listWebElementPackage;
    @FindBy(id = "listWebElementPublic")
    public List<WebElement> listWebElementPublic;
    @FindBy(id = "listWebElementPrivate")
    private List<WebElement> listWebElementPrivate;
    @UI(".listWebElementPackageUI")
    List<WebElement> listWebElementPackageUI;
    @UI(".listWebElementPublicUI")
    public List<WebElement> listWebElementPublicUI;
    @Css(".listWebElementPackageCss")
    List<WebElement> listWebElementPackageCss;
    @XPath("//*[@class='listWebElementPublicXPath']")
    public List<WebElement> listWebElementPublicXPath;

    @FindBy(id = "uielementPackage")
    UIElement uielementPackage;
    @FindBy(id = "uielementPublic")
    public UIElement uielementPublic;
    @FindBy(id = "uielementPrivate")
    private UIElement uielementPrivate;
    @UI(".uielementPackageUI")
    UIElement uielementPackageUI;
    @UI(".uielementPublicUI")
    public UIElement uielementPublicUI;
    @Css(".uielementPackageCss")
    UIElement uielementPackageCss;
    @XPath("//*[@class='uielementPublicXPath']")
    public UIElement uielementPublicXPath;

    @FindBy(id = "buttonPackage")
    Button buttonPackage;
    @FindBy(id = "buttonPublic")
    public Button buttonPublic;
    @FindBy(id = "buttonPrivate")
    private Button buttonPrivate;
    @UI(".buttonPackageUI")
    Button buttonPackageUI;
    @UI(".buttonPublicUI")
    public Button buttonPublicUI;
    @Css(".buttonPackageCss")
    UIElement buttonPackageCss;
    @XPath("//*[@class='buttonPublicXPath']")
    public UIElement buttonPublicXPath;

    @FindBy(id = "listUIElementPackage")
    List<UIElement> listUIElementPackage;
    @FindBy(id = "listUIElementPublic")
    public List<UIElement> listUIElementPublic;
    @FindBy(id = "listUIElementPrivate")
    private List<UIElement> listUIElementPrivate;
    @UI(".listUIElementPackageUI")
    List<UIElement> listUIElementPackageUI;
    @UI(".listUIElementPublicUI")
    public List<UIElement> listUIElementPublicUI;
    @Css(".listUIElementPackageCss")
    List<UIElement> listUIElementPackageCss;
    @XPath("//*[@class='listUIElementPublicXPath']")
    public List<UIElement> listUIElementPublicXPath;

    @FindBy(id = "listButtonPackage")
    List<Button> listButtonPackage;
    @FindBy(id = "listButtonPublic")
    public List<Button> listButtonPublic;
    @FindBy(id = "listButtonPrivate")
    private List<Button> listButtonPrivate;
    @UI(".listButtonPackageUI")
    List<Button> listButtonPackageUI;
    @UI(".listButtonPublicUI")
    public List<Button> listButtonPublicUI;
    @Css(".listButtonPackageCss")
    List<Button> listButtonPackageCss;
    @XPath("//*[@class='listButtonPublicXPath']")
    public List<Button> listButtonPublicXPath;

    @FindBy(id = "webListPackage")
    WebList webListPackage;
    @FindBy(id = "webListPublic")
    public WebList webListPublic;
    @FindBy(id = "webListPrivate")
    private WebList webListPrivate;
    @UI(".webListPackageUI")
    WebList webListPackageUI;
    @UI(".webListPublicUI")
    public WebList webListPublicUI;
    @Css(".webListPackageCss")
    WebList webListPackageCss;
    @XPath("//*[@class='webListPublicXPath']")
    public WebList webListPublicXPath;

    @FindBy(id = "uilistQuestionPackage")
    UIList<CustomSection, ?> uilistQuestionPackage;
    @FindBy(id = "uilistQuestionPublic")
    public UIList<CustomSection, ?> uilistQuestionPublic;
    @FindBy(id = "uilistQuestionPrivate")
    private UIList<CustomSection, ?> uilistQuestionPrivate;
    @UI(".uilistQuestionPackageUI")
    UIList<CustomSection, ?> uilistQuestionPackageUI;
    @UI(".uilistQuestionPublicUI")
    public UIList<CustomSection, ?> uilistQuestionPublicUI;
    @Css(".uilistQuestionPackageCss")
    UIList<CustomSection, ?> uilistQuestionPackageCss;
    @XPath("//*[@class='uilistQuestionPublicXPath']")
    public UIList<CustomSection, ?> uilistQuestionPublicXPath;

    @FindBy(id = "uilistSomedataPackage")
    UIList<CustomSection, SomeData> uilistSomedataPackage;
    @FindBy(id = "uilistSomedataPublic")
    public UIList<CustomSection, SomeData> uilistSomedataPublic;
    @FindBy(id = "uilistSomedataPrivate")
    private UIList<CustomSection, SomeData> uilistSomedataPrivate;
    @UI(".uilistSomedataPackageUI")
    UIList<CustomSection, SomeData> uilistSomedataPackageUI;
    @UI(".uilistSomedataPublicUI")
    public UIList<CustomSection, SomeData> uilistSomedataPublicUI;
    @Css(".uilistSomedataPackageCss")
    UIList<CustomSection, SomeData> uilistSomedataPackageCss;
    @XPath("//*[@class='uilistSomedataPublicXPath']")
    public UIList<CustomSection, SomeData> uilistSomedataPublicXPath;

    @JDropdown(root = "div[ui=droplistPackage", value = "input", list = "li", expand = ".expand")
    Droplist droplistPackage;
    @JDropdown(root = "div[ui=droplistPublic", value = "input", list = "li", expand = ".expand")
    public Droplist droplistPublic;
    @JDropdown(root = "div[ui=droplistPrivate", value = "input", list = "li", expand = ".expand")
    private Droplist droplistPrivate;
    private static Droplist droplistPrivateStatic;
    @UI(".droplistPackageUI")
    Droplist droplistPackageUI;
    @UI(".droplistPublicUI")
    public Droplist droplistPublicUI;

    public WebElement getWebElementPackage() {
        return webElementPackage;
    }

    public void setWebElementPackage(WebElement webElementPackage) {
        this.webElementPackage = webElementPackage;
    }

    public WebElement getWebElementPrivate() {
        return webElementPrivate;
    }

    public void setWebElementPrivate(WebElement webElementPrivate) {
        this.webElementPrivate = webElementPrivate;
    }


    public WebElement getWebElementPackageUI() {
        return webElementPackageUI;
    }

    public void setWebElementPackageUI(WebElement webElementPackageUI) {
        this.webElementPackageUI = webElementPackageUI;
    }

    public List<WebElement> getListWebElementPackage() {
        return listWebElementPackage;
    }

    public void setListWebElementPackage(List<WebElement> listWebElementPackage) {
        this.listWebElementPackage = listWebElementPackage;
    }

    public List<WebElement> getListWebElementPrivate() {
        return listWebElementPrivate;
    }

    public void setListWebElementPrivate(List<WebElement> listWebElementPrivate) {
        this.listWebElementPrivate = listWebElementPrivate;
    }

    public List<WebElement> getListWebElementPackageUI() {
        return listWebElementPackageUI;
    }

    public void setListWebElementPackageUI(List<WebElement> listWebElementPackageUI) {
        this.listWebElementPackageUI = listWebElementPackageUI;
    }

    public UIElement getUielementPackage() {
        return uielementPackage;
    }

    public void setUielementPackage(UIElement uielementPackage) {
        this.uielementPackage = uielementPackage;
    }

    public UIElement getUielementPrivate() {
        return uielementPrivate;
    }

    public void setUielementPrivate(UIElement uielementPrivate) {
        this.uielementPrivate = uielementPrivate;
    }

    public UIElement getUielementPackageUI() {
        return uielementPackageUI;
    }

    public void setUielementPackageUI(UIElement uielementPackageUI) {
        this.uielementPackageUI = uielementPackageUI;
    }

    public Button getButtonPackage() {
        return buttonPackage;
    }

    public void setButtonPackage(Button buttonPackage) {
        this.buttonPackage = buttonPackage;
    }

    public Button getButtonPrivate() {
        return buttonPrivate;
    }

    public void setButtonPrivate(Button buttonPrivate) {
        this.buttonPrivate = buttonPrivate;
    }

    public Button getButtonPackageUI() {
        return buttonPackageUI;
    }

    public void setButtonPackageUI(Button buttonPackageUI) {
        this.buttonPackageUI = buttonPackageUI;
    }

    public List<UIElement> getListUIElementPackage() {
        return listUIElementPackage;
    }

    public void setListUIElementPackage(List<UIElement> listUIElementPackage) {
        this.listUIElementPackage = listUIElementPackage;
    }

    public List<UIElement> getListUIElementPrivate() {
        return listUIElementPrivate;
    }

    public void setListUIElementPrivate(List<UIElement> listUIElementPrivate) {
        this.listUIElementPrivate = listUIElementPrivate;
    }


    public List<UIElement> getListUIElementPackageUI() {
        return listUIElementPackageUI;
    }

    public void setListUIElementPackageUI(List<UIElement> listUIElementPackageUI) {
        this.listUIElementPackageUI = listUIElementPackageUI;
    }

    public List<Button> getListButtonPackage() {
        return listButtonPackage;
    }

    public void setListButtonPackage(List<Button> listButtonPackage) {
        this.listButtonPackage = listButtonPackage;
    }

    public List<Button> getListButtonPrivate() {
        return listButtonPrivate;
    }

    public void setListButtonPrivate(List<Button> listButtonPrivate) {
        this.listButtonPrivate = listButtonPrivate;
    }

    public List<Button> getListButtonPackageUI() {
        return listButtonPackageUI;
    }

    public void setListButtonPackageUI(List<Button> listButtonPackageUI) {
        this.listButtonPackageUI = listButtonPackageUI;
    }

    public WebList getWebListPackage() {
        return webListPackage;
    }

    public void setWebListPackage(WebList webListPackage) {
        this.webListPackage = webListPackage;
    }

    public WebList getWebListPrivate() {
        return webListPrivate;
    }

    public void setWebListPrivate(WebList webListPrivate) {
        this.webListPrivate = webListPrivate;
    }

    public WebList getWebListPackageUI() {
        return webListPackageUI;
    }

    public void setWebListPackageUI(WebList webListPackageUI) {
        this.webListPackageUI = webListPackageUI;
    }

    public UIList<CustomSection, ?> getUilistQuestionPackage() {
        return uilistQuestionPackage;
    }

    public void setUilistQuestionPackage(UIList<CustomSection, ?> uilistQuestionPackage) {
        this.uilistQuestionPackage = uilistQuestionPackage;
    }

    public UIList<CustomSection, ?> getUilistQuestionPrivate() {
        return uilistQuestionPrivate;
    }

    public void setUilistQuestionPrivate(UIList<CustomSection, ?> uilistQuestionPrivate) {
        this.uilistQuestionPrivate = uilistQuestionPrivate;
    }


    public UIList<CustomSection, ?> getUilistQuestionPackageUI() {
        return uilistQuestionPackageUI;
    }

    public void setUilistQuestionPackageUI(UIList<CustomSection, ?> uilistQuestionPackageUI) {
        this.uilistQuestionPackageUI = uilistQuestionPackageUI;
    }

    public UIList<CustomSection, SomeData> getUilistSomedataPackage() {
        return uilistSomedataPackage;
    }

    public void setUilistSomedataPackage(UIList<CustomSection, SomeData> uilistSomedataPackage) {
        this.uilistSomedataPackage = uilistSomedataPackage;
    }

    public UIList<CustomSection, SomeData> getUilistSomedataPrivate() {
        return uilistSomedataPrivate;
    }

    public void setUilistSomedataPrivate(UIList<CustomSection, SomeData> uilistSomedataPrivate) {
        this.uilistSomedataPrivate = uilistSomedataPrivate;
    }

    public UIList<CustomSection, SomeData> getUilistSomedataPackageUI() {
        return uilistSomedataPackageUI;
    }

    public void setUilistSomedataPackageUI(UIList<CustomSection, SomeData> uilistSomedataPackageUI) {
        this.uilistSomedataPackageUI = uilistSomedataPackageUI;
    }

    public Droplist getDroplistPackage() {
        return droplistPackage;
    }

    public void setDroplistPackage(Droplist droplistPackage) {
        this.droplistPackage = droplistPackage;
    }

    public Droplist getDroplistPrivate() {
        return droplistPrivate;
    }

    public void setDroplistPrivate(Droplist droplistPrivate) {
        this.droplistPrivate = droplistPrivate;
    }

    public static Droplist getDroplistPrivateStatic() {
        return droplistPrivateStatic;
    }

    public static void setDroplistPrivateStatic(Droplist droplistPrivateStatic) {
        ExtendedSection.droplistPrivateStatic = droplistPrivateStatic;
    }

    public Droplist getDroplistPackageUI() {
        return droplistPackageUI;
    }

    public void setDroplistPackageUI(Droplist droplistPackageUI) {
        this.droplistPackageUI = droplistPackageUI;
    }

    public WebElement getWebElementPackageCss() {
        return webElementPackageCss;
    }

    public List<WebElement> getListWebElementPackageCss() {
        return listWebElementPackageCss;
    }

    public UIElement getUielementPackageCss() {
        return uielementPackageCss;
    }

    public UIElement getButtonPackageCss() {
        return buttonPackageCss;
    }

    public List<UIElement> getListUIElementPackageCss() {
        return listUIElementPackageCss;
    }

    public List<Button> getListButtonPackageCss() {
        return listButtonPackageCss;
    }

    public WebList getWebListPackageCss() {
        return webListPackageCss;
    }

    public UIList<CustomSection, ?> getUilistQuestionPackageCss() {
        return uilistQuestionPackageCss;
    }

    public UIList<CustomSection, SomeData> getUilistSomedataPackageCss() {
        return uilistSomedataPackageCss;
    }
}
