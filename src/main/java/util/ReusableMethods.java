package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.CreateJson;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static String jsonValueString(CreateJson createJson) throws JsonProcessingException {

        String createBody = new ObjectMapper().writeValueAsString(createJson);
        return createBody;
    }

    public static JsonPath rawToJson(Response r)
    {
        String respon=r.asString();
        JsonPath x=new JsonPath(respon);
        System.out.println("Response: "+respon+"\n~~~~~~~~~~~~~~~~~~~~~");
        return x;
    }

    public static void sendPostAddBookRequest(String BaseUrl, CreateJson RequestBody, String Endpoint) throws JsonProcessingException {

        RestAssured.baseURI=BaseUrl;

        Response resp=given().log().all()
                .header("Content-Type","application/json")
                .body(RequestBody)
                .when().post(Endpoint)
                .then().assertThat().statusCode(200).extract().response();

       ReusableMethods.rawToJson(resp);
    }

    public static void sendPutUpdateDogRequest(String BaseUrl, CreateJson RequestBody, String Endpoint) throws JsonProcessingException {
        RestAssured.baseURI = BaseUrl;
        Response response = given().log().all().header("Content-Type", "application/json")
                .body(RequestBody)
                .when().put(Endpoint)
                .then().assertThat().statusCode(200).extract().response();

        ReusableMethods.rawToJson(response);
    }

    public static void sendGetListDogRequest (String BaseUrl, String Path, int Id){
        RestAssured.baseURI = BaseUrl;
        Response response = given().log().all().header("Content-Type", "application/json")
                .when().get(Path + Id)
                .then().assertThat().statusCode(200).extract().response();

        ReusableMethods.rawToJson(response);

    }

    public static void sendDeleteDogRequest(String BaseUrl, String Path, int Id){
        RestAssured.baseURI = BaseUrl;
        Response response = given().log().all()
                .header("Content-Type", "application/json")
                .when().delete(Path + Id)
                .then().assertThat().statusCode(200).extract().response();

        ReusableMethods.rawToJson(response);
    }
}

