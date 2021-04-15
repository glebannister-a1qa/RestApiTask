package framework.elements;

import framework.utils.LogUtil;
import framework.utils.StringUtil;
import org.openqa.selenium.By;

public class Image extends BaseElement {
    public Image(By locator, String name) {
        super(locator, name);
    }

    public String getImageBase64() {
        LogUtil.info(String.format("Getting image base64 %s, %s", locator, name));
        return StringUtil.cutFromSpecifiedMomentToTheEnd(getAttribute("style"), 46);
    }
}
