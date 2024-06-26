import Model.ToDo;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Task {
    @Test
    public void task1() {
        /**
         * Task 1
         * create a request to https://httpstat.us/203
         * expect status 203
         * expect content type TEXT
         */
        given()
                .when()
                .get("https://httpstat.us/203")

                .then()
                .log().all()
                .statusCode(203)
                .contentType(ContentType.TEXT)
        ;

    }
    @Test
    public void task2(){
        /**
         * Task 2
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * Converting Into POJO
         */
        ToDo todo=
        given()


                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")


                .then()
                .log().body()
                .statusCode(200)
                .extract().body().as(ToDo.class)
        ;
        System.out.println("todo = " + todo);
        System.out.println("todo.getTitle() = " + todo.getTitle());
        System.out.println("todo.getId() = " + todo.getId());

    }
    @Test
    public void task3(){
        /**
         * Task 3
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * expect content type JSON
         * expect title in response body to be "quis ut nam facilis et officia qui"
         */


        given()

                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")


                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("quis ut nam facilis et officia qui"))
                .body("userId",equalTo(1))

                ;

    }
    /**
     * Task 4
     * create a request to https://jsonplaceholder.typicode.com/todos/2
     * expect status 200
     * expect content type JSON
     * expect response completed status to be false(hamcrest)
     * extract completed field and testNG assertion(testNG)
     */

    @Test
    public void task4(){
        // Birinci Yöntem (hamscrest)
        given()

                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()
                .statusCode(200)
                .body("completed",equalTo(false))
                ;

        // ikinci Yöntem (TEstNG)
        Boolean completed=
        given()

                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .log().body()
                .statusCode(200)
                .extract().path("completed")
        ;
        Assert.assertFalse(completed);
    }

}