package GoRest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestUsersTests {
    Faker faker = new Faker();
    int userID;

    RequestSpecification requestSpecification;
    @BeforeClass
    public void setup(){
        baseURI  = "https://gorest.co.in/public/v2/users";
        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer 5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542")
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURI)
                .build();

    }

    @Test(enabled = false)
    public void createUserJson() {
        // POST https://gorest.co.in/public/v2/users
        //5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542
        // {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}


        String rndFullname = faker.name().fullName();
        String rndEmail = faker.internet().emailAddress();

        userID =
                given()
                        .spec(requestSpecification)
                        .body("{\"name\":\"" + rndFullname + "\", \"gender\":\"male\", \"email\":\"" + rndEmail + "\", \"status\":\"active\"}")
                        .log().uri()
                        .log().body()

                        .when()
                        .post("")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id")


        ;

    }

    @Test
    public void createUserMap() {
        String rndFullname = faker.name().fullName();
        String rndEmail = faker.internet().emailAddress();

        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", rndFullname);
        newUser.put("gender", "male");
        newUser.put("email", rndEmail);
        newUser.put("status", "active");


        userID =
                given()
                        .spec(requestSpecification)
                        .body(newUser)
                        .log().uri()
                        .log().body()

                        .when()
                        .post("")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id")


        ;

    }
    @Test(enabled = false)
    public void createUserClass() {
        String rndFullname = faker.name().fullName();
        String rndEmail = faker.internet().emailAddress();

       User newUser=new User();
       newUser.name=rndFullname;
       newUser.gender="male";
       newUser.email=rndEmail;
       newUser.status="active";


        userID =
                given()
                        .spec(requestSpecification)
                        .body(newUser)
                        .log().uri()
                        .log().body()

                        .when()
                        .post("")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id")


        ;

    }

    @Test(dependsOnMethods = "createUserMap")
    public void getUserByID() {
        given()
                .spec(requestSpecification)

                .when()
                .get("" + userID)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userID))

        ;

    }


    @Test(dependsOnMethods = "getUserByID")
    public void updateUser() {

        Map<String,String> updateUser=new HashMap<>();
        updateUser.put("name","zorbey durmaz");


        given()
                .spec(requestSpecification)
                .body(updateUser)
                .when()
                .put(""+userID)

                .then()
                .log().body()
                .statusCode(200)
                .body("id",equalTo(userID))
                .contentType(ContentType.JSON)

        ;

    }

    @Test(dependsOnMethods = "updateUser")
    public void deleteUser() {

        given()
                .spec(requestSpecification)

                .when()
                .delete(""+userID)

                .then()
                .log().all()
                .statusCode(204)


        ;

    }

    @Test(dependsOnMethods = "deleteUser")
    public void deleteUserNegative() {
        given()
                .spec(requestSpecification)

                .when()
                .delete(""+userID)

                .then()
                .log().all()
                .statusCode(404);




    }
}
