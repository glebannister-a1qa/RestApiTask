package app.forms;

import framework.elements.Button;
import framework.elements.Field;
import org.openqa.selenium.By;

public class CookiesForm {
    private Button acceptCookiesButton = new Button(By.xpath("//button[@class='button button--solid button--transparent']"),
            "AcceptCookieButton");
    public Field cookiesField = new Field(By.xpath("//div[@class='cookies']"), "CookiesField");

    public void acceptCookie() {
        acceptCookiesButton.click();
    }

    public boolean isCookieFormGone() {
        return cookiesField.isElementNotVisible();
    }
}
