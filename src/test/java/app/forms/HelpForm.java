package app.forms;

import framework.elements.Button;
import org.openqa.selenium.By;

public class HelpForm {
    private Button closeFormButton = new Button(By.xpath("//div[@class='align__cell u-right']"),
            "CloseFormButton");
    private Button openFormButton = new Button(By.xpath("//button[@class='button button--solid button--blue help-form__close-button']"), "OpenFormButton");

    public void closeHelpForm() {
        closeFormButton.click();
    }

    public boolean isFormInvisible() {
        return openFormButton.isElementNotVisible();
    }
}
