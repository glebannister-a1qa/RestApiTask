package test;

import app.pages.CardPage;
import app.pages.WelcomePage;
import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseUI2 extends BaseTestUI {
    @Test
    public void testCase2() {
        LogUtil.step(1, String.format("Step go to %s", PropertyReader.getConfigValue("url")));
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.isWelcomePageLoaded(), "Welcome page was not opened");

        LogUtil.step(2, "Step close help form");
        welcomePage.clickHereLink();
        CardPage cardPage = new CardPage();
        Assert.assertTrue(cardPage.isCardPageLoaded(), "Card page was not loaded");
        cardPage.helpForm.closeHelpForm();
        Assert.assertTrue(cardPage.helpForm.isFormInvisible(), "Help form was not closed");
    }
}
