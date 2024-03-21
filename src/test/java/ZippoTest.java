import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers.*;

public class ZippoTest {
    @Test
    public void test(){ // https://api.zippopotam.us/us/90210
        given().when().then();

    }
    @Test
    public void statusCodeTest(){
        given()

                .when()
                .get("https://api.zippopotam.us/us/90210")

                .then()
                .log().body() // .log().all() Her şeyi alır
                .statusCode(200)

        ;
    }
    @Test
    public void contenTypeCodeTest(){
        given()

                .when()
                .get("https://api.zippopotam.us/us/90210")

                .then()
                .log().body() // .log().all() Her şeyi alır
                .statusCode(200)
                .contentType(ContentType.JSON) // dönen sonuç JSon mı

        ;
    }
    @Test
    public void checkCountryResponsitvBody(){
        given()

                .when()
                .get("https://api.zippopotam.us/us/90210")

                .then()
                .log().body() // .log().all() Her şeyi alır
                .statusCode(200) // dönüş kodu 200 mü
                .contentType(ContentType.JSON) // dönen sonuç JSon mı
                .body("country",equalTo("United States")) // body conutry degişkeni United States eşitmi

        ;
    }
}
