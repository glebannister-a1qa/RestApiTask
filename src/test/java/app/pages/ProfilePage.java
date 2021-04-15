package app.pages;

import app.forms.AvatarAndInterestsForm;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class ProfilePage {
    private Label interestForm = new Label(By.xpath("//div[@class='avatar-and-interests-page']"),
            "InterestForm");
    private Button nextButton = new Button(By.xpath("//button[@class='button button--stroked button--white button--fluid']"), "ProfilePageNextButton");

    public AvatarAndInterestsForm avatarAndInterestsForm = new AvatarAndInterestsForm();

    public boolean isProfilePageLoaded() {
        return interestForm.isVisible();
    }

    public void clickNextButton() {
        nextButton.click();
    }
}
