package framework.utils;

public class StringUtil {

    private static final String EMPTY_STRING = "";

    public static String cutFromSpecifiedMomentToTheEnd(String text, int index) {
        return text.substring(index);
    }

    public static String getCorrectInterestName(String name) {
        return name.replaceAll("\\W", "").toLowerCase();
    }

    public static String getSubstringWithTwoRegulars(String base64, String regularFirst, String regularSecond) {
        String firstCut = base64.replace(regularFirst, "");
        return firstCut.replace(regularSecond, "");
    }

    public static String replaceWithRegExpWithEmptyString(String string, String regExp) {
        return replaceWithRegExp(string, regExp, EMPTY_STRING);
    }
    public static String replaceWithRegExp(String string, String regExp, String replacement) {
        return string.replaceAll(regExp, replacement);
    }
}
