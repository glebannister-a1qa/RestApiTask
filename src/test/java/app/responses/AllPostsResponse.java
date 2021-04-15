package app.responses;
import app.model.Post;
import java.util.List;

public class AllPostsResponse {

    private final int statusCode;
    private final List<Post> posts;
    private final List<String> contentType;

    public AllPostsResponse(int statusCode, List<Post> posts, List<String> contentType) {
        this.statusCode = statusCode;
        this.posts = posts;
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<String> getContentType() {
        return contentType;
    }
}
