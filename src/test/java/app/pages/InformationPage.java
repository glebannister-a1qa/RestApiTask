package app.pages;

import framework.elements.Field;
import org.openqa.selenium.By;

public class InformationPage {
    private Field informationField = new Field(By.xpath("//div[@class='personal-details__form']"), "InformationPageForm");

    public boolean isInformationPageLoaded() {
        return informationField.isVisible();
    }
}
