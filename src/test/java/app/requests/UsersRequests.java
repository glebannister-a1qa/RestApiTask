package app.requests;
import app.constants.TextConstants;
import app.model.User;
import app.responses.AllUsersResponse;
import app.responses.SpecificUserResponse;
import framework.utils.APIUtils;
import framework.utils.JsonUtil;
import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class UsersRequests {

    private static final String ALL_USERS_URL = PropertyReader.getConfigValue("urlApi")
            + PropertyReader.getDataValue("usersEndpoint");

    public static AllUsersResponse getAllUsers() {
        LogUtil.info("Getting all users");
        HttpResponse<String> result = APIUtils.get(ALL_USERS_URL);
        return getAllUsers(result);
    }

    public static SpecificUserResponse getSpecialUser(int numberOfUser) {
        LogUtil.info(String.format("Getting specific user %s", numberOfUser));
        String url = ALL_USERS_URL + TextConstants.SYMBOL_SLASH + numberOfUser;
        HttpResponse<String> result = APIUtils.get(url);
        return getSpecificUser(result);
    }

    private static AllUsersResponse getAllUsers(HttpResponse<String> result) {
        return new AllUsersResponse(
                APIUtils.getResponseStatusCode(result),
                Arrays.asList(JsonUtil.fromJsonStringAsArray(APIUtils.getResponseBody(result), User[].class)),
                BaseRequest.getContentType(result)
        );
    }

    private static SpecificUserResponse getSpecificUser(HttpResponse<String> result) {
        return new SpecificUserResponse(
                APIUtils.getResponseStatusCode(result),
                JsonUtil.fromJsonString(APIUtils.getResponseBody(result), User.class),
                BaseRequest.getContentType(result)
        );
    }
}
