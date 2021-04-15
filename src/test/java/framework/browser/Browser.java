package framework.browser;

import org.openqa.selenium.WebDriver;


final public class Browser {
    private Browser() {

    }

    public static WebDriver getDriver() {
        return WebDriverSingleton.getInstance();
    }

    public static void moveTo(String url) {
        getDriver().get(url);
    }

    public static void maximizeWindow() {
        getDriver().manage().window().maximize();
    }

    public static void quitBrowser() {
        getDriver().quit();
    }

    public static void setBrowserNull() {
        WebDriverSingleton.setDriverNull();
    }


}
