package framework.elements;

import framework.utils.LogUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class CheckBox extends BaseElement {

    private List<WebElement> listOfElement = null;

    public CheckBox(By locator, String name) {
        super(locator, name);
    }

    public void clickOnCheckBoxByName(String text) {
        for (int i = 0; i < listOfElement.size(); i++) {
            if (listOfElement.get(i).getText().equals(text)) {
                LogUtil.info(String.format("Text of interest %s", listOfElement.get(i).getText()));
                clickOnElementOfListOfElementsOfAnotherList(By.xpath("//div[@class='avatar-and-interests__interests" +
                                "-list__item']/div/span/label"),
                        i);
                break;
            }
        }
    }

    public List<String> getCheckBoxesNames() {
        listOfElement = findElements();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < listOfElement.size(); i++){
            names.add(listOfElement.get(i).getText());
            if (names.get(i).equals("Select all")){
                names.remove(i);
                break;
            }
            LogUtil.info("Names of all interests");
            LogUtil.info(names.get(i));
        }
        return names;
    }

    public void clickAcceptCheckBox() {
        LogUtil.info("Clicking on CheckBox");
        if (!findElement().isSelected()) {
            findElement().click();
        }
    }
}
