package app.forms;

import framework.elements.Button;
import framework.elements.CheckBox;
import framework.elements.DropDown;
import framework.elements.Field;
import org.openqa.selenium.By;

public class CardField {
    private Field passwordField = new Field(By.xpath("//input[@placeholder='Choose Password']"), "PasswordField");
    private Field emailField = new Field(By.xpath("//input[@placeholder='Your email']"), "EmailField");
    private Field domainField = new Field(By.xpath("//input[@placeholder='Domain']"), "DomainField");
    private Button domainDropDownField = new Button(By.xpath("//div[@class='dropdown__field']"), "DomainDropDownField");
    private DropDown domainDropDown = new DropDown(By.xpath("//div[@class='dropdown__list-item']"), "DomainDropDownList");
    private CheckBox acceptCheckBox = new CheckBox(By.xpath("//span[@class='icon icon-check checkbox__check']"), "AcceptCheckBox");
    private Button nextButton = new Button(By.xpath("//div[@class='align__cell button-container__secondary']/a"), "NextButton");


    public void setPassword(String text) {
        passwordField.clearText();
        passwordField.sendKeys(text);
    }

    public void setEmail(String text) {
        emailField.clearText();
        emailField.sendKeys(text);
    }

    public void setDomain(String text) {
        domainField.clearText();
        domainField.sendKeys(text);
    }

    public void selectHighLevelDomain(String domain) {
        domainDropDownField.click();
        domainDropDown.selectElementByName(domain);
    }

    public void clickOnCheckBox() {
        acceptCheckBox.clickAcceptCheckBox();
    }
    public void clickNextLink(){
        nextButton.scrollToElementWithJS();
        nextButton.click();
    }
}
