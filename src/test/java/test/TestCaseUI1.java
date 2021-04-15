package test;

import app.constants.AuthConstants;
import app.constants.RegularExpressionConstants;
import app.emuns.EmailDomains;
import app.pages.CardPage;
import app.pages.InformationPage;
import app.pages.ProfilePage;
import app.pages.WelcomePage;
import framework.utils.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;


public class TestCaseUI1 extends BaseTestUI {
    @Test
    public void testCase1() {
        LogUtil.step(1, String.format("Step go to %s", PropertyReader.getConfigValue("url")));
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.isWelcomePageLoaded(), "Welcome page was not opened");

        LogUtil.step(2, "Step go to next page");
        welcomePage.clickHereLink();
        CardPage cardPage = new CardPage();
        Assert.assertTrue(cardPage.isCardPageLoaded(), "Card page was not loaded");

        LogUtil.step(3, "Step enter correct password, email and click next");
        cardPage.cardField.setPassword(RandomUtil.getRandomStringWithRange(AuthConstants.RANGE_FOR_PASSWORD,
                AuthConstants.LENGTH_OF_PASSWORD));
        cardPage.cardField.setEmail(RandomUtil.getRandomStringWithRange(AuthConstants.RANGE,
                AuthConstants.LENGTH_OF_TEXT));
        cardPage.cardField.setDomain(RandomUtil.getRandomStringWithRange(AuthConstants.RANGE,
                AuthConstants.LENGTH_OF_TEXT));
        cardPage.cardField.selectHighLevelDomain(EmailDomains.COUK.getDomain());
        cardPage.cardField.clickOnCheckBox();
        cardPage.cardField.clickNextLink();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.isProfilePageLoaded(), "Profile page was not loaded");

        LogUtil.step(4, "Step upload image and pick 3 interests");
        profilePage.avatarAndInterestsForm.clickUploadImage(FileUtil.correctPathToFile());
        Assert.assertEquals(StringUtil.getSubstringWithTwoRegulars(profilePage.avatarAndInterestsForm.getImageBase64(),
                RegularExpressionConstants.REGULAR_START, RegularExpressionConstants.REGULAR_END),
                FileUtil.getBase64(PropertyReader.getConfigValue("pathToResources")),
                String.format("Base64 are not match %s %s",
                        StringUtil.getSubstringWithTwoRegulars(profilePage.avatarAndInterestsForm.getImageBase64(),
                                RegularExpressionConstants.REGULAR_START, RegularExpressionConstants.REGULAR_END),
                        FileUtil.getBase64(PropertyReader.getConfigValue("pathToResources"))));
        profilePage.avatarAndInterestsForm.clickUnselectAllCheckBox();
        List<String> names = profilePage.avatarAndInterestsForm.getInterestsNames();
        Collections.shuffle(names);
        profilePage.avatarAndInterestsForm.clickOnInterestByName(
                StringUtil.getCorrectInterestName(
                        names.get(Integer.parseInt(PropertyReader.getDataValue("firstItem")))
                )
        );
        profilePage.avatarAndInterestsForm.clickOnInterestByName(
                StringUtil.getCorrectInterestName(
                        names.get(Integer.parseInt(PropertyReader.getDataValue("secondItem")))
                )
        );
        profilePage.avatarAndInterestsForm.clickOnInterestByName(
                StringUtil.getCorrectInterestName(
                        names.get(Integer.parseInt(PropertyReader.getDataValue("thirdItem")))
                )
        );
        profilePage.clickNextButton();
        InformationPage informationPage = new InformationPage();
        Assert.assertTrue(informationPage.isInformationPageLoaded(), "Information page was not loaded");
    }
}
