package framework.waits;

import app.constants.WaiterConstants;
import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Waits {
    public static void waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), WaiterConstants.WAIT_6_SEC, WaiterConstants.WAIT_2_SEC);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static void waitUntilNotVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), WaiterConstants.WAIT_12_SEC, WaiterConstants.WAIT_2_SEC);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), WaiterConstants.WAIT_12_SEC, WaiterConstants.WAIT_2_SEC);
        //wait.until(ExpectedConditions.elementToBeClickable(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
