package framework.browser;

import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.HashMap;
import java.util.Map;

final public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private static WebDriver initDriver() {
        String browser = PropertyReader.getConfigValue("browser");
        switch (browser) {
            case "Chrome":
                DesiredCapabilities jsCapabilities = chromeBrowser();
                driver = new ChromeDriver(jsCapabilities);
                break;
            case "Firefox":
                FirefoxProfile profile = firefoxBrowser();
                driver = new FirefoxDriver(profile);
                break;
            default:
                LogUtil.error("Invalid browser");
                break;
        }
        return driver;
    }

    public static WebDriver setDriverNull() {
        return driver = null;
    }


    public static DesiredCapabilities chromeBrowser() {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities jsCapabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", PropertyReader.getConfigValue("language"));
        options.setExperimentalOption("prefs", prefs);
        jsCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return jsCapabilities;
    }

    public static FirefoxProfile firefoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", PropertyReader.getConfigValue("language"));
        return profile;
    }
}