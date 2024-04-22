package test;

import dto.UpdateUserRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PutUserTest extends BaseTest{

    @Test
    public void updateUserChangeProfile() {
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");
        Response response = putRequest("/users/" + userId, 200, updateUserRequest);

        assertEquals(200, response.getStatusCode());
        assertEquals ("Nodari",updateUserRequest.getName ());
        assertEquals ("Amoev",updateUserRequest.getSurname ());

        assertFalse (updateUserRequest.getName ().isEmpty ());
        assertFalse (updateUserRequest.getSurname ().isEmpty ());
    }

    @Test
    public void updateChangeAnotherUserProfile() {
        String userId = "f8e8a413-715b-46b7-bbd0-522d6081f666";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");

        Response response = putRequest("/users/" + userId, 403, updateUserRequest);
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"FORBIDDEN");
        assertTrue (responseBody.contains ("message"),"You don't have permission to edit");
        assertEquals (403,response.getStatusCode ());
    }

    @Test
    public void updateUserWithInvalidUserId(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374dsdsadasdasdsadf68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");
        Response response = putRequest("/users/" + userId, 500, updateUserRequest);

        assertEquals(500, response.getStatusCode());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"INTERNAL_SERVER_ERROR");
        assertTrue (responseBody.contains ("message"),"Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; UUID string too large");
    }

    @Test
    public void updateUserWithInvalidEndPoint(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");
        Response response = putRequest("/usders/" + userId, 404, updateUserRequest);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void updateUserWithoutEndPoint(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");
        Response response = putRequest("//" + userId, 400, updateUserRequest);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void updateUserWithoutEmptyField(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("");
        updateUserRequest.setSurname("");
        Response response = putRequest("/users/" + userId, 400, updateUserRequest);
        assertEquals(400, response.getStatusCode());

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("surname"),"Surname must contain from 3 to 20 characters");
        assertTrue (responseBody.contains ("surname"),"must match \\\"^[A-Za-z-]+$\\");

        assertTrue (responseBody.contains ("name"),"Name must contain from 3 to 20 characters");
        assertTrue (responseBody.contains ("name"),"must match \\\"^[A-Za-z-]+$\\");
    }
    //---------------------------------------------------------------------------

    @Test
    public void updateUserTestAdmin() {
        String userId = "c0975548-52b1-40f2-8ec0-982e51eed4cf";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Kirril");
        updateUserRequest.setSurname("Kirkorov");
        Response response = putRequestAdmin ("/users/" + userId, 200, updateUserRequest);

        assertEquals(200, response.getStatusCode());
        assertEquals ("Kirril",updateUserRequest.getName ());
        assertEquals ("Kirkorov",updateUserRequest.getSurname ());

        assertFalse (updateUserRequest.getName ().isEmpty ());
        assertFalse (updateUserRequest.getSurname ().isEmpty ());
    }

    @Test
    public void updateChangeAnotherUserProfileAdmin() {
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");

        Response response = putRequestAdmin ("/users/" + userId, 200, updateUserRequest);
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("name"),"Nodari");
        assertTrue (responseBody.contains ("surname"),"Amoev");

        assertEquals (200,response.getStatusCode ());
    }

    @Test
    public void updateUserWithInvalidUserIdAdmin(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374dsdsadasdasdsadf68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");
        Response response = putRequest("/users/" + userId, 500, updateUserRequest);

        assertEquals(500, response.getStatusCode());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"INTERNAL_SERVER_ERROR");
        assertTrue (responseBody.contains ("message"),"Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; UUID string too large");
    }

    @Test
    public void updateUserWithInvalidEndPointAdmin(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");
        Response response = putRequest("/usders/" + userId, 404, updateUserRequest);

        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void updateUserWithoutEndPointAdmin(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Nodari");
        updateUserRequest.setSurname("Amoev");
        Response response = putRequest("//" + userId, 400, updateUserRequest);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void updateUserWithoutEmptyFieldAdmin(){
        String userId = "4f3606fa-9db2-41a8-ac48-83b9374df68d";

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("");
        updateUserRequest.setSurname("");
        Response response = putRequest("/users/" + userId, 400, updateUserRequest);
        assertEquals(400, response.getStatusCode());

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("surname"),"Surname must contain from 3 to 20 characters");
        assertTrue (responseBody.contains ("surname"),"must match \\\"^[A-Za-z-]+$\\");

        assertTrue (responseBody.contains ("name"),"Name must contain from 3 to 20 characters");
        assertTrue (responseBody.contains ("name"),"must match \\\"^[A-Za-z-]+$\\");
    }
}
