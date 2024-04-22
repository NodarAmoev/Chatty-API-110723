package test;


import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetPostByUserTest extends BaseTest{

    @Test
    public void getPostByUser(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54d38/posts?skip=1&limit=1";
        Response response = getRequest ("/users/" + userId,200);
        String responseBody = response.getBody ().asString ();
        assertEquals (200,response.getStatusCode ());

        assertTrue (responseBody.contains ("id"),"09c0e977-083d-4603-8c76-822e98131e32");
        assertTrue (responseBody.contains ("title"),"My new Post as User");
        assertTrue (responseBody.contains ("description"),"My Dogg");
        assertTrue (responseBody.contains ("body"),"Hello this is my new Post");
        assertTrue (responseBody.contains ("imageUrl"),"https://chatty-images-s3.s3.eu-central-1.amazonaws.com/e97e30ae-3b77-4ff5-a829-4ecabb76a1b7/1e80e3d9-c1b2-40d7-9f3f-1519a4b1a63f.jpg");
        assertTrue (responseBody.contains ("createdAt"),"2024-04-21T09:25:07.100Z");
        assertTrue (responseBody.contains ("updatedAt"),"2024-04-21T09:25:07.100Z");

        assertTrue (responseBody.contains ("id"),"95b5c981-650b-46e6-ad41-08df96d54d3");
        assertTrue (responseBody.contains ("name"),"Alex");
        assertTrue (responseBody.contains ("surname"),"Rashevchenko");
        assertTrue (responseBody.contains ("phone"),"+79717901351");
        assertTrue (responseBody.contains ("email"),"alexxxx@gmail.com");
        assertTrue (responseBody.contains ("role"),"USER");
        assertTrue (responseBody.contains ("gender"),"MALE");
        assertTrue (responseBody.contains ("birthDate"),"null");
        assertTrue (responseBody.contains ("avatarUrl"),"https://chatty-images-s3.s3.eu-central-1.amazonaws.com/95b5c981-650b-46e6-ad41-08df96d54d38/6471404c-3970-4909-a686-5295964f3557.jpg");
        assertTrue (responseBody.contains ("backgroundUrl"),"null");
    }

    @Test
    public void getPostByUserIdWithInvalidEndPoint(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54d38/posts?skip=1&limit=1";
        Response response = getRequest ("/userss/" + userId,404);
        assertEquals (404,response.getStatusCode ());
        assertEquals ("HTTP/1.1 404 ",response.getStatusLine ());
    }
    @Test
    public void getPostByUserIdWithoutBody(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54d38/posts?skip=1&limit=1";
        Response response = getRequestWithoutBody ("/users/" + userId,401);
        String responseBody = response.getBody ().asString ();

        assertTrue (responseBody.contains ("httpStatus"),"UNAUTHORIZED");
        assertTrue (responseBody.contains ("message"),"Authentication failed: Full authentication is required to access this resource");
    }

    @Test
    public void getPostByUserWithAdmin(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54d38/posts?skip=1&limit=1";

        Response response = getRequestAdmin ("/users/" + userId,200);
        String responseBody = response.getBody ().asString ();
        assertEquals (200,response.getStatusCode ());

        assertTrue (responseBody.contains ("id"),"09c0e977-083d-4603-8c76-822e98131e32");
        assertTrue (responseBody.contains ("title"),"My new Post as User");
        assertTrue (responseBody.contains ("description"),"My Dogg");
        assertTrue (responseBody.contains ("body"),"Hello this is my new Post");
        assertTrue (responseBody.contains ("imageUrl"),"https://chatty-images-s3.s3.eu-central-1.amazonaws.com/e97e30ae-3b77-4ff5-a829-4ecabb76a1b7/1e80e3d9-c1b2-40d7-9f3f-1519a4b1a63f.jpg");
        assertTrue (responseBody.contains ("createdAt"),"2024-04-21T09:25:07.100Z");
        assertTrue (responseBody.contains ("updatedAt"),"2024-04-21T09:25:07.100Z");

        assertTrue (responseBody.contains ("id"),"95b5c981-650b-46e6-ad41-08df96d54d3");
        assertTrue (responseBody.contains ("name"),"Alex");
        assertTrue (responseBody.contains ("surname"),"Rashevchenko");
        assertTrue (responseBody.contains ("phone"),"+79717901351");
        assertTrue (responseBody.contains ("email"),"alexxxx@gmail.com");
        assertTrue (responseBody.contains ("role"),"USER");
        assertTrue (responseBody.contains ("gender"),"MALE");
        assertTrue (responseBody.contains ("birthDate"),"null");
        assertTrue (responseBody.contains ("avatarUrl"),"https://chatty-images-s3.s3.eu-central-1.amazonaws.com/95b5c981-650b-46e6-ad41-08df96d54d38/6471404c-3970-4909-a686-5295964f3557.jpg");
        assertTrue (responseBody.contains ("backgroundUrl"),"null");
    }
    @Test
    public void getPostByUserIdWithInvalidEndPointAdmin(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54d38/posts?skip=1&limit=1";
        Response response = getRequestAdmin ("/userss/" + userId,404);
        assertEquals (404,response.getStatusCode ());
        assertEquals ("HTTP/1.1 404 ",response.getStatusLine ());
    }
    @Test
    public void getPostByUserIdWithoutBodyAdmin(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54d38/posts?skip=1&limit=1";
        Response response = getRequestWithoutBody ("/users/" + userId,401);
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"UNAUTHORIZED");
        assertTrue (responseBody.contains ("message"),"Authentication failed: Full authentication is required to access this resource");
    }
}
