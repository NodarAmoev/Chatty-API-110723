package test;

import dto.SendFeedbackResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SendFeedbackTest extends BaseTest{
    @Test
    public void sendFeedback(){
        SendFeedbackResponse sendFeedbackResponse = new SendFeedbackResponse();
        sendFeedbackResponse.setName("Michael");
        sendFeedbackResponse.setEmail("nodari.amoev2@gmail.com");
        sendFeedbackResponse.setContent("Привет");

        Response response = postRequest("/feedback", 200, sendFeedbackResponse);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void sendFeedBackWithInvalidEndPoint(){
        SendFeedbackResponse sendFeedbackResponse = new SendFeedbackResponse();
        sendFeedbackResponse.setName("Michael");
        sendFeedbackResponse.setEmail("nodari.amoev2@gmail.com");
        sendFeedbackResponse.setContent("Привет");

        Response response = postRequest("/feedbadsck", 404, sendFeedbackResponse);
        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void seedFeedBackWithoutField(){
        SendFeedbackResponse sendFeedbackResponse = new SendFeedbackResponse();
        sendFeedbackResponse.setName("");
        sendFeedbackResponse.setEmail("");
        sendFeedbackResponse.setContent("");

        Response response = postRequest("/feedback", 400, sendFeedbackResponse);
        assertEquals(400, response.getStatusCode());

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("name"),"Name can not be empty!");
        assertTrue (responseBody.contains ("name"),"Name must contain from 1 to 30 characters");
        assertTrue (responseBody.contains ("content"),"Content can not be empty!");
        assertTrue (responseBody.contains ("content"),"Content must contain from 1 to 30 characters");
        assertTrue (responseBody.contains ("email"),"Email can not be empty!");
    }



    @Test
    public void sendFeedbackAdmin(){
        SendFeedbackResponse sendFeedbackResponse = new SendFeedbackResponse();
        sendFeedbackResponse.setName("Michael");
        sendFeedbackResponse.setEmail("nodari.amoev2@gmail.com");
        sendFeedbackResponse.setContent("Привет");

        Response response = postRequest("/feedback", 200, sendFeedbackResponse);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void sendFeedBackWithInvalidEndPointAdmin(){
        SendFeedbackResponse sendFeedbackResponse = new SendFeedbackResponse();
        sendFeedbackResponse.setName("Michael");
        sendFeedbackResponse.setEmail("nodari.amoev2@gmail.com");
        sendFeedbackResponse.setContent("Привет");

        Response response = postRequest("/feedbadsck", 404, sendFeedbackResponse);
        assertEquals(404, response.getStatusCode());
    }

    @Test
    public void seedFeedBackWithoutFieldAdmin(){
        SendFeedbackResponse sendFeedbackResponse = new SendFeedbackResponse();
        sendFeedbackResponse.setName("");
        sendFeedbackResponse.setEmail("");
        sendFeedbackResponse.setContent("");

        Response response = postRequest("/feedback", 400, sendFeedbackResponse);
        assertEquals(400, response.getStatusCode());

        String responseBody = response.getBody ().asString ();
        assertTrue (responseBody.contains ("name"),"Name can not be empty!");
        assertTrue (responseBody.contains ("name"),"Name must contain from 1 to 30 characters");
        assertTrue (responseBody.contains ("content"),"Content can not be empty!");
        assertTrue (responseBody.contains ("content"),"Content must contain from 1 to 30 characters");
        assertTrue (responseBody.contains ("email"),"Email can not be empty!");
    }
}
