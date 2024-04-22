package test;


import dto.ErrorMessageResponse;
import dto.User;
import dto.UserPost;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetAllPostTest extends BaseTest {

    @Test
    public void getAllPosts() {
        Response response = getRequest("/posts", 200);

        List<UserPost> posts = Arrays.asList(response.getBody().as(UserPost[].class));
        List<User> users = Arrays.asList(response.getBody().as(User[].class));
        for (User user : users){
            if(user != null){
                assertNotNull(user);
                assertNotNull(user.getId());

            }
        }

        for (UserPost post : posts) {
            assertNotNull (post.getId ());
            assertNotNull (post.getTitle ());
            assertNotNull (post.getDescription ());

        }
    }

    @Test
    public void testWithSkip1000AdnLimit50(){
        Response response = getRequest("/posts?skip=1000&limit=50", 200);

        assertEquals(200, response.getStatusCode(), "Unexpected status code");
        UserPost[] posts = response.getBody().as(UserPost[].class);
        assertNotNull(posts, "Posts array is null");
        assertEquals(50, posts.length, "Unexpected number of posts returned");

        for (UserPost post : posts) {
            assertNotNull(post.getId(), "Post ID is null");
            assertNotNull(post.getTitle(), "Post title is null");
            assertNotNull(post.getDescription(), "Post description is null");

        }
    }
    @Test
    public void testPaginationWithEmptySkipAndLimit10() {
        Response response = getRequest("/posts?limit=10", 200);

        assertEquals(200, response.getStatusCode(), "Unexpected status code");
        UserPost[] posts = response.getBody().as(UserPost[].class);
        assertNotNull(posts, "Posts array is null");
        assertEquals(10, posts.length, "Unexpected number of posts returned");

        for (UserPost post : posts) {
            assertNotNull(post.getId(), "Post ID is null");
            assertNotNull(post.getTitle(), "Post title is null");
            assertNotNull(post.getDescription(), "Post description is null");
        }
    }

    @Test
    public void testPaginationWithSkip500AndEmptyLimit() {
        Response response = getRequest("/posts?skip=500", 200);

        assertEquals(200, response.getStatusCode(), "Unexpected status code");
        UserPost[] posts = response.getBody().as(UserPost[].class);
        assertNotNull(posts, "Posts array is null");


        for (UserPost post : posts) {
            assertNotNull(post.getId(), "Post ID is null");
            assertNotNull(post.getTitle(), "Post title is null");
            assertNotNull(post.getDescription(), "Post description is null");
        }
    }

    @Test
    public void testWithInvalidData(){
        ErrorMessageResponse errorMessageResponse = getRequest ("/posts?skip=dasdas&limit=",500)
                .body ().jsonPath ().getObject ("",ErrorMessageResponse.class);

        assertFalse (errorMessageResponse.getHttpStatus ().isEmpty ());
        assertFalse (errorMessageResponse.getMessage ().isEmpty ());
        assertTrue (errorMessageResponse.getHttpStatus ().contains ("INTERNAL_SERVER_ERROR"));
    }
    @Test
    public void testWithInvalidEndPoint(){
        Response response = getRequest ("/api/postsd",404);
        assertEquals (404,response.getStatusCode ());
    }

    @Test
    public void testWithoutBody(){
        Response response = getRequestWithoutBody ("/api/posts",401);
        assertEquals(401, response.getStatusCode());
    }




    @Test
    public void getAllPostsAdmin() {
        Response response = getRequestAdmin("/posts", 200);

        List<UserPost> posts = Arrays.asList(response.getBody().as(UserPost[].class));
        List<User> users = Arrays.asList(response.getBody().as(User[].class));
        for (User user : users){
            if(user != null){
                assertNotNull(user);
                assertNotNull(user.getId());

            }
        }

        for (UserPost post : posts) {
            assertNotNull (post.getId ());
            assertNotNull (post.getTitle ());
            assertNotNull (post.getDescription ());

        }
    }

    @Test
    public void testWithSkip1000AndLimit50Admin(){
        Response response = getRequestAdmin("/posts?skip=1000&limit=50", 200);

        assertEquals(200, response.getStatusCode(), "Unexpected status code");
        UserPost[] posts = response.getBody().as(UserPost[].class);
        assertNotNull(posts, "Posts array is null");
        assertEquals(50, posts.length, "Unexpected number of posts returned");

        for (UserPost post : posts) {
            assertNotNull(post.getId(), "Post ID is null");
            assertNotNull(post.getTitle(), "Post title is null");
            assertNotNull(post.getDescription(), "Post description is null");

        }
    }

    @Test
    public void testPaginationWithEmptySkipAndLimit10Admin() {
        Response response = getRequestAdmin("/posts?limit=10", 200);

        assertEquals(200, response.getStatusCode(), "Unexpected status code");
        UserPost[] posts = response.getBody().as(UserPost[].class);
        assertNotNull(posts, "Posts array is null");
        assertEquals(10, posts.length, "Unexpected number of posts returned");

        for (UserPost post : posts) {
            assertNotNull(post.getId(), "Post ID is null");
            assertNotNull(post.getTitle(), "Post title is null");
            assertNotNull(post.getDescription(), "Post description is null");
        }
    }

    @Test
    public void testPaginationWithSkip500AndEmptyLimitAdmin() {
        Response response = getRequestAdmin("/posts?skip=500", 200);

        assertEquals(200, response.getStatusCode(), "Unexpected status code");
        UserPost[] posts = response.getBody().as(UserPost[].class);
        assertNotNull(posts, "Posts array is null");

        for (UserPost post : posts) {
            assertNotNull(post.getId(), "Post ID is null");
            assertNotNull(post.getTitle(), "Post title is null");
            assertNotNull(post.getDescription(), "Post description is null");
        }
    }

    @Test
    public void testWithInvalidDataAdmin(){
        ErrorMessageResponse errorMessageResponse = getRequestAdmin ("/posts?skip=dasdas&limit=",500)
                .body ().jsonPath ().getObject ("",ErrorMessageResponse.class);

        assertFalse (errorMessageResponse.getHttpStatus ().isEmpty ());
        assertFalse (errorMessageResponse.getMessage ().isEmpty ());
        assertTrue (errorMessageResponse.getHttpStatus ().contains ("INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void testWithInvalidEndPointAdmin(){
        Response response = getRequestAdmin ("/api/postsd",404);
        assertEquals (404,response.getStatusCode ());
    }

    @Test
    public void testWithoutBodyAdmin(){
        Response response = getRequestWithoutBody ("/api/posts",401);
        assertEquals(401, response.getStatusCode());
    }
}

