package com.gorest.cucumber.crudtest;

import com.gorest.testbase.TestBase;
import com.gorest.userinfo.GoRestSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static com.gorest.utils.TestUtils.getRandomValue;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class GoRestCURDTest extends TestBase {
    static String name = "pari";
    static String email = "pari" + getRandomValue() + "@gmail.com";
    static String gender = "female";
    static String status = "active";

    static int id;

    static final String token = "Bearer a329e99dc701baa9a84abdaf264dd68759c23dc0a094a98348a2c9e5d7173d32";


    @Steps
    GoRestSteps goRestSteps;

    @Title("This method will get all users records")
    @Test
    public void test001() {
        goRestSteps.getUserIDs(id, token);
    }

    @Title("This method will create a new users record and verify it by its ID")
    @Test
    public void test002() {
        ValidatableResponse getId = goRestSteps.createUserRecord(name, email, gender, status,token);
        id = getId.extract().path("id");
        ValidatableResponse response = goRestSteps.getUserIDs(id,token);
        ArrayList<?> userId = response.extract().path("id");
        Assert.assertTrue(userId.contains(id));
    }

    @Title("This method will update the existing record")
    @Test
    public void test003() {
        status = "inactive";
        goRestSteps.userRecordUpdate(id, name, email, gender, status, token);
        ValidatableResponse response = goRestSteps.getSingleUser(id).statusCode(200);
        HashMap<String, ?> userRecord = response.extract().path("");
        Assert.assertThat(userRecord, hasValue("inactive"));
    }

    @Title("This method will delete the existing record")
    @Test
    public void test004() {
        goRestSteps.deleteUserRecord(id).statusCode(204);
        goRestSteps.getSingleUser(id).statusCode(404);
    }
}


