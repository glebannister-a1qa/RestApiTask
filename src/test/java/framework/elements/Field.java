package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Field extends BaseElement {
    public Field(By locator, String name) {
        super(locator, name);
    }

    public void clearText() {
        findElement().clear();
    }

    public void highlightText(String key) {
        findElement().sendKeys(Keys.CONTROL + key);
    }
}
