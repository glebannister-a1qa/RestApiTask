package app.forms;

import framework.elements.CheckBox;
import framework.elements.Image;
import framework.elements.Link;
import framework.utils.LogUtil;
import framework.utils.WindowUploadUtil;
import org.openqa.selenium.By;

import java.util.List;

public class AvatarAndInterestsForm {

    private static final String checkBoxesNamesLocator = "//*[contains(@class, 'avatar-and-interests__interests-list" +
            "__item')]/div[.//ancestor::span[last() and text()!='Select all' and text()!='Unselect all']]";
    private static final String checkBoxClickLocator = "//label[@for='interest_%s']";
    private static Link uploadImageLink = new Link(By.xpath("//a[@class='avatar-and-interests__upload-button']"),
            "UploadImageLink");
    private Image avatarImage = new Image(By.xpath("//div[@class='avatar-and-interests__avatar-image']"),
            "AvatarImage");
    private CheckBox unselectAllCheckBox = new CheckBox(By.xpath("//label[@for='interest_unselectall']"),
            "UnselectAllCheckBox");

    public void clickUploadImage(String path) {
        LogUtil.info(String.format("Uploading file from %s", path));
        uploadImageLink.click();
        WindowUploadUtil.uploadAvatarImage(path);
    }

    public String getImageBase64() {
        return avatarImage.getAttribute("style");
    }

    public void clickUnselectAllCheckBox() {
        unselectAllCheckBox.click();
    }

    public List<String> getInterestsNames() {
        return new CheckBox(By.xpath(checkBoxesNamesLocator), "CheckBoxesNames").getCheckBoxesNames();
    }

    public void clickOnInterestByName(String interestName) {
        new CheckBox(
                By.xpath(String.format(checkBoxClickLocator, interestName)), "CheckBoxesClick"
        ).clickAcceptCheckBox();
    }

}
