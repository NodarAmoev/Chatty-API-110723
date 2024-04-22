package test;

import dto.PutUpdatePasswordRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PutUpdatePasswordTest extends BaseTest {
    @Test
    public void putUpdatePassword(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("Nodari234");
        putUpdatePasswordRequest.setNewPassword("Nodari2345");
        putUpdatePasswordRequest.setConfirmPassword("Nodari2345");

        int statusCode = putRequest("/user/password/update", 200, putUpdatePasswordRequest).getStatusCode();

        assertEquals(200, statusCode, "Код ответа не соответствует ожидаемому");
    }

    @Test
    public void putUpdatePasswordWithoutCurrentPassword(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("");
        putUpdatePasswordRequest.setNewPassword("Nodari234");
        putUpdatePasswordRequest.setConfirmPassword("Nodari234");

        Response response = putRequest("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("currentPassword"),"Current password must not be empty");
    }
    @Test
    public void putUpdatePasswordWithIncorrectCurrentPassword(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("Qatest234");
        putUpdatePasswordRequest.setNewPassword("Nodari2345");
        putUpdatePasswordRequest.setConfirmPassword("Nodari2345");


        Response response = putRequest("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("httpStatus"),"BAD_REQUEST");
        assertTrue(responseBody.contains("message"),"Current password is incorrect.");
    }

    @Test
    public void putUpdatePasswordWithMismatchedConfirmPassword(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("Nodari2345");
        putUpdatePasswordRequest.setNewPassword("Nodari23456");
        putUpdatePasswordRequest.setConfirmPassword("Qatest2321321");

        Response response = putRequest("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("httpStatus"),"BAD_REQUEST");
        assertTrue(responseBody.contains("message"),"Current password is incorrect.");
    }
    @Test
    public void putUpdatePasswordWithInvalidEndpoint(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("Nodari2345");
        putUpdatePasswordRequest.setNewPassword("Nodari234");
        putUpdatePasswordRequest.setConfirmPassword("Nodari234");

        Response response = putRequest("/usesr/password/update", 404, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        assertEquals(404, statusCode, "Код ответа не соответствует ожидаемому");
    }

    @Test
    public void putUpdatePasswordWithoutBody(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("Nodari2345");
        putUpdatePasswordRequest.setNewPassword("Nodari234");
        putUpdatePasswordRequest.setConfirmPassword("Nodari234");

        Response response = putRequestWithoutBody ("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("httpStatus"),"BAD_REQUEST");
        assertTrue(responseBody.contains("message"),"Current password is incorrect.");
    }
    @Test
    public void putUpdatePasswordWithoutField(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("");
        putUpdatePasswordRequest.setNewPassword("");
        putUpdatePasswordRequest.setConfirmPassword("");

        Response response = putRequestWithoutBody ("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("confirmPassword"),"Current password must not be empty");

        assertTrue(responseBody.contains("newPassword"),"Password must contain letters and numbers");
        assertTrue(responseBody.contains("newPassword"),"New password must not be empty");
        assertTrue(responseBody.contains("newPassword"),"Password must contain at least 8 characters");

        assertTrue(responseBody.contains("currentPassword"),"Current password must not be empty");
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void putUpdatePasswordAsAdmin(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("Nodari2345");
        putUpdatePasswordRequest.setNewPassword("Nodari234");
        putUpdatePasswordRequest.setConfirmPassword("Nodari234");

        int statusCode = putRequestAdmin("/user/password/update", 200, putUpdatePasswordRequest).getStatusCode();

        assertEquals(200, statusCode, "Код ответа не соответствует ожидаемому");
    }

    @Test
    public void putUpdatePasswordWithoutCurrentPasswordAsAdmin(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("");
        putUpdatePasswordRequest.setNewPassword("AdminNewPassword123");
        putUpdatePasswordRequest.setConfirmPassword("AdminNewPassword123");

        Response response = putRequestAdmin("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("currentPassword"),"Current password must not be empty");
    }

    @Test
    public void putUpdatePasswordWithIncorrectCurrentPasswordAsAdmin(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("IncorrectPassword123");
        putUpdatePasswordRequest.setNewPassword("AdminNewPassword123");
        putUpdatePasswordRequest.setConfirmPassword("AdminNewPassword123");

        Response response = putRequestAdmin("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("httpStatus"),"BAD_REQUEST");
        assertTrue(responseBody.contains("message"),"Current password is incorrect.");
    }

    @Test
    public void putUpdatePasswordWithMismatchedConfirmPasswordAsAdmin(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("AdminPassword123");
        putUpdatePasswordRequest.setNewPassword("AdminNewPassword123");
        putUpdatePasswordRequest.setConfirmPassword("MismatchedPassword123");

        Response response = putRequestAdmin("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("httpStatus"),"BAD_REQUEST");
        assertTrue(responseBody.contains("message"),"Password confirmation does not match.");
    }

    @Test
    public void putUpdatePasswordWithInvalidEndpointAsAdmin(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("AdminPassword123");
        putUpdatePasswordRequest.setNewPassword("AdminNewPassword123");
        putUpdatePasswordRequest.setConfirmPassword("AdminNewPassword123");

        Response response = putRequestAdmin("/usesr/password/update", 404, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();

        assertEquals(404, statusCode, "Код ответа не соответствует ожидаемому");
    }

    @Test
    public void putUpdatePasswordWithoutBodyAsAdmin(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("Nodari2345");
        putUpdatePasswordRequest.setNewPassword("Nodari234");
        putUpdatePasswordRequest.setConfirmPassword("Nodari234");

        Response response = putRequestWithoutBody ("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("httpStatus"),"BAD_REQUEST");
        assertTrue(responseBody.contains("message"),"Request body is required.");
    }

    @Test
    public void putUpdatePasswordWithoutFieldAsAdmin(){
        PutUpdatePasswordRequest putUpdatePasswordRequest = new PutUpdatePasswordRequest();
        putUpdatePasswordRequest.setCurrentPassword("");
        putUpdatePasswordRequest.setNewPassword("");
        putUpdatePasswordRequest.setConfirmPassword("");

        Response response = putRequestWithoutBody ("/user/password/update", 400, putUpdatePasswordRequest);
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        assertEquals(400, statusCode, "Код ответа не соответствует ожидаемому");
        assertTrue(responseBody.contains("confirmPassword"),"Confirm password must not be empty");
        assertTrue(responseBody.contains("newPassword"),"Password must contain letters and numbers");
        assertTrue(responseBody.contains("newPassword"),"New password must not be empty");
        assertTrue(responseBody.contains("newPassword"),"Password must contain at least 8 characters");
        assertTrue(responseBody.contains("currentPassword"),"Current password must not be empty");
    }

}
