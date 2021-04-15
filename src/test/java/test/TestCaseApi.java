package test;
import app.constants.TextConstants;
import app.model.Post;
import app.model.User;
import app.requests.PostsRequests;
import app.requests.UsersRequests;
import app.responses.AllPostsResponse;
import app.responses.AllUsersResponse;
import app.responses.SpecificPostResponse;
import app.responses.SpecificUserResponse;
import app.constants.RequestHeaderConstants;
import framework.utils.JsonUtil;
import framework.utils.LogUtil;
import framework.utils.PropertyReader;
import framework.utils.RandomUtil;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseApi extends BaseTestApi{

    @Test
    public void restApiTest() {
        LogUtil.step(1,
                String.format(
                        "Step go to %s take list in JSON format, data will be sorted by id order",
                        PropertyReader.getConfigValue("url")
                )
        );
        AllPostsResponse allPostsResponse = PostsRequests.getAllPosts();
        Assert.assertEquals(allPostsResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertTrue(
                allPostsResponse.getContentType().contains(RequestHeaderConstants.HEADER_VALUE),
                "Response is not in JSON format"
        );
        boolean isSorted = true;
        for (int i = 0; i < allPostsResponse.getPosts().size() - 1; i++) {
            if (allPostsResponse.getPosts().get(i).getId() > allPostsResponse.getPosts().get(i + 1).getId()) {
                isSorted = false;
                break;
            }
        }
        Assert.assertTrue(isSorted, "Post array is not sorted by id");

        LogUtil.step(
                2,
                "Step make GET request for get post99 and check value of id userId body and" +
                        " title"
        );
        SpecificPostResponse specificPostResponse99 = PostsRequests.getSpecialPost(
                Integer.parseInt(PropertyReader.getDataValue("post99number"))
        );
        long expectedSpecificPost99UserId = Long.parseLong(PropertyReader.getDataValue("expectedPost99UserId"));
        long expectedSpecificPost99Id = Long.parseLong(PropertyReader.getDataValue("expectedPost99Id"));
        Assert.assertEquals(specificPostResponse99.getStatusCode(),
                HttpStatus.SC_OK,
                String.format("Codes are not match %s %s",
                        specificPostResponse99.getStatusCode(),
                        HttpStatus.SC_OK
                )
        );
        Assert.assertEquals(specificPostResponse99.getPost().getUserId(), expectedSpecificPost99UserId,
                String.format("UserId are not match %s %s",
                        specificPostResponse99.getPost().getUserId(),
                        expectedSpecificPost99UserId
                )
        );
        Assert.assertEquals(specificPostResponse99.getPost().getId(), expectedSpecificPost99Id,
                String.format("Id are not match %s %s",
                        specificPostResponse99.getPost().getId(),
                        expectedSpecificPost99UserId
                )
        );
        Assert.assertNotNull(specificPostResponse99.getPost().getBody(), "Body is empty");
        Assert.assertNotNull(specificPostResponse99.getPost().getTitle(), "Title is empty");

        LogUtil.step(3, "Step make GET request for get post150, body of response will be empty");
        SpecificPostResponse specificPostResponse150 = PostsRequests.getSpecialPost(
                Integer.parseInt(PropertyReader.getDataValue("post150number"))
        );
        Assert.assertEquals(
                specificPostResponse150.getStatusCode(),
                HttpStatus.SC_NOT_FOUND,
                String.format("Codes are not match %s %s",
                        specificPostResponse150.getStatusCode(),
                        HttpStatus.SC_NOT_FOUND
                )
        );
        Assert.assertNull(specificPostResponse150.getPost().getBody(), "Body is not empty");
        Assert.assertNull(specificPostResponse150.getPost().getTitle(), "Title is not empty");
        Assert.assertEquals(
                specificPostResponse150.getPost().getId(),
                Long.parseLong(PropertyReader.getDataValue("nullId")),
                String.format("Id are not match %s %s",
                        specificPostResponse150.getPost().getId(),
                        Long.parseLong(PropertyReader.getDataValue("nullId"))
                )
        );
        Assert.assertEquals(
                specificPostResponse150.getPost().getUserId(),
                Long.parseLong(PropertyReader.getDataValue("nullUserId")),
                String.format("Id are not match %s %s",
                        specificPostResponse150.getPost().getUserId(),
                        Long.parseLong(PropertyReader.getDataValue("nullUserId"))
                )
        );

        LogUtil.step(4, "Step make POST request and check if body,id,userId are equals" +
                " to the request data, id is in response");
        Post customPost = new Post();
        customPost.setId(Long.parseLong(PropertyReader.getDataValue("id101")));
        customPost.setUserId(Long.parseLong(PropertyReader.getDataValue("userId11")));
        customPost.setBody(RandomUtil.getRandomString(TextConstants.LENGTH_OF_TEXT));
        customPost.setTitle(RandomUtil.getRandomString(TextConstants.LENGTH_OF_TEXT));
        SpecificPostResponse customSpecificPostResponse = PostsRequests.sendPost(customPost);
        Assert.assertEquals(
                customSpecificPostResponse.getStatusCode(),
                HttpStatus.SC_CREATED,
                String.format("Codes are not match %s %s",
                        customSpecificPostResponse.getStatusCode(),
                        HttpStatus.SC_CREATED
                )
        );
        Assert.assertEquals(
                customSpecificPostResponse.getPost().getTitle(),
                customPost.getTitle(),
                String.format("Titles are not match %s %s",
                        customSpecificPostResponse.getPost().getTitle(),
                        customPost.getTitle()
                )
        );
        Assert.assertEquals(
                customSpecificPostResponse.getPost().getBody(),
                customPost.getBody(),
                String.format("Bodies are not match %s %s",
                        customSpecificPostResponse.getPost().getBody(),
                        customPost.getBody()
                )
        );
        Assert.assertEquals(
                customSpecificPostResponse.getPost().getUserId(),
                customPost.getUserId(),
                String.format("UserIds are not match %s %s",
                        customSpecificPostResponse.getPost().getUserId(),
                        customPost.getUserId()
                )
        );
        Assert.assertNotNull(
                String.valueOf(customSpecificPostResponse.getPost().getId()),
                "Id is empty");

        LogUtil.step(
                5,
                "Step make GET request for /users, response is in JSON format and user with id 5 have special" +
                        " data"
        );
        AllUsersResponse allUsersResponse = UsersRequests.getAllUsers();
        Assert.assertEquals(
                allUsersResponse.getStatusCode(),
                HttpStatus.SC_OK,
                String.format("Codes are not match %s %s",
                        allUsersResponse.getStatusCode(), HttpStatus.SC_OK
                )
        );
        Assert.assertTrue(allUsersResponse.getContentType().contains("application/json"));
        User fifthUser = allUsersResponse.getUsers().get(
                Integer.parseInt(PropertyReader.getDataValue("fifthUser")
                )
        );
        User expectedFifthUser = JsonUtil.fromJsonFile(PropertyReader.getDataValue("pathToUser5Json"), User.class);
        Assert.assertEquals(
                fifthUser.getName(),
                expectedFifthUser.getName(),
                String.format("Names are not match %s %s",
                        fifthUser.getName(),
                        expectedFifthUser.getName()
                )
        );
        Assert.assertEquals(
                fifthUser.getUserName(),
                expectedFifthUser.getUserName(),
                String.format("Usernames are not match %s %s",
                        fifthUser.getUserName(),
                        expectedFifthUser.getUserName()
                )
        );
        Assert.assertEquals(
                fifthUser.getEmail(),
                expectedFifthUser.getEmail(),
                String.format("Emails are not match %s %s",
                        fifthUser.getEmail(),
                        expectedFifthUser.getEmail()
                )
        );
        Assert.assertEquals(
                fifthUser.getAddress().getStreet(),
                expectedFifthUser.getAddress()
                        .getStreet(),
                String.format("Streets are not match %s %s",
                        fifthUser.getAddress().getStreet(),
                        expectedFifthUser.getAddress().getStreet()
                )
        );
        Assert.assertEquals(
                fifthUser.getAddress().getSuite(),
                expectedFifthUser.getAddress().getSuite(),
                String.format("Suits are not match %s %s",
                        fifthUser.getAddress().getSuite(),
                        expectedFifthUser.getAddress().getSuite()
                )
        );
        Assert.assertEquals(
                fifthUser.getAddress().getCity(),
                expectedFifthUser.getAddress().getCity(),
                String.format("Cities are not match %s %s",
                        fifthUser.getAddress().getCity(),
                        expectedFifthUser.getAddress().getCity()
                )
        );
        Assert.assertEquals(
                fifthUser.getAddress().getZipcode(),
                expectedFifthUser.getAddress().getZipcode(),
                String.format("Zipcodes are not match %s %s",
                        fifthUser.getAddress().getZipcode(),
                        expectedFifthUser.getAddress().getZipcode()
                )
        );
        Assert.assertEquals(
                fifthUser.getAddress().getGeo().getLat(),
                expectedFifthUser.getAddress().getGeo().getLat(),
                String.format("Lats are not match %s %s",
                        fifthUser.getAddress().getGeo().getLat(),
                        expectedFifthUser.getAddress().getGeo().getLat()
                )
        );
        Assert.assertEquals(
                fifthUser.getAddress().getGeo().getLng(),
                expectedFifthUser.getAddress().getGeo().getLng(),
                String.format("Lngs are not match %s %s",
                        fifthUser.getAddress().getGeo().getLng(),
                        expectedFifthUser.getAddress().getGeo().getLng()
                )
        );
        Assert.assertEquals(
                fifthUser.getPhone(),
                expectedFifthUser.getPhone(),
                String.format("Phones are not match %s %s",
                        fifthUser.getPhone(),
                        expectedFifthUser.getPhone()
                )
        );
        Assert.assertEquals(
                fifthUser.getWebsite(),
                expectedFifthUser.getWebsite(),
                String.format("Websites are not match %s %s",
                        fifthUser.getWebsite(),
                        expectedFifthUser.getWebsite()
                )
        );
        Assert.assertEquals(
                fifthUser.getCompany().getName(),
                expectedFifthUser.getCompany().getName(),
                String.format("Company names are not match %s %s",
                        fifthUser.getCompany().getName(),
                        expectedFifthUser.getCompany().getName()
                )
        );
        Assert.assertEquals(
                fifthUser.getCompany().getCatchPhrase(),
                expectedFifthUser.getCompany().getCatchPhrase(),
                String.format("Catchphrases are not match %s %s",
                        fifthUser.getCompany().getName(),
                        expectedFifthUser.getCompany().getCatchPhrase()
                )
        );
        Assert.assertEquals(
                fifthUser.getCompany().getBs(),
                expectedFifthUser.getCompany().getBs(),
                String.format("Bs are not match %s %s",
                        fifthUser.getCompany().getBs(),
                        expectedFifthUser.getCompany().getBs()
                )
        );

        LogUtil.step(6, "Step make GET request for /users/5, data equals to data in step 5");
        SpecificUserResponse specificUserResponse5 = UsersRequests.getSpecialUser(
                Integer.parseInt(PropertyReader.getDataValue("user5number")
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getStatusCode(), HttpStatus.SC_OK,
                String.format("Codes are not match %s %s",
                        specificUserResponse5.getStatusCode(),
                        HttpStatus.SC_OK
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getName(),
                fifthUser.getName(),
                String.format("Names are not match %s %s",
                        specificUserResponse5.getUser().getName(),
                        fifthUser.getName()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getUserName(),
                fifthUser.getUserName(),
                String.format("Usernames are not match %s %s",
                        specificUserResponse5.getUser().getUserName(),
                        fifthUser.getUserName()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getEmail(),
                fifthUser.getEmail(),
                String.format("Emails are not match %s %s",
                        specificUserResponse5.getUser().getEmail(),
                        fifthUser.getEmail()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getAddress().getStreet(),
                fifthUser.getAddress().getStreet(),
                String.format("Streets are not match %s %s",
                        specificUserResponse5.getUser().getAddress().getStreet(),
                        fifthUser.getAddress().getStreet()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getAddress().getSuite(),
                fifthUser.getAddress().getSuite(),
                String.format("Suits are not match %s %s",
                        specificUserResponse5.getUser().getAddress().getSuite(),
                        fifthUser.getAddress().getSuite()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getAddress().getCity(),
                fifthUser.getAddress().getCity(),
                String.format("Cities are not match %s %s",
                        specificUserResponse5.getUser().getAddress().getCity(),
                        fifthUser.getAddress().getCity()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getAddress().getZipcode(),
                fifthUser.getAddress().getZipcode(),
                String.format("Zipcodes are not match %s %s",
                        specificUserResponse5.getUser().getAddress().getZipcode(),
                        fifthUser.getAddress().getZipcode()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getAddress().getGeo().getLat(),
                fifthUser.getAddress().getGeo().getLat(),
                String.format("Lats are not match %s %s",
                        specificUserResponse5.getUser().getAddress().getGeo().getLat(),
                        fifthUser.getAddress().getGeo().getLat()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getAddress().getGeo().getLng(),
                fifthUser.getAddress().getGeo().getLng(),
                String.format("Lngs are not match %s %s",
                        specificUserResponse5.getUser().getAddress().getGeo().getLng(),
                        fifthUser.getAddress().getGeo().getLng()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getPhone(),
                fifthUser.getPhone(),
                String.format("Phones are not match %s %s",
                        specificUserResponse5.getUser().getPhone(),
                        fifthUser.getPhone()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getWebsite(),
                fifthUser.getWebsite(),
                String.format("Websites are not match %s %s",
                        specificUserResponse5.getUser().getWebsite(),
                        fifthUser.getWebsite()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getCompany().getName(),
                fifthUser.getCompany().getName(),
                String.format("Company names are not match %s %s",
                        specificUserResponse5.getUser().getCompany().getName(),
                        fifthUser.getName()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getCompany().getCatchPhrase(),
                fifthUser.getCompany().getCatchPhrase(),
                String.format("Catchphrases are not match %s %s",
                        specificUserResponse5.getUser().getCompany().getCatchPhrase(),
                        fifthUser.getCompany().getCatchPhrase()
                )
        );
        Assert.assertEquals(
                specificUserResponse5.getUser().getCompany().getBs(),
                fifthUser.getCompany().getBs(),
                String.format("Bs are not match %s %s",
                        specificUserResponse5.getUser().getCompany().getBs(),
                        fifthUser.getCompany().getBs()
                )
        );
    }

}
