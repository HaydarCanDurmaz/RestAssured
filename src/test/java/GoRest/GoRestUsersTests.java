package GoRest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestUsersTests {
    @Test
    public void createUser(){
        // POST https://gorest.co.in/public/v2/users
        //5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542
        // {"name":"{{$randomFullName}}", "gender":"male", "email":"{{$randomEmail}}", "status":"active"}

        int userID=
        given()
                .header("Authorization","Bearer 5d0ab4dcbd48e6378fefac952a31f043225aa55d8f3de3e8f0d7d0cbd3c82542")
                .contentType(ContentType.JSON)
                .body("{\"name\":\"zorbeyDurmaz\", \"gender\":\"male\", \"email\":\"zorbey@gmail.com\", \"status\":\"active\"}")
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
    public void getUserByID(){

    }
    @Test
    public void updatUser(){

    }
    @Test
    public void deleteUser(){

    }
    @Test
    public void deleteUserNegative(){

    }
}
