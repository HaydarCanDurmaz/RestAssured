import GoRest.User;
import Model.Location;
import Model.Place;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class PathAndJsonPath {
    @Test
    public void extractingPath() {
// "post code": "90210"
        int postCode =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("'post code'")
                ;
        System.out.println("postCode = " + postCode);
    }
    @Test
    public void extractingJsonPath() {
// "post code": "90210"
        int postCode =
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().jsonPath().getInt("'post code'")
                // tip dönüşümü otomatik, uygun tip verilmeli
                ;
        System.out.println("postCode = " + postCode);
    }
    @Test
    public void getUsers(){
        Response response=
        given()

                .when()
                .get("https://gorest.co.in/public/v2/users")

                .then()
               // .log().body()
                .extract().response()

                ;
        int idPath=response.path("[2].id");
        int idjSonPath=response.jsonPath().getInt("[2].id");

        System.out.println("idPath = " + idPath);
        System.out.println("idjSonPath = " + idjSonPath);

        User[] usersPath = response.as(User[].class);
        List<User> usersJsonPath = response.jsonPath().getList("", User.class);

        System.out.println("usersPath = " + Arrays.toString(usersPath));
        System.out.println("usersJsonPath = " + usersJsonPath);


    }
    @Test
    public void getUsersV1(){
        Response response=
        given()

                .when()
                .get("https://gorest.co.in/public/v1/users")



                .then()
                //.log().body()
                .extract().response();
        List<User> dataUsers = response.body().jsonPath().getList("data", User.class);
        // JSONPATH bir response içindeki bir parçayı nesneye ödnüştürebiliriz.
        System.out.println("dataUsers = " + dataUsers);
        // Daha önceki örneklerde (as) Clas dönüşümleri için tüm yapıya karşılık gelen
        // gereken tüm classları yazarak dönüştürüp istediğimiz elemanlara ulaşıyorduk.

        // Burada ise(JsonPath) aradaki bir veriyi clasa dönüştürerek bir list olarak almamıza
        // imkan veren JSONPATH i kullandık.Böylece tek class ile veri alınmış oldu
        // diğer class lara gerek kalmadan

        // path : class veya tip dönüşümüne imkan veremeyen direk veriyi verir. List<String> gibi
        // jsonPath : class dönüşümüne ve tip dönüşümüne izin vererek , veriyi istediğimiz formatta verir.


    }
    @Test
    public void getZipCode(){

        Response response =
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                //.log().body()
                .extract().response()
                ;
        Location locPathas= response.as(Location.class);// Bütün classlarını yazmak zorundasın
        System.out.println("locPathas = " + locPathas.getPlaces());

        List<Place> places = response.jsonPath().getList("places",Place.class); //bNokta atışı istedigimiz nesneyi aldık
        System.out.println("places = " + places);
    }
}
