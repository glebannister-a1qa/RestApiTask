package app.responses;
import app.model.User;
import java.util.List;

public class AllUsersResponse {

    private final int statusCode;
    private final List<User> users;
    private final List<String> contentType;

    public AllUsersResponse(int statusCode, List<User> users, List<String> contentType) {
        this.statusCode = statusCode;
        this.users = users;
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<String> getContentType() {
        return contentType;
    }
}
