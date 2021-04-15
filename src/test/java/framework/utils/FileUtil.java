package framework.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class FileUtil {
    public static String correctPathToFile() {
        String resourcePath = PropertyReader.getConfigValue("pathToResources");
        return System.getProperty("user.dir")
                + System.getProperty("file.separator") + resourcePath.substring(0, 3)
                + System.getProperty("file.separator") + resourcePath.substring(4, 8)
                + System.getProperty("file.separator") + resourcePath.substring(9, 18)
                + System.getProperty("file.separator") + resourcePath.substring(19, 22);
    }

    public static String getBase64(String path) {
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            LogUtil.error(String.format("Error while read file to byte array %s, s", e));
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static void writeToFile(List<String> resultList, String pathToFile) {
        try (FileWriter writer = new FileWriter(pathToFile)) {
            for (String str : resultList) {
                writer.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFileWithQueryResultNotEmpty(String pathToFile){
        return new File(pathToFile).length() > 0;
    }
}
