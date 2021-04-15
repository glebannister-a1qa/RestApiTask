package app.responses;
import app.model.User;
import java.util.List;

public class SpecificUserResponse {

    private final int statusCode;
    private final User user;
    private final List<String> contentType;

    public SpecificUserResponse(int statusCode, User user, List<String> contentType) {
        this.statusCode = statusCode;
        this.user = user;
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public User getUser() {
        return user;
    }

    public List<String> getContentType() {
        return contentType;
    }
}
