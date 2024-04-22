package test;


import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DeleteUserTest extends BaseTest {
    @Test
    public void deleteUser() {
        String userId = "9e4ecf11-2c5a-4abc-aa2d-8be59fbc9baa";
        Response response = deleteRequest("/users/" + userId, 403);
        assertEquals(403, response.getStatusCode());

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"FORBIDDEN");
        assertTrue (responseBody.contains ("message"),"You don't have permission to delete users");
    }

    @Test
    public void deleteUserWithInvalidUserId(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54ddsda2434435t38";
        Response deleteUserResponse = deleteRequest("/users/" + userId, 500);
        assertEquals(500, deleteUserResponse.getStatusCode());
    }

    @Test
    public void deleteUserWithInvalidEndPoint(){
        String userId = "9e4ecf11-2c5a-4abc-aa2d-8be59fbc9baa";
        Response deleteUserResponse = deleteRequest("/usersdsadass/" + userId, 404);
        assertEquals(404, deleteUserResponse.getStatusCode());
    }
    @Test
    public void deleteUserWithoutAuthorization(){
        String userId = "9e4ecf11-2c5a-4abc-aa2d-8be59fbc9baa";
        Response response = deleteRequestWithoutAuthorization ("/users/" + userId, 400);
        assertEquals(400, response.getStatusCode());
    }



    @Test
    public void deleteUserAdmin() {
        String userId = "f8e8a413-715b-46b7-bbd0-522d6081f666";
        Response response = deleteRequestAdmin ("/users/" + userId, 200);
        assertEquals(200, response.getStatusCode());


    }

    @Test
    public void deleteUserWithInvalidUserIdAdmin(){
        String userId = "95b5c981-650b-46e6-ad41-08df96d54ddsda2434435t38";
        Response deleteUserResponse = deleteRequestAdmin("/users/" + userId, 500);
        assertEquals(500, deleteUserResponse.getStatusCode());
    }

    @Test
    public void deleteUserWithInvalidEndPointAdmin(){
        String userId = "9e4ecf11-2c5a-4abc-aa2d-8be59fbc9baa";
        Response deleteUserResponse = deleteRequestAdmin("/usersdsadass/" + userId, 404);
        assertEquals(404, deleteUserResponse.getStatusCode());
    }
    @Test
    public void deleteUserWithoutAuthorizationAdmin(){
        String userId = "9e4ecf11-2c5a-4abc-aa2d-8be59fbc9baa";
        Response response = deleteRequestWithoutAuthorization ("/users/" + userId, 400);
        assertEquals(400, response.getStatusCode());
    }
}
