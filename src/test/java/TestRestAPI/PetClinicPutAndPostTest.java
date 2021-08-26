package TestRestAPI;

import Utils.Constants;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetClinicPutAndPostTest {
    private final static Logger logger = Logger.getLogger(PetClinicPutAndPostTest.class);

    private String URL;
    private String dataToPost;

    @Test(description = "Unparseable date")
    public void createAPetError() throws UnirestException {
        logger.info("Running the Test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        URL = Constants.BASE_URL + Constants.BASE_PATH_PET;
        dataToPost = "{ \"birthDate\": \"2021-02-03T11:33:26.029Z\", \"id\": 0, \"name\": \"string\", \"owner_id\":0, \"type_id\": 0, \"visits\": [ { \"date\": \"yyyy/MM/dd\", \"description\": \"string\", \"id\": 0 } ] }";

        String expectedResponse = "Unparseable date";

         Assert.assertTrue(postDataToEndpoint(dataToPost).getBody().toString().contains(expectedResponse));

    }

    @Test(description = "a pet creation")
    public void createAPet() throws UnirestException {
        logger.info("Running the Test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        URL = Constants.BASE_URL + Constants.BASE_PATH_PET+"1";
        dataToPost = "{ \"birthDate\": \"2021/02/03\", \"id\": 0, \"name\": \"string\", \"owner_id\":0, \"type_id\": 0, \"visits\": [ { \"date\": \"yyyy/MM/dd\", \"description\": \"string\", \"id\": 0 } ] }";

        Assert.assertEquals(putDataToEndpoint(dataToPost).getCode(), 201);
    }

    private HttpResponse<JsonNode> putDataToEndpoint(String dataToPost) throws UnirestException {
        return Unirest.put( URL)
                .header("Content-Type", "application/json")
                .body(dataToPost)
                .asJson();
    }

    private HttpResponse<JsonNode> postDataToEndpoint(String dataToPost) throws UnirestException {
        return Unirest.post( URL)
                .header("Content-Type", "application/json")
                .body(dataToPost)
                .asJson();
    }
}
