package test;

import dto.ErrorMessageResponse;
import dto.User;
import dto.UserPost;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetPostByID extends BaseTest{
    @Test
    public void getPostById() {
        Response response = getRequest("/posts/" + "b855905e-4e96-4784-b5df-74fc301e0e04", 200);
        assertTrue(response.getStatusCode() == 200, "Expected status code 200");
        UserPost post = response.as(UserPost.class);
        assertNotNull(post, "Post object is null");
        assertNotNull(post.getId(), "Post id is null");
        assertFalse(post.getId().isEmpty(), "Post id is empty");
    }
    @Test
    public void getPostByIdInvalidEndPoint() {
        Response response = getRequest("/postss/" + "b855905e-4e96-4784-b5df-74fc301e0e04", 404);
        assertTrue(response.getStatusCode() == 404, "Expected status code 404");
    }

    @Test
    public void getPostByEmptyBody(){
        Response response = getRequestWithoutBody ("/posts/" + "b855905e-4e96-4784-b5df-74fc301e0e04", 401);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"UNAUTHORIZED");
        assertTrue (responseBody.contains ("message"),"Authentication failed: Full authentication is required to access this resource");
        assertTrue(response.getStatusCode() == 401, "Expected status code 404");
    }


    @Test
    public void getPostByIdAdmin() {
        Response response = getRequestAdmin ("/posts/" + "934946e1-8dca-44ff-bd82-1abe3db3e87e", 200);
        assertTrue(response.getStatusCode() == 200, "Expected status code 200");
        UserPost post = response.as(UserPost.class);
        assertNotNull(post, "Post object is null");
        assertNotNull(post.getId(), "Post id is null");
        assertFalse(post.getId().isEmpty(), "Post id is empty");
    }
    @Test
    public void getPostByIdInvalidEndPointAdmin() {
        Response response = getRequest("/postss/" + "b855905e-4e96-4784-b5df-74fc301e0e04", 404);
        assertTrue(response.getStatusCode() == 404, "Expected status code 404");
    }

    @Test
    public void getPostByEmptyBodyAdmin(){
        Response response = getRequestWithoutBody ("/posts/" + "b855905e-4e96-4784-b5df-74fc301e0e04", 401);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"UNAUTHORIZED");
        assertTrue (responseBody.contains ("message"),"Authentication failed: Full authentication is required to access this resource");
        assertTrue(response.getStatusCode() == 401, "Expected status code 404");
    }
}
