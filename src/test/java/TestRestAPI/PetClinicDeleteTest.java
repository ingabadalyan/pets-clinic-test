package TestRestAPI;

import Utils.Constants;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetClinicDeleteTest {
    private final static Logger logger = Logger.getLogger(PetClinicDeleteTest.class);

    private String URL;

    @Test(description = "get all the pets")
    public void deleteAnOwner() throws UnirestException {
        logger.info("Running the Test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

        URL = Constants.BASE_URL + "/api/owners/8";
        Assert.assertEquals(deleteData().getCode(), 204);
    }


    private HttpResponse<JsonNode> deleteData() throws UnirestException {
        return Unirest.delete(URL)
                .header("Content-Type", "application/json")
                .asJson();
    }
}
