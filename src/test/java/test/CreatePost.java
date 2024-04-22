package test;

import dto.CreateUserPostResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePost extends BaseTest {
    @Test
    public void createPost(){
        CreateUserPostResponse postRequest = new CreateUserPostResponse();
        postRequest.setTitle("Новый пост");
        postRequest.setDescription("Описание нового поста");
        postRequest.setBody("Тело нового поста");
        postRequest.setDraft("true");
        Response response = createPost(postRequest, 201);

        assertEquals(201, response.getStatusCode(), "Unexpected status code");
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("id"), "Response body does not contain expected data");
        assertTrue(responseBody.contains("title"), "Response body does not contain expected data");
        assertTrue(responseBody.contains("description"), "Response body does not contain expected data");
        assertTrue(responseBody.contains("body"), "Response body does not contain expected data");
        assertTrue(responseBody.contains("imageUrl"), "Response body does not contain expected data");
        assertTrue(responseBody.contains("publishDate"), "Response body does not contain expected data");
        assertTrue(responseBody.contains("draft"), "Response body does not contain expected data");
        assertTrue(responseBody.contains("userId"), "Response body does not contain expected data");
    }
    @Test
    public void createPostUnauthorized(){
        CreateUserPostResponse postRequest = new CreateUserPostResponse();
        postRequest.setTitle("");
        postRequest.setDescription("");
        postRequest.setBody("");
        postRequest.setDraft("");

        Response response = createRequestWithoutBody("/api/post", 404);
        assertEquals(404, response.getStatusCode(), "Unexpected status code");
    }
    @Test
    public void createPostStatusError(){
        CreateUserPostResponse postRequest = new CreateUserPostResponse();
        createRequestWithoutBodyAndToken ("/api/post", 401);
        postRequest.setTitle("string");
        postRequest.setDescription("string");
        postRequest.setBody("string");
        postRequest.setDraft("true");


    }

    @Test
    public void createPostBadRequest(){
        CreateUserPostResponse postRequest = new CreateUserPostResponse("","","","","","","","","");
        Response response = createPost(postRequest, 400);
        assertEquals(400, response.getStatusCode(), "Unexpected status code");

       String responseBody = response.getBody ().asString ();
       assertTrue (responseBody.contains ("description"),"Description can not be empty!");
       assertTrue (responseBody.contains ("description"),"Description must contain from 1 to 100 characters");

       assertTrue (responseBody.contains ("title"),"Title must contain from 1 to 40 characters");
       assertTrue (responseBody.contains ("title"),"Title can not be empty!");

       assertTrue (responseBody.contains ("body"),"Body must contain from 1 to 1000 characters");
       assertTrue (responseBody.contains ("body"),"Body can not be empty!");
    }

    @Test
    public void createPostWithoutTitle(){
        CreateUserPostResponse postRequest = new CreateUserPostResponse("","","dsdsd","dsdsd","","","","true","");
        Response response = createPost(postRequest, 200);
        String responseBody = response.getBody ().asString ();

        assertTrue (responseBody.contains ("title"),"Title must contain from 1 to 40 characters");
        assertTrue (responseBody.contains ("title"),"Title can not be empty!");

        assertFalse (postRequest.getDescription ().isEmpty ());
        assertFalse (postRequest.getBody ().isEmpty ());
    }

    @Test
    public void createPostWithoutDescription(){
        CreateUserPostResponse postRequest = new CreateUserPostResponse("","dsdsd","","dsdsd","","","","true","");
        Response response = createPost(postRequest, 200);
        String responseBody = response.getBody ().asString ();

        assertTrue (responseBody.contains ("description"),"Description can not be empty!");
        assertTrue (responseBody.contains ("description"),"Description must contain from 1 to 100 characters");
    }




}
