package framework.utils;

import java.util.Random;

public class RandomUtil {

    public final static String RANGE_OF_SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    public static String getRandomStringWithRange(String range, int number) {
        char[] chars = range.toCharArray();
        StringBuilder sb = new StringBuilder(number);
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getRandomString(int number) {
        char[] chars = RANGE_OF_SMALL_LETTERS.toCharArray();
        StringBuilder sb = new StringBuilder(number);
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static int getRandomInt(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }
}
