package test;

import dto.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LoginTest extends BaseTest{
    @Test
    public void userLogin(){
        LoginRequest requestBody = new LoginRequest (validEmail,validPassword);
        LoginResponse responseBody = postRequest ("/auth/login",200,requestBody)
                .body ().jsonPath ().getObject ("",LoginResponse.class);


        assertFalse(responseBody.getAccessToken ().isEmpty ());
        assertFalse (responseBody.getExpiration ().isEmpty ());
        assertFalse (responseBody.getRefreshToken ().isEmpty ());
    }

    @Test
    public void userLoginWithoutPassword(){
        LoginRequest requestBody = new LoginRequest (validEmail,emptyPassword);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue(responseBody.contains("Password cannot be empty"));
        assertTrue(responseBody.contains("Password must contain letters and numbers"));
        assertTrue(responseBody.contains("Password must contain at least 8 characters"));
    }

    @Test
    public void userLoginWithoutEmail() {
        LoginRequest requestBody = new LoginRequest (emptyEmail, validPassword);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("Invalid email format"));
        assertTrue (responseBody.contains ("Email cannot be empty"));

    }

    @Test
    public void userLoginWithInvalidPassword() {
        LoginRequest requestBody = new LoginRequest(validEmail, invalidPassword);
        ErrorMessageResponse response = postRequest ("/auth/login", 401,requestBody).as (ErrorMessageResponse.class);

        assertTrue(response.getHttpStatus ().equals ("UNAUTHORIZED"));
        assertTrue(response.getMessage ().equals ("The password does not match the one saved in the database!"));
    }

    @Test
    public void userLoginWithInvalidEmail(){
        LoginRequest requestBody = new LoginRequest (invalidEmail,validPassword);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("Invalid email format"));
    }

    @Test
    public void userLoginWithEmptyEmailAndPassword(){
        LoginRequest requestBody = new LoginRequest (emptyEmail,emptyPassword);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("Invalid email format"));
        assertTrue (responseBody.contains ("Email cannot be empty"));

        assertTrue(responseBody.contains("Password cannot be empty"));
        assertTrue(responseBody.contains("Password must contain letters and numbers"));
        assertTrue(responseBody.contains("Password must contain at least 8 characters"));
    }
    @Test
    public void userLoginWithoutAccessToken(){
        LoginRequest requestBody = new LoginRequest (validEmail,validPassword);
        ErrorMessageResponse userLoginResponse = postRequestWithoutBody ("/auth/login",400)
                .body ().jsonPath ().getObject ("",ErrorMessageResponse.class);
        assertTrue (userLoginResponse.getHttpStatus ().contains ("BAD_REQUEST"));
        assertTrue (userLoginResponse.getMessage ().contains ("Required request body is missing"));
    }







    @Test
    public void adminLogin(){
        LoginRequest requestBody = new LoginRequest (validEmailAdmin,validPasswordAdmin);
        LoginResponse responseBody = postRequest ("/auth/login",200,requestBody)
                .body ().jsonPath ().getObject ("",LoginResponse.class);


        assertFalse(responseBody.getAccessToken ().isEmpty ());
        assertFalse (responseBody.getExpiration ().isEmpty ());
        assertFalse (responseBody.getRefreshToken ().isEmpty ());
    }

    @Test
    public void adminLoginWithoutPassword(){
        LoginRequest requestBody = new LoginRequest (validEmailAdmin,emptyPassword);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue(responseBody.contains("Password cannot be empty"));
        assertTrue(responseBody.contains("Password must contain letters and numbers"));
        assertTrue(responseBody.contains("Password must contain at least 8 characters"));
    }
    @Test
    public void adminLoginWithoutEmail() {
        LoginRequest requestBody = new LoginRequest (emptyEmail, validPassword);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("Invalid email format"));
        assertTrue (responseBody.contains ("Email cannot be empty"));

    }

    @Test
    public void adminLoginWithInvalidPassword() {
        LoginRequest requestBody = new LoginRequest(validEmailAdmin, invalidPassword);
        ErrorMessageResponse response = postRequestAdmin ("/auth/login", 401,requestBody).as (ErrorMessageResponse.class);

        assertTrue(response.getHttpStatus ().equals ("UNAUTHORIZED"));
        assertTrue(response.getMessage ().equals ("The password does not match the one saved in the database!"));
    }

    @Test
    public void adminLoginWithInvalidEmail(){
        LoginRequest requestBody = new LoginRequest (invalidEmail,validPasswordAdmin);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("Invalid email format"));
    }

    @Test
    public void adminLoginWithEmptyEmailAndPassword(){
        LoginRequest requestBody = new LoginRequest (emptyEmail,emptyPassword);
        Response response = postRequest ("/auth/login",400,requestBody);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("Invalid email format"));
        assertTrue (responseBody.contains ("Email cannot be empty"));

        assertTrue(responseBody.contains("Password cannot be empty"));
        assertTrue(responseBody.contains("Password must contain letters and numbers"));
        assertTrue(responseBody.contains("Password must contain at least 8 characters"));
    }
    @Test
    public void adminLoginWithoutAccessToken(){
        LoginRequest requestBody = new LoginRequest (validEmail,validPassword);
        ErrorMessageResponse userLoginResponse = postRequestWithoutBody ("/auth/login",400)
                .body ().jsonPath ().getObject ("",ErrorMessageResponse.class);
        assertTrue (userLoginResponse.getHttpStatus ().contains ("BAD_REQUEST"));
        assertTrue (userLoginResponse.getMessage ().contains ("Required request body is missing"));
    }





    @Test
    public void refreshUserToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(REFRESH_TOKEN);
        Response response = postRequest("/auth/refresh", 200, refreshTokenRequest);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void refreshTokenWithInvalidTokenTest(){
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest ("2312321321");
        ErrorMessageResponse userLoginResponse = postRequest("/auth/refresh", 401,refreshTokenRequest)
                .body ().jsonPath ().getObject ("",ErrorMessageResponse.class);

        assertFalse (userLoginResponse.getHttpStatus ().isEmpty ());
        assertFalse(userLoginResponse.getMessage ().isEmpty ());

        assertTrue (userLoginResponse.getHttpStatus ().contains ("UNAUTHORIZED"));
        assertTrue (userLoginResponse.getMessage ().contains ("Unauthorized"));
    }

    @Test
    public void refreshTokenWithInvalidTokenErrorTest(){
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest ("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0ZjM2MDZmYS05ZGIyLTQxYTgtYWM0OC04M2I5Mzc0ZGY2OGQiLCJpYXQiOjE3MTMzMzU0NTYsImV4cCI6MTcxMzUwODI1Nn0.p2U8cNQ4kqjpaTPtY1E75GW_t3xUjhD9suO3wrXd9xId");
        ErrorMessageResponse userLoginResponse = postRequest  ("/auth/refresh",500,refreshTokenRequest)
                .body ().jsonPath ().getObject ("",ErrorMessageResponse.class);
        assertTrue (userLoginResponse.getHttpStatus ().contains ("INTERNAL_SERVER_ERROR"));
        assertTrue (userLoginResponse.getMessage ().contains ("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted."));
    }


    @Test
    public void registerWithUser(){
        RegisterRequest createUserRequest = new RegisterRequest ("gowodax239@iliken.com","Nodari234","Nodari234","user");
        LoginResponse  loginResponse= postRequest ("/auth/register",200,createUserRequest)
                .body ().jsonPath ().getObject ("",LoginResponse.class);
        assertFalse (loginResponse.getAccessToken ().isEmpty ());
        assertFalse (loginResponse.getRefreshToken ().isEmpty ());
        assertFalse (loginResponse.getExpiration ().isEmpty ());
    }
    @Test
    public void registerUserExistingUser(){
        RegisterRequest createUserRequest = new RegisterRequest ("abbey26371@9b3xg.cashbenties.com","Nodari234","Nodari234","user");
        Response response = postRequest ("/auth/register",409,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"CONFLICT");
        assertTrue (responseBody.contains ("message"),"Email already exists!");

    }

    @Test
    public void registerWitInvalidEmail(){
        RegisterRequest createUserRequest = new RegisterRequest ("вфывфывфывфыв","Nodari234","Nodari234","user");
        Response response = postRequest ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("email"),"Email is not valid.");
    }

    @Test
    public void registerWithMinPassword(){
        RegisterRequest createUserRequest = new RegisterRequest ("hadlee24073@hlfh.cse445.com","1234567","1234567","user");
        Response response = postRequest ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("password"),"Password must contain at least 8 characters");
        assertTrue (responseBody.contains ("password"),"Password must contain letters and numbers");
    }

    @Test
    public void registerWithSpecSymbol(){
        RegisterRequest createUserRequest = new RegisterRequest ("hadlee24073@hlfh.cse445.com","Nodar_2321@!!","Nodar_2321@!","user");
        Response response = postRequest ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"BAD_REQUEST");
        assertTrue (responseBody.contains ("message"),"Password mismatch");
    }

    @Test
    public void registerWitEmptyPassword(){
        RegisterRequest createUserRequest = new RegisterRequest ("gerome3845@3apd.crankymonkey.info","","","user");
        Response response = postRequest ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("password"),"Password must contain at least 8 characters");
        assertTrue (responseBody.contains ("password"),"Password cannot be empty");
        assertTrue (responseBody.contains ("password"),"Password must contain letters and numbers");
        assertTrue (responseBody.contains ("confirmPassword"),"You need to confirm your password");
    }
    @Test
    public void registerWithEmptyConfirmPassword(){
        RegisterRequest createUserRequest = new RegisterRequest ("gerome3845@3apd.crankymonkey.info","Nodari234","","user");
        Response response = postRequest ("/auth/register",400,createUserRequest);
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("confirmPassword"),"You need to confirm your password");
    }

    @Test
    public void registerWithMismatchedPassword(){
        RegisterRequest createUserRequest = new RegisterRequest("carolynn45093@1zlf.crankymonkey.info", "Nodar_rrii1223", "11111111111111111111111111111111111111111111111111111111111111111111111111111", "user");
        Response response = postRequest("/auth/register", 400, createUserRequest);
        assertTrue(response.getBody().asString().contains("Password mismatch!"), "Expected error message: Password mismatch!");
    }





    @Test
    public void registerWithAdmin(){
        RegisterRequest createUserRequest = new RegisterRequest ("eveleen16870@ewc9k.cse445.com","Nodari234","Nodari234","admin");
        LoginResponse  loginResponse = postRequestAdmin ("/auth/register",200,createUserRequest)
                .body ().jsonPath ().getObject ("",LoginResponse.class);
        assertFalse (loginResponse.getAccessToken ().isEmpty ());
        assertFalse (loginResponse.getRefreshToken ().isEmpty ());
        assertFalse (loginResponse.getExpiration ().isEmpty ());
    }
    @Test
    public void registerAdminExistingUser(){
        RegisterRequest createUserRequest = new RegisterRequest ("abbey26371@9b3xg.cashbenties.com","Nodari234","Nodari234","admin");
        Response response = postRequestAdmin ("/auth/register",409,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"CONFLICT");
        assertTrue (responseBody.contains ("message"),"Email already exists!");

    }

    @Test
    public void registerWithAdminInvalidEmail(){
        RegisterRequest createUserRequest = new RegisterRequest ("вфывфывфывфыв","Nodari234","Nodari234","admin");
        Response response = postRequestAdmin ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("email"),"Email is not valid.");
    }

    @Test
    public void registerWithAdminMinPassword(){
        RegisterRequest createUserRequest = new RegisterRequest ("saleem18358@jtwlj.cashbenties.com","123","123","admin");
        Response response = postRequestAdmin ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("password"),"Password must contain at least 8 characters");
        assertTrue (responseBody.contains ("password"),"Password must contain letters and numbers");
    }

    @Test
    public void registerWithSpecSymbolAdmin(){
        RegisterRequest createUserRequest = new RegisterRequest ("hadlee24073@hlfh.cse445.com","Nodar_2321@!!","Nodar_2321@!","admin");
        Response response = postRequestAdmin ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("httpStatus"),"BAD_REQUEST");
        assertTrue (responseBody.contains ("message"),"Password mismatch");
    }

    @Test
    public void registerWithAdminEmptyPassword(){
        RegisterRequest createUserRequest = new RegisterRequest ("saleem18358@jtwlj.cashbenties.com","","","admin");
        Response response = postRequestAdmin ("/auth/register",400,createUserRequest);

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("password"),"Password must contain at least 8 characters");
        assertTrue (responseBody.contains ("password"),"Password cannot be empty");
        assertTrue (responseBody.contains ("password"),"Password must contain letters and numbers");
        assertTrue (responseBody.contains ("confirmPassword"),"You need to confirm your password");
    }
    @Test
    public void registerWithAdminEmptyConfirmPassword(){
        RegisterRequest createUserRequest = new RegisterRequest ("saleem18358@jtwlj.cashbenties.com","Nodari234","","admin");
        Response response = postRequestAdmin ("/auth/register",400,createUserRequest);
        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("confirmPassword"),"You need to confirm your password");
    }

    @Test
    public void registerWithAdminMismatchedPassword(){
        RegisterRequest createUserRequest = new RegisterRequest("saleem18358@jtwlj.cashbenties.com", "Nodar_rrii1223", "11111111111111111111111111111111111111111111111111111111111111111111111111111", "admin");
        Response response = postRequestAdmin("/auth/register", 400, createUserRequest);
        assertTrue(response.getBody().asString().contains("Password mismatch!"), "Expected error message: Password mismatch!");
    }
}
