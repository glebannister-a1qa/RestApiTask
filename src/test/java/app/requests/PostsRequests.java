package app.requests;
import app.constants.TextConstants;
import app.model.Post;
import app.responses.AllPostsResponse;
import app.responses.SpecificPostResponse;
import app.constants.RequestHeaderConstants;
import framework.utils.APIUtils;
import framework.utils.JsonUtil;
import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class PostsRequests {

    private static final String ALL_POSTS_URL = PropertyReader.getConfigValue("urlApi")
            + PropertyReader.getDataValue("postsEndpoint");

    public static AllPostsResponse getAllPosts() {
        LogUtil.info("Getting all posts");
        HttpResponse<String> result = APIUtils.get(PostsRequests.ALL_POSTS_URL);
        return getAllPosts(result);
    }

    public static SpecificPostResponse getSpecialPost(int numberOfPost) {
        LogUtil.info(String.format("Getting specific post %s", numberOfPost));
        String url = ALL_POSTS_URL + TextConstants.SYMBOL_SLASH + numberOfPost;
        HttpResponse<String> result = APIUtils.get(url);
        return getSpecificPost(result);
    }

    public static SpecificPostResponse sendPost(Post sendingPost) {
        LogUtil.info("Sending post request");
        HttpResponse<String> result = APIUtils.post(
                PostsRequests.ALL_POSTS_URL, JsonUtil.toJsonString(sendingPost),
                RequestHeaderConstants.CONTENT_TYPE_HEADER,
                RequestHeaderConstants.HEADER_VALUE
        );
        LogUtil.info(String.format("Request body %s", result.body()));
        return getSpecificPost(result);
    }

    private static AllPostsResponse getAllPosts(HttpResponse<String> result) {
        return new AllPostsResponse(
                APIUtils.getResponseStatusCode(result),
                Arrays.asList(JsonUtil.fromJsonStringAsArray(APIUtils.getResponseBody(result), Post[].class)),
                BaseRequest.getContentType(result)
        );
    }

    private static SpecificPostResponse getSpecificPost(HttpResponse<String> result) {
        return new SpecificPostResponse(
                APIUtils.getResponseStatusCode(result),
                JsonUtil.fromJsonString(APIUtils.getResponseBody(result), Post.class),
                BaseRequest.getContentType(result)
        );
    }
}
