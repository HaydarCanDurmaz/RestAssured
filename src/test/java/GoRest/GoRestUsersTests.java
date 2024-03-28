package GoRest;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestUsersTests {
    Faker faker = new Faker();
    int userID;

    @Test
    public void createUserJson() {
        // POST https://gorest.co.in/public/v2/users
        //5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542
        // {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}


        String rndFullname = faker.name().fullName();
        String rndEmail = faker.internet().emailAddress();

        userID =
                given()
                        .header("Authorization", "Bearer 5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542")
                        .contentType(ContentType.JSON)
                        .body("{\"name\":\"" + rndFullname + "\", \"gender\":\"male\", \"email\":\"" + rndEmail + "\", \"status\":\"active\"}")
                        .log().uri()
                        .log().body()

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

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
                        .header("Authorization", "Bearer 5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542")
                        .contentType(ContentType.JSON)
                        .body(newUser)
                        .log().uri()
                        .log().body()

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .extract().path("id")


        ;

    }
    @Test
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
                        .header("Authorization", "Bearer 5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542")
                        .contentType(ContentType.JSON)
                        .body(newUser)
                        .log().uri()
                        .log().body()

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

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
                .header("Authorization", "Bearer 5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542")
                .contentType(ContentType.JSON)

                .when()
                .get("https://gorest.co.in/public/v2/users/" + userID)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userID))

        ;

    }


    @Test(dependsOnMethods = "getUserByID")
    public void updateUser() {
        given()
                .header("Authorization", "Bearer 5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542")
                .contentType(ContentType.JSON)

                .when()
                .put("https://gorest.co.in/public/v2/users/")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)

        ;

    }

    @Test(dependsOnMethods = "updateUser")
    public void deleteUser() {

    }

    @Test(dependsOnMethods = "deleteUser")
    public void deleteUserNegative() {

    }
}
