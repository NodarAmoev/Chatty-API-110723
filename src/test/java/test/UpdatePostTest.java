package test;

import dto.ErrorMessageResponse;
import dto.UpdatePostResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdatePostTest extends BaseTest {

    @Test
    public void updatePostUser() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequest ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 200, updatePostResponse);

        assertEquals (200, response.getStatusCode (), "Неверный статус-код ответа");
        assertTrue (updatePostResponse.getTitle ().equals ("Увы"));
        assertTrue (updatePostResponse.getDescription ().equals ("Дождик идёт"));
        assertTrue (updatePostResponse.getBody ().equals ("Весна пришла"));
    }

    @Test
    public void updatePostUserWithInvalidEndPoint() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequest ("/postss/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 404, updatePostResponse);

        assertEquals (404, response.getStatusCode (), "Неверный статус-код ответа");
        assertTrue (updatePostResponse.getTitle ().equals ("Увы"));
        assertTrue (updatePostResponse.getDescription ().equals ("Дождик идёт"));
        assertTrue (updatePostResponse.getBody ().equals ("Весна пришла"));
    }

    @Test
    public void updatePostUserServerError() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequest ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb72312dasd", 500, updatePostResponse);

        assertEquals (500, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"), "INTERNAL_SERVER_ERROR");
        assertTrue (responseBody.contains ("message"), "Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; UUID string too large");

    }

    @Test
    public void updatePostUserWithEmptyTitle() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequest ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("title"), "Title can not be empty!");
        assertTrue (responseBody.contains ("title"), "Title must contain from 1 to 40 characters");
    }

    @Test
    public void updatePostUserWithEmptyDescription() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "", "Весна пришла", "", "");
        Response response = putRequest ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("description"), "Description can not be empty!");
        assertTrue (responseBody.contains ("description"), "Description must contain from 1 to 100 characters");
    }

    @Test
    public void updatePostUserWithEmptyBody() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "Дождик идёт", "", "", "");
        Response response = putRequest ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("body"), "Body can not be empty!");
        assertTrue (responseBody.contains ("body"), "Body must contain from 1 to 1000 characters");
    }

    @Test
    public void updatePostUserWithEmptyField() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("", "", "", "", "");
        Response response = putRequest ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();

        assertTrue (responseBody.contains ("description"), "Description can not be empty!");
        assertTrue (responseBody.contains ("description"), "Description must contain from 1 to 100 characters");

        assertTrue (responseBody.contains ("body"), "Body can not be empty!");
        assertTrue (responseBody.contains ("body"), "Body must contain from 1 to 1000 characters");

        assertTrue (responseBody.contains ("title"), "Title must contain from 1 to 40 characters");
        assertTrue (responseBody.contains ("title"), "Title can not be empty!");
    }




    @Test
    public void AdminUpdatesUserPostTitleTest() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Миша", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequestAdmin ("/posts/b855905e-4e96-4784-b5df-74fc301e0e04", 200, updatePostResponse);

        assertEquals (200, response.getStatusCode (), "Неверный статус-код ответа");
        assertTrue (updatePostResponse.getTitle ().equals ("Увы"));
        assertTrue (updatePostResponse.getDescription ().equals ("Дождик идёт"));
        assertTrue (updatePostResponse.getBody ().equals ("Весна пришла"));
    }

    @Test
    public void updatePostAdminWithInvalidEndPoint() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequestAdmin ("/postss/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 404, updatePostResponse);

        assertEquals (404, response.getStatusCode (), "Неверный статус-код ответа");
        assertTrue (updatePostResponse.getTitle ().equals ("Увы"));
        assertTrue (updatePostResponse.getDescription ().equals ("Дождик идёт"));
        assertTrue (updatePostResponse.getBody ().equals ("Весна пришла"));
    }

    @Test
    public void updatePostAdminServerError() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequestAdmin ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb72312dasd", 500, updatePostResponse);

        assertEquals (500, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"), "INTERNAL_SERVER_ERROR");
        assertTrue (responseBody.contains ("message"), "Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; UUID string too large");
    }

    @Test
    public void updatePostAdminWithEmptyTitle() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("", "Дождик идёт", "Весна пришла", "", "");
        Response response = putRequestAdmin ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("title"), "Title can not be empty!");
        assertTrue (responseBody.contains ("title"), "Title must contain from 1 to 40 characters");
    }

    @Test
    public void updatePostAdminWithEmptyDescription() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "", "Весна пришла", "", "");
        Response response = putRequestAdmin ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("description"), "Description can not be empty!");
        assertTrue (responseBody.contains ("description"), "Description must contain from 1 to 100 characters");
    }

    @Test
    public void updatePostAdminWithEmptyBody() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("Увы", "Дождик идёт", "", "", "");
        Response response = putRequestAdmin ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("body"), "Body can not be empty!");
        assertTrue (responseBody.contains ("body"), "Body must contain from 1 to 1000 characters");
    }

    @Test
    public void updatePostAdminWithEmptyField() {
        UpdatePostResponse updatePostResponse = new UpdatePostResponse ("", "", "", "", "");
        Response response = putRequestAdmin ("/posts/29f9f03d-7a3c-4269-bd31-f850f4c5bbb7", 400, updatePostResponse);

        assertEquals (400, response.getStatusCode ());
        String responseBody = response.getBody ().asString ();

        assertTrue (responseBody.contains ("description"), "Description can not be empty!");
        assertTrue (responseBody.contains ("description"), "Description must contain from 1 to 100 characters");

        assertTrue (responseBody.contains ("body"), "Body can not be empty!");
        assertTrue (responseBody.contains ("body"), "Body must contain from 1 to 1000 characters");

        assertTrue (responseBody.contains ("title"), "Title must contain from 1 to 40 characters");
        assertTrue (responseBody.contains ("title"), "Title can not be empty!");
    }

}