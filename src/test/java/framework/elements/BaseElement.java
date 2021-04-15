package framework.elements;

import framework.browser.Browser;
import framework.constants.StringConstants;
import framework.utils.LogUtil;
import framework.waits.Waits;
import org.openqa.selenium.*;

import java.util.List;
import java.util.NoSuchElementException;

abstract public class BaseElement {

    protected By locator;
    protected String name;


    protected BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public WebElement findElement() {
        LogUtil.info(String.format("Finding element %s, %s", locator, name));
        Waits.waitForVisibility(locator);
        return Browser.getDriver().findElement(locator);
    }

    public void sendKeys(String text) {
        LogUtil.info(String.format("Sending keys to element %s, %s, %s", locator, name, text));
        findElement().sendKeys(text);
    }

    public List<WebElement> findElements() {
        LogUtil.info(String.format("Finding list of elements %s, %s", locator, name));
        Waits.waitForVisibility(locator);
        return Browser.getDriver().findElements(locator);
    }

    public void click() {
        LogUtil.info(String.format("Checking if element clickable %s, %s", locator, name));
        Waits.waitToBeClickable(locator);
        LogUtil.info(String.format("Clicking on %s, %s", locator, name));
        findElement().click();
    }

    public String getTextOfElementOfListOfElements(int i){
        LogUtil.info(String.format("Getting text of element of list %s, %s", locator, name));
        return findElements().get(i).getText();
    }

    public void clickOnElementOfListOfElements(int i) {
        LogUtil.info(String.format("Checking if element clickable %s, %s", locator, name));
        Waits.waitToBeClickable(locator);
        LogUtil.info(String.format("Clicking on element of list %s, %s", locator, name));
        findElements().get(i).click();
    }

    public void clickOnElementOfListOfElementsOfAnotherList(By newLocator, int i) {
        LogUtil.info(String.format("Checking if element clickable %s", newLocator));
        Waits.waitToBeClickable(newLocator);
        LogUtil.info(String.format("Clicking on element of list %s", newLocator));
        findElements().get(i).findElements(newLocator).get(i).click();
    }

    public String getAttribute(String attribute) {
        LogUtil.info(String.format("Getting attribute of %s, %s", locator, name));
        return findElement().getAttribute(attribute);
    }

    public String getText() {
        LogUtil.info(String.format("Getting text of %s, %s", locator, name));
        return findElement().getText();
    }

    public void scrollToElementWithJS(){
        LogUtil.info(String.format("Scrolling to %s, %s", locator, name));
        ((JavascriptExecutor) Browser.getDriver()).executeScript(StringConstants.SCROLL_TO_ELEMENT_CONSTANT, findElement());
    }
    public boolean isVisible() {
        try {
            LogUtil.info(String.format("Checking for visibility of element %s, %s", locator, name));
            findElement();
            if (findElement() == null) {
                Waits.waitForVisibility(locator);
                return true;
            } else {
                return true;
            }
        } catch (StaleElementReferenceException | IndexOutOfBoundsException | TimeoutException | NoSuchElementException e) {
            LogUtil.error(String.valueOf(e));
            return false;
        }
    }

    public boolean isElementNotVisible() {
        LogUtil.info(String.format("Check for invisible of element %s, %s", locator, name));
        Waits.waitUntilNotVisibility(locator);
        if (!isVisible()) {
            return true;
        } else {
            return false;
        }
    }
}