package com.gorest.cucumber.steps;

import com.gorest.userinfo.GoRestSteps;
import com.gorest.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {

    static ValidatableResponse response;

    static String email = null;

    static String name = null;

    static int gorestId;

    static final String token = "Bearer a329e99dc701baa9a84abdaf264dd68759c23dc0a094a98348a2c9e5d7173d32";

    @Steps
    GoRestSteps goRestSteps;


    @When("^User sends a GET request to userID endpoint$")
    public void userSendsAGETRequestToUserIDEndpoint() {
        response = goRestSteps.getUserIDs(gorestId, token);
    }

    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^I create a new student by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewStudentByProvidingTheInformationNameEmailGenderStatus(String _name, String _email, String gender, String status) {
        email = TestUtils.getRandomValue() + _email;
        name = TestUtils.getRandomValue() + _name;
        response = goRestSteps.createUserRecord(name, email,gender,status,token);
        gorestId = response.extract().path("id");
    }

    @Then("^I verify that user is created with \"([^\"]*)\"$")
    public void iVerifyThatUserIsCreatedWith(String field) {
        response.statusCode(201);
//        if (field.contains("gmail.com")) {
//            HashMap<String, Object> studentMap = goRestSteps.getgorestInfoByEmail(email);
//            gorestId = (int) studentMap. get("id");
//            Assert.assertThat(studentMap, hasValue(email));
//        }else{
//            HashMap<String, Object> studentMap = goRestSteps.getGorestInfoByname(name);
//            gorestId = (int) studentMap. get("id");
//            Assert.assertThat(studentMap, hasValue(name));
//        }
    }

    @Given("^Gorest application is running$")
    public void gorestApplicationIsRunning() {
    }

    @And("^I update user with usertId name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iUpdateUserWithUsertIdNameEmailGenderStatus(String _name, String _email, String gender, String status) {
        List<String> List = new ArrayList<>();
        name = name +"_updated";
        email = TestUtils.getRandomValue() + _email;
        name = TestUtils.getRandomValue() + _name;
        response = goRestSteps.userRecordUpdate(gorestId, name, email,gender,status,token);
    }

    @And("^U check User is update successfully$")
    public void uCheckUserIsUpdateSuccessfully() {
    }

    @And("^I delete user with userId$")
    public void iDeleteUserWithUserId() {
    }

    @Then("^I verify that user is deleted successfully$")
    public void iVerifyThatUserIsDeletedSuccessfully() {
    }
}
