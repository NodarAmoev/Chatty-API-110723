package test;

import dto.ErrorMessageResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class DeletePostTest extends BaseTest{
    @Test
    public void deletePostById() {
        String postIdToDelete = "29f9f03d-7a3c-4269-bd31-f850f4c5bbb7";
        Response response = deleteRequest ("/posts/" + postIdToDelete, 200);
        assertEquals(200, response.getStatusCode(),"Неверный статус-код ответа");
    }
    @Test
    public void deleteAlreadyDeletedPost() {
        String postIdToDelete = "4ef505a2-3bb4-4a79-8fc0-2a97328ffd86";
        Response response = deleteRequest("/posts/" + postIdToDelete, 404);

        ErrorMessageResponse errorMessageResponse = response.as(ErrorMessageResponse.class);
        assertFalse(errorMessageResponse.getHttpStatus ().isEmpty ());
        assertFalse(errorMessageResponse.getMessage ().isEmpty ());

        assertEquals(404, response.getStatusCode(), "Неверный статус-код ответа");
        assertEquals("NOT_FOUND",errorMessageResponse.getHttpStatus ());
        assertEquals("Post not found!",errorMessageResponse.getMessage ());
    }

    @Test
    public void deleteOtherUserPost(){
        String postIdToDelete = "004a2423-03af-40f2-8928-1c39e9bc9dc0";
        Response response = deleteRequest("/posts/" + postIdToDelete, 403);

        ErrorMessageResponse errorMessage = response.as(ErrorMessageResponse.class);

        assertEquals(403, response.getStatusCode(),"Неверный статус-код ответа");
        assertEquals("FORBIDDEN", errorMessage.getHttpStatus(), "Неверный статус ответа");
        assertEquals("You are not allowed to delete this post!", errorMessage.getMessage(), "Неверное сообщение об ошибке");

        assertFalse (errorMessage.getHttpStatus ().isEmpty ());
        assertFalse (errorMessage.getMessage ().isEmpty ());
    }

    @Test
    public void errorServer(){
        String postIdToDelete = "004a2423-03af-40f2-8928-1c39e9bc9dc0dsds";
        Response response = deleteRequest("/posts/" + postIdToDelete, 500);

        assertEquals(500, response.getStatusCode(),"Неверный статус-код ответа");
        ErrorMessageResponse errorMessage = response.as(ErrorMessageResponse.class);
        assertEquals ("INTERNAL_SERVER_ERROR",errorMessage.getHttpStatus (),"Неверный статус ответа");
        assertEquals ("Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; UUID string too large",
                errorMessage.getMessage (),"Неверное сообщение об ошибке");

        assertFalse (errorMessage.getHttpStatus ().isEmpty ());
        assertFalse (errorMessage.getMessage ().isEmpty ());
    }

    @Test
    public void deletePostWithoutAuthorization() {
        String postIdToDelete = "004a2423-03af-40f2-8928-1c39e9bc9dc0";
        Response response = deleteRequestWithoutAuthorization ("/posts/" + postIdToDelete, 400);
        assertEquals (400, response.getStatusCode (), "Неверный статус-код ответа");
    }
    @Test
    public void deletePostWithInvalidEndPoint() {
        String postIdToDelete = "004a2423-03af-40f2-8928-1c39e9bc9dc0";
        Response response = deleteRequest ("/postsd/" + postIdToDelete, 404);
        assertEquals(404, response.getStatusCode(), "Неверный статус-код ответа");
    }





    @Test
    public void deletePostByIdAdmin() {
        String postIdToDelete = "ca262838-96fb-4769-810a-36ef292ba5e6";
        Response response = deleteRequestAdmin("/posts/" + postIdToDelete, 200);
        assertEquals(200, response.getStatusCode(), "Неверный статус-код ответа");
    }


    @Test
    public void deleteAlreadyDeletedPostAdmin() {
        String postIdToDelete = "4ef505a2-3bb4-4a79-8fc0-2a97328ffd86";
        Response response = deleteRequestAdmin ("/posts/" + postIdToDelete, 404);

        ErrorMessageResponse errorMessageResponse = response.as(ErrorMessageResponse.class);
        assertFalse(errorMessageResponse.getHttpStatus().isEmpty());
        assertFalse(errorMessageResponse.getMessage().isEmpty());

        assertEquals(404, response.getStatusCode(), "Неверный статус-код ответа");
        assertEquals("NOT_FOUND", errorMessageResponse.getHttpStatus());
        assertEquals("Post not found!", errorMessageResponse.getMessage());
    }


    @Test
    public void deleteOtherUserPostAdmin() {
        String postIdToDelete = "fb1c0d62-8796-47d1-8881-8345973ab669";
        Response response = deleteRequestAdmin("/posts/" + postIdToDelete, 200);

        assertEquals(200, response.getStatusCode(), "Неверный статус-код ответа");

    }


    @Test
    public void errorServerAdmin() {
        String postIdToDelete = "004a2423-03af-40f2-8928-1c39e9bc9dc0dsds";
        Response response = deleteRequestAdmin ("/posts/" + postIdToDelete, 500);

        assertEquals(500, response.getStatusCode(), "Неверный статус-код ответа");
        ErrorMessageResponse errorMessage = response.as(ErrorMessageResponse.class);
        assertEquals("INTERNAL_SERVER_ERROR", errorMessage.getHttpStatus(), "Неверный статус ответа");
        assertEquals("Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; UUID string too large",
                errorMessage.getMessage(), "Неверное сообщение об ошибке");

        assertFalse(errorMessage.getHttpStatus().isEmpty());
        assertFalse(errorMessage.getMessage().isEmpty());
    }


    @Test
    public void deletePostWithoutAuthorizationAdmin() {
        String postIdToDelete = "004a2423-03af-40f2-8928-1c39e9bc9dc0";
        Response response = deleteRequestWithoutAuthorization("/posts/" + postIdToDelete, 400);
        assertEquals(400, response.getStatusCode(), "Неверный статус-код ответа");
    }


    @Test
    public void deletePostWithInvalidEndPointAdmin() {
        String postIdToDelete = "004a2423-03af-40f2-8928-1c39e9bc9dc0";
        Response response = deleteRequestAdmin("/postsd/" + postIdToDelete, 404);
        assertEquals(404, response.getStatusCode(), "Неверный статус-код ответа");
    }
}

