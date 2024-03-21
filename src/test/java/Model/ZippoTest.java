package Model;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers.*;

public class ZippoTest {
    @Test
    public void test() { // https://api.zippopotam.us/us/90210
        given().when().then();

    }

    @Test
    public void statusCodeTest() {
        given()

                .when()
                .get("https://api.zippopotam.us/us/90210")

                .then()
                .log().body() // .log().all() Her şeyi alır
                .statusCode(200)

        ;
    }

    @Test
    public void contenTypeCodeTest() {
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
    public void checkCountryResponsitvBody() {
        given()

                .when()
                .get("https://api.zippopotam.us/us/90210")

                .then()
                .log().body() // .log().all() Her şeyi alır
                .statusCode(200) // dönüş kodu 200 mü
                .contentType(ContentType.JSON) // dönen sonuç JSon mı
                .body("country", equalTo("United States")) // body conutry degişkeni United States eşitmi

        ;
    }

    @Test
    public void checkCityInResponsiveBody() {
        given()

                .when()
                .get("https://api.zippopotam.us/us/90210")

                .then()
                .log().body() // .log().all() Her şeyi alır
                .statusCode(200) // dönüş kodu 200 mü
                .contentType(ContentType.JSON) // dönen sonuç JSon mı
                .body("places[0].state", equalTo("California")) // body conutry degişkeni United States eşitmi

        ;
    }

    @Test
    public void chackHasItemy() {
        given()

                .when()
                .get("https://api.zippopotam.us/tr/01000")

                .then()
                .log().body() // .log().all() Her şeyi alır
                .statusCode(200) // dönüş kodu 200 mü
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))


        ;
    }

    @Test
    public void BodyArrayHasSizeTest() {
        given()

                .when()
                .get("https://api.zippopotam.us/us/90210")

                .then()
                // .log().body() // .log().all() Her şeyi alır
                .statusCode(200) // dönüş kodu 200 mü
                .body("places", hasSize(1))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))


        ;
    }

    @Test
    public void pathparamTest() {
        given()
                .pathParam("ulke", "us")
                .pathParam("postaKod", 90210)


                .when()
                .get("https://api.zippopotam.us/{ulke}/{postaKod}")

                .then()
                .statusCode(200)
                //.log().body()


        ;
    }
}
