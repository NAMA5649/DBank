package starter.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import starter.status.NaceDetailsValidation;

import java.io.IOException;

public class NaceDetailsValidationStepDefinitions {

    @Steps
    NaceDetailsValidation nacedetailsValidation;

    @Given("I GET the details for NACE \"(.*)\"")
    public void i_get_the_details(String NACECode) {
        nacedetailsValidation.getNACEDetails(NACECode);
    }

    @When("search is executed successfully")
    public void search_is_executed( ) {
        nacedetailsValidation.searchIsExecutedSuccessfully();
    }

    @Then("I validate the NACE details for \"(.*)\"")
    public void validate_Nace_Details(String fileName ) throws IOException {
        nacedetailsValidation.validateJsonBody(fileName);
    }

}
