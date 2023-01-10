package com.gorest.cucumber.crudtest;

import com.gorest.testbase.TestBase;
import com.gorest.userinfo.GoRestSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent(threads = "2x")
@UseTestDataFrom("src/test/java/resources/testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateUserDataDrivenTest extends TestBase {
    private String name;
    private String email;
    private String gender;
    private String status;

    static final String token = "Bearer a329e99dc701baa9a84abdaf264dd68759c23dc0a094a98348a2c9e5d7173d32";
    @Steps
    GoRestSteps goRestSteps;

    @Title("Data driven test for adding multiple users to the application")
    @Test
    public void createMultipleUsers(){
        goRestSteps.createUserRecord(name, email, gender, status,token).statusCode(200);
    }
}
