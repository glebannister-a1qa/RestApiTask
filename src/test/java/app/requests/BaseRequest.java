package app.requests;
import app.constants.RegExpConstants;
import framework.utils.APIUtils;
import framework.utils.StringUtil;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class BaseRequest {

    public static List<String> getContentType(HttpResponse<String> result) {
        String contentTypesResponse = APIUtils.getContentTypeHeader(result).stream().findFirst().get();
        return Arrays.asList(
                StringUtil.replaceWithRegExpWithEmptyString(
                        contentTypesResponse,
                        RegExpConstants.squareBrackets
                ).split(RegExpConstants.semicolonAndSpace)
        );
    }
}
