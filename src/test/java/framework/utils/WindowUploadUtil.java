package framework.utils;

import app.constants.WaiterConstants;
import framework.emunUtil.UploadWindowHotKeys;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class WindowUploadUtil {
    public static void uploadAvatarImage(String path) {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(WaiterConstants.WAIT_INTERVAL_2000);
            StringSelection stringSelection = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            robot.setAutoDelay(WaiterConstants.WAIT_INTERVAL_1000);
            robot.keyPress(UploadWindowHotKeys.VK_CONTROL.getKey());
            robot.keyPress(UploadWindowHotKeys.VK_V.getKey());
            robot.keyPress(UploadWindowHotKeys.VK_E.getKey());
            robot.keyRelease(UploadWindowHotKeys.VK_CONTROL.getKey());
            robot.keyRelease(UploadWindowHotKeys.VK_V.getKey());
            robot.setAutoDelay(WaiterConstants.WAIT_INTERVAL_1000);
        } catch (AWTException e) {
            LogUtil.error(String.format("Caught error when upload image %s", e));
        }
    }
}
