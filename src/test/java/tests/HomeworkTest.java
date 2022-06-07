package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class HomeworkTest {

    @Test
    void getListUsers1() {

        String body = "{ page: 2, per_page: 6, total: 12, total_pages: 2, data: [ { id: 7, email: \"michael.lawson@reqres.in\", first_name: \"Michael\", last_name: \"Lawson\", avatar: \"https://reqres.in/img/faces/7-image.jpg\" }, { id: 8, email: \"lindsay.ferguson@reqres.in\", first_name: \"Lindsay\", last_name: \"Ferguson\", avatar: \"https://reqres.in/img/faces/8-image.jpg\" }, { id: 9, email: \"tobias.funke@reqres.in\", first_name: \"Tobias\", last_name: \"Funke\", avatar: \"https://reqres.in/img/faces/9-image.jpg\" }, { id: 10, email: \"byron.fields@reqres.in\", first_name: \"Byron\", last_name: \"Fields\", avatar: \"https://reqres.in/img/faces/10-image.jpg\" }, { id: 11, email: \"george.edwards@reqres.in\", first_name: \"George\", last_name: \"Edwards\", avatar: \"https://reqres.in/img/faces/11-image.jpg\" }, { id: 12, email: \"rachel.howell@reqres.in\", first_name: \"Rachel\", last_name: \"Howell\", avatar: \"https://reqres.in/img/faces/12-image.jpg\" } ], support: { url: \"https://reqres.in/#support-heading\", text: \"To keep ReqRes free, contributions towards server costs are appreciated!\" } }";
        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[1].email", is("lindsay.ferguson@reqres.in"));
    }

    @Test
    void getListUsers2() {

        String body = "{ page: 2, per_page: 6, total: 12, total_pages: 2, data: [ { id: 7, email: \"michael.lawson@reqres.in\", first_name: \"Michael\", last_name: \"Lawson\", avatar: \"https://reqres.in/img/faces/7-image.jpg\" }, { id: 8, email: \"lindsay.ferguson@reqres.in\", first_name: \"Lindsay\", last_name: \"Ferguson\", avatar: \"https://reqres.in/img/faces/8-image.jpg\" }, { id: 9, email: \"tobias.funke@reqres.in\", first_name: \"Tobias\", last_name: \"Funke\", avatar: \"https://reqres.in/img/faces/9-image.jpg\" }, { id: 10, email: \"byron.fields@reqres.in\", first_name: \"Byron\", last_name: \"Fields\", avatar: \"https://reqres.in/img/faces/10-image.jpg\" }, { id: 11, email: \"george.edwards@reqres.in\", first_name: \"George\", last_name: \"Edwards\", avatar: \"https://reqres.in/img/faces/11-image.jpg\" }, { id: 12, email: \"rachel.howell@reqres.in\", first_name: \"Rachel\", last_name: \"Howell\", avatar: \"https://reqres.in/img/faces/12-image.jpg\" } ], support: { url: \"https://reqres.in/#support-heading\", text: \"To keep ReqRes free, contributions towards server costs are appreciated!\" } }";
        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total_pages", is(2));
    }

    @Test
    void getSingleUser() {

        String body = "{ data: { id: 2, email: \"janet.weaver@reqres.in\", first_name: \"Janet\", last_name: \"Weaver\", " +
                "avatar: \"https://reqres.in/img/faces/2-image.jpg\" }, support: { url: \"https://reqres.in/#support-heading\", " +
                "text: \"To keep ReqRes free, contributions towards server costs are appreciated!\" } }";

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.avatar", is("https://reqres.in/img/faces/2-image.jpg"));
    }

    @Test
    void getSingleUserNotFound() {

        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }


    @Test
    void getListResource() {
        String body = "{ page: 1, per_page: 6, total: 12, total_pages: 2, data: [ { id: 1, name: \"cerulean\", year: 2000, color: \"#98B2D1\", pantone_value: \"15-4020\" }, { id: 2, name: \"fuchsia rose\", year: 2001, color: \"#C74375\", pantone_value: \"17-2031\" }, { id: 3, name: \"true red\", year: 2002, color: \"#BF1932\", pantone_value: \"19-1664\" }, { id: 4, name: \"aqua sky\", year: 2003, color: \"#7BC4C4\", pantone_value: \"14-4811\" }, { id: 5, name: \"tigerlily\", year: 2004, color: \"#E2583E\", pantone_value: \"17-1456\" }, { id: 6, name: \"blue turquoise\", year: 2005, color: \"#53B0AE\", pantone_value: \"15-5217\" } ], support: { url: \"https://reqres.in/#support-heading\", text: \"To keep ReqRes free, contributions towards server costs are appreciated!\" } }";
        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data[5].name", is("blue turquoise"));
    }

    @Test
    void postCreate() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void putUpdate() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";
        given()
                .body(body)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", is("2022-06-07T17:41:26.056Z")); // не поняла как решить. начение динамичное ?
    }

    @Test
    void postREGISTERSUCCESSFUL() {
        String body = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void postREGISTERUNSUCCESSFUL() {
        String body = "{ \"email\": \"sydney@fife\" }";

        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void postLOGINSUCCESSFUL() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

                given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", notNullValue());

    }
}
