package app.pages;


import framework.elements.Label;
import framework.elements.Link;
import org.openqa.selenium.By;

public class WelcomePage {
    private Link hereLink = new Link(By.xpath("//a[@class='start__link']"), "HERELink");
    private Label logoLabel = new Label(By.xpath("//div[@class='logo__icon']"), "LogoLabel");

    public boolean isWelcomePageLoaded() {
        return logoLabel.isVisible();
    }

    public void clickHereLink() {
        hereLink.click();
    }
}
