package test;

import dto.CreateUserPostResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;


public class BaseTest {
    final static String BASE_URI = "http://chatty.telran-edu.de:8989/api";
    final static String REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0ZjM2MDZmYS05ZGIyLTQxYTgtYWM0OC04M2I5Mzc0ZGY2OGQiLCJpYXQiOjE3MTM2OTM3MzQsImV4cCI6MTcxMzg2NjUzNH0.V90Z_uky4swIPdFpjBOW3kBgBOVGL37yjoZJjzShLhg";
    final static String REFRESH_TOKEN_ADMIN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjMDk3NTU0OC01MmIxLTQwZjItOGVjMC05ODJlNTFlZWQ0Y2YiLCJpYXQiOjE3MTM3MDYwNTMsImV4cCI6MTcxMzg3ODg1M30.k3O-oTYbrmfKS1dJq3Cg4aO-uRhJyVxW4Lub-qwLTuc";
    static String accessToken;
    static String accessTokenAdmin;

    String validEmail = "nodari.amoev2@gmail.com";
    String validPassword = "Nodari234";

    String validEmailAdmin = "amoev.nodari@gmail.com";
    String validPasswordAdmin = "Nodari234";


    String invalidEmail = "nodari.@gmail.com";
    String invalidPassword = "Nodari23";

    String emptyEmail = "";
    String emptyPassword = "";





    public static void setAccessToken(String token) {
        accessToken = token;
    }



    static RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .build();

    @BeforeEach
    public void setUp() {
        accessToken = getAccessTokenFromRefreshToken(REFRESH_TOKEN);
        accessTokenAdmin = getAccessTokenAdminFromRefreshToken(REFRESH_TOKEN_ADMIN);
        setAccessToken(getAccessTokenFromRefreshToken(REFRESH_TOKEN));
    }


    public static Response postRequest(String endPoint, Integer expectedStatusCode, Object body) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .log().all()
                .post (endPoint)
                .then()
                .log()
                .all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response putRequest(String endPoint, Integer expectedStatusCode, Object body) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .log().all()
                .put (endPoint)
                .then()
                .log()
                .all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response putRequestAdmin(String endPoint, Integer expectedStatusCode, Object body) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessTokenAdmin)
                .body(body)
                .when()
                .log().all()
                .put (endPoint)
                .then()
                .log()
                .all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response putRequestWithoutBody(String endPoint, Integer expectedStatusCode, Object body) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessTokenAdmin)
                .body(body)
                .when()
                .log().all()
                .put (endPoint)
                .then()
                .log()
                .all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }


    public static Response getRequest(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .when().log().all()
                .get(endPoint)
                .then().log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

    private static String getAccessTokenFromRefreshToken(String refreshToken) {
        return refreshToken;
    }

    private static String getAccessTokenAdminFromRefreshToken(String refreshToken) {
        return refreshToken;
    }
    static RequestSpecification specificationWithoutAccessToken = new RequestSpecBuilder ()
            .setBaseUri (BASE_URI)
            .setContentType (ContentType.JSON)
            .build ();

    public static Response postRequestWithoutBody(String endPoint, Integer expectedStatusCode ){
        Response response = given()
                .spec(specificationWithoutAccessToken)
                .when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response getRequestWithoutBody(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specificationWithoutAccessToken)
                .when()
                .log().all()
                .get(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response getRequestAdmin(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessTokenAdmin)
                .when().log().all()
                .get(endPoint)
                .then().log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response createPost(CreateUserPostResponse postRequest, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .body(postRequest)
                .when()
                .log().all()
                .post("/posts")
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response createRequestWithoutBody(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response createRequestWithoutBodyAndToken(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer ")
                .when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response postRequestAdmin(String endPoint, Integer expectedStatusCode, Object body) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessTokenAdmin)
                .body(body)
                .when()
                .log().all()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }

    public static Response deleteRequest(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .when().log().all()
                .delete(endPoint)
                .then().log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response deleteRequestAdmin(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessTokenAdmin)
                .when().log().all()
                .delete(endPoint)
                .then().log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }
    public static Response deleteRequestWithoutAuthorization(String endPoint, Integer expectedStatusCode) {
        Response response = given()
                .spec(specification)
                .header("вфывыфв", "Bearer " + accessToken)
                .when().log().all()
                .delete(endPoint)
                .then().log().all()
                .statusCode(expectedStatusCode)
                .extract().response();
        return response;
    }





}
