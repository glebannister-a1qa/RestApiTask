package framework.utils;
import app.constants.RequestHeaderConstants;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class APIUtils {

    public static HttpResponse<String> get(String uri) {
        HttpResponse<String> result = null;
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();
            result = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            LogUtil.error(e.toString());
        }
        return result;
    }

    public static HttpResponse<String> post(String uri, String body, String contentType, String typeValue) {
        HttpResponse<String> result = null;
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(uri))
                    .header(contentType, typeValue)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();
            result = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            LogUtil.error(e.toString());
        }
        return result;
    }

    public static <T> String getResponseBody(HttpResponse<T> response) {
        return (String) response.body();
    }

    public static <T> int getResponseStatusCode(HttpResponse<T> response) {
        return response.statusCode();
    }

    public static <T> List<String> getContentTypeHeader(HttpResponse<T> response) {
        return response.headers().allValues(RequestHeaderConstants.CONTENT_TYPE_HEADER);
    }
}
