package starter.status;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;

public class NaceDetailsValidation {
    private String NACE_CODE_SEARCH ="https://getNaceDetails/";
    public Response response;

    public NaceDetailsValidation() throws IOException {
    }

    @Step("I GET the details for NACE {}")
    public void getNACEDetails(String NACECode) {
        response = SerenityRest.rest().contentType("application/json")
                .when()
                .get(NACE_CODE_SEARCH + NACECode);
    }

    @Step("search is executed successfully")
    public void searchIsExecutedSuccessfully(){
        response.then().statusCode(200);
    }

    @Step("I validate the NACE details for {}")
    public void validateJsonBody(String fileName) throws IOException {
        String localDir = System.getProperty("user.dir");
        String fileContent = FileUtils.readFileToString(new File(localDir+"/src/test/resources/templates/"+ fileName), "UTF-8");
        String actualResponse = response.then().extract().body().toString();
        JsonParser parser = new JsonParser();
        JsonElement expected = parser.parse(fileContent);
        JsonElement actual = parser.parse(actualResponse);
        Assert.assertEquals(expected, actual);
    }

}
