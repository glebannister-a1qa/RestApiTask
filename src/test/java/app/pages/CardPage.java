package app.pages;

import app.forms.CardField;
import app.forms.CookiesForm;
import app.forms.HelpForm;
import framework.elements.Label;
import org.openqa.selenium.By;

public class CardPage {
    private Label cardPageLabel = new Label(By.xpath("//div[@class='login-form__container']"), "CardPageLabel");
    private Label timerLabel = new Label(By.xpath("//div[@class='timer timer--white timer--center']"), "TimerLabel");
    public CardField cardField = new CardField();
    public HelpForm helpForm = new HelpForm();
    public CookiesForm cookiesForm = new CookiesForm();

    public boolean isCardPageLoaded(){
        return cardPageLabel.isVisible();
    }
    public String getStartTime(){
        return timerLabel.getText();
    }
}
