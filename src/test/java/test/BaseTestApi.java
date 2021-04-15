package test;

import framework.browser.Browser;
import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestApi {

//    @BeforeMethod
//    public void initDriver() {
//        LogUtil.info(String.format("Go to %s", PropertyReader.getConfigValue("url")));
//        Browser.moveTo(PropertyReader.getConfigValue("urlApi"));
//        Browser.maximizeWindow();
//    }
//
//    @AfterMethod
//    public void closeDriver() {
//        LogUtil.info("Close browser");
//        Browser.quitBrowser();
//        Browser.setBrowserNull();
//    }
}
