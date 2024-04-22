package test;

import dto.ErrorMessageResponse;
import dto.GetUserResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetUserTest extends BaseTest {
    @Test
    public void getUserCheck() {
        Response response = getRequest("/me", 200);
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);

        assertFalse (getUserResponse.getId ().isEmpty ());
        assertFalse (getUserResponse.getName ().isEmpty ());
        assertFalse (getUserResponse.getSurname ().isEmpty ());
        assertFalse (getUserResponse.getPhone ().isEmpty ());
        assertFalse (getUserResponse.getEmail ().isEmpty ());
        assertFalse (getUserResponse.getRole ().isEmpty ());
        assertFalse (getUserResponse.getGender ().isEmpty ());
        assertFalse (getUserResponse.getBirthDate ().isEmpty ());


        assertTrue (getUserResponse.getId ().equals ("4f3606fa-9db2-41a8-ac48-83b9374df68d"));
        assertTrue (getUserResponse.getName ().equals ("Makarov"));
        assertTrue (getUserResponse.getSurname ().equals ("Kalashnikov"));
        assertTrue (getUserResponse.getPhone ().equals ("+49176316596"));
        assertTrue (getUserResponse.getEmail ().equals ("nodari.amoev2@gmail.com"));
        assertTrue (getUserResponse.getRole ().equals ("USER"));
        assertTrue (getUserResponse.getGender ().equals ("MALE"));

    }
    @Test
    public void getUserWithInvalidEndpoint() {
        Response response = getRequest("/invalidEndpoint", 404);
        assertEquals (404,response.getStatusCode ());
    }

    @Test
    public void getUserWithoutAuthentication() {
        Response response = getRequestWithoutBody("/me", 401);

        assertEquals(401, response.getStatusCode());
        ErrorMessageResponse errorMessageResponse = response.as(ErrorMessageResponse.class);
        assertNotNull(errorMessageResponse);
        assertFalse(errorMessageResponse.getMessage().isEmpty());
    }

    @Test
    public void getUserCheckAdmin() {
        Response response = getRequestAdmin ("/me", 200);
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);

        assertFalse (getUserResponse.getId ().isEmpty ());
        assertFalse (getUserResponse.getName ().isEmpty ());
        assertFalse (getUserResponse.getSurname ().isEmpty ());
        assertFalse (getUserResponse.getPhone ().isEmpty ());
        assertFalse (getUserResponse.getEmail ().isEmpty ());
        assertFalse (getUserResponse.getRole ().isEmpty ());
        assertFalse (getUserResponse.getGender ().isEmpty ());
        assertFalse (getUserResponse.getBirthDate ().isEmpty ());


        assertTrue (getUserResponse.getId ().equals ("c0975548-52b1-40f2-8ec0-982e51eed4cf"));
        assertTrue (getUserResponse.getName ().equals ("Makarov"));
        assertTrue (getUserResponse.getSurname ().equals ("Kalashnikov"));
        assertTrue (getUserResponse.getPhone ().equals ("+49176316596"));
        assertTrue (getUserResponse.getEmail ().equals ("amoev.nodari@gmail.com"));
        assertTrue (getUserResponse.getRole ().equals ("ADMIN"));
        assertTrue (getUserResponse.getGender ().equals ("MALE"));

    }

    @Test
    public void getUserUnauthorized(){
        Response response = getRequestWithoutBody ("/me", 401);
        ErrorMessageResponse errorMessageResponse = response.as(ErrorMessageResponse.class);

        assertTrue (errorMessageResponse.getHttpStatus ().contains ("UNAUTHORIZED"));
        assertTrue (errorMessageResponse.getMessage ().contains ("Authentication failed: Full authentication is required to access this resource"));

    }

}

