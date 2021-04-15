package framework.utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonUtil {

    public static <T> T[] fromJsonStringAsArray(String json, Class<T[]> className) {
        ObjectReader reader = new ObjectMapper().readerFor(className);
        T[] result = null;
        try {
            result = reader.readValue(json);
        } catch (IOException e) {
            LogUtil.error(e.toString());
        }
        return result;
    }
    public static <T> T fromJsonString(String json, Class<T> className) {
        ObjectReader reader = new ObjectMapper().readerFor(className);
        T result = null;
        try {
            result = reader.readValue(json);
        } catch (IOException e) {
            LogUtil.error(e.toString());
        }
        return result;
    }
    public static <T> String toJsonString(T object) {
        ObjectWriter writer = new ObjectMapper().writerFor(object.getClass());
        String result = null;
        try {
            result = writer.writeValueAsString(object);
        } catch (JsonProcessingException jpe) {
            LogUtil.error(jpe.toString());
        }
        return result;
    }
    public static <T> T fromJsonFile(String fileName, Class<T> className) {
        ObjectMapper mapper = new ObjectMapper();
        T result = null;
        try {
            result = mapper.readValue(Paths.get(fileName).toFile(), className);
        } catch (IOException e) {
            LogUtil.error(e.toString());
        }
        return result;
    }
}
