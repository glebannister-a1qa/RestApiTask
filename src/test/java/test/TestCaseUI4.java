package test;

import app.pages.CardPage;
import app.pages.WelcomePage;
import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseUI4 extends BaseTestUI {
    @Test
    public void testCase4() {
        LogUtil.step(1, String.format("Step go to %s", PropertyReader.getConfigValue("url")));
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.isWelcomePageLoaded(), "Welcome page was not opened");

        LogUtil.step(2, "Check for correct time time");
        welcomePage.clickHereLink();
        CardPage cardPage = new CardPage();
        Assert.assertTrue(cardPage.isCardPageLoaded(), "Card page was not loaded");
        Assert.assertEquals(PropertyReader.getDataValue("timerStart"), cardPage.getStartTime(), String.format("Time started not from %s %s", PropertyReader.getDataValue("timerStart"), cardPage.getStartTime()));
    }
}
