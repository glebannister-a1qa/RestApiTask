package app.responses;
import app.model.Post;
import java.util.List;

public class SpecificPostResponse {

    private final int statusCode;
    private final Post post;
    private final List<String> contentType;

    public SpecificPostResponse(int statusCode, Post post, List<String> contentType) {
        this.statusCode = statusCode;
        this.post = post;
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Post getPost() {
        return post;
    }

    public List<String> getContentType() {
        return contentType;
    }
}
