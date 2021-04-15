package framework.elements;

import framework.utils.LogUtil;
import org.openqa.selenium.By;

public class DropDown extends BaseElement {
    public DropDown(By locator, String name) {
        super(locator, name);
    }

    public void selectElementByName(String domain) {
        LogUtil.info(String.format("Selecting domain by name %s", domain));
        for (int i = 0; i <= findElements().size(); i++) {
            if (findElements().get(i).getText().equals(domain)) {
                LogUtil.info(String.format("Text of domain %s", getTextOfElementOfListOfElements(i)));
                clickOnElementOfListOfElements(i);
                break;
            }
        }
    }
}
