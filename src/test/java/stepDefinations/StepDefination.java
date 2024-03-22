package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Addplace;
import pojo.locdetails;
import resources.APIResources;
import resources.TestDatabuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response finalresp;
	TestDatabuild td = new TestDatabuild();
	static String placeid;
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address)  throws IOException {
									
	   res = given().log().all().spec(RequestSpecification()).body(td.addplacePayLoad(name, language,address ));
	   resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	@When("user call {string} with {string} http request")
	public void user_call_with_post_http_request(String resource , String perform) {
		//introducing enum
		APIResources resAPI = APIResources.valueOf(resource);
		System.out.println(resAPI.getResource());
		
//		 finalresp = res.when().post("/maps/api/place/add/json").then().log().all().spec(resp).and().body("status",equalTo("OK"))
//	  			.and().body("scope",equalTo("APP")).extract().response();
		 
		if(perform.equalsIgnoreCase("post"))
		{
		 finalresp = res.when().post(resAPI.getResource()).then().log().all().spec(resp).and().body("status",equalTo("OK"))
		  			.and().body("scope",equalTo("APP")).extract().response();
		}
		else if(perform.equalsIgnoreCase("get"))
		{
			finalresp = res.when().get(resAPI.getResource()).then().log().all().spec(resp).extract().response();
		}
		else
		{
			finalresp = res.when().delete(resAPI.getResource()).then().log().all().spec(resp).extract().response();
		}
		
	    
	}

	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
	    
		assertEquals(finalresp.getStatusCode(), 200);
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String key, String value) {
	   
//		String resString= finalresp.asString();
//		JsonPath js = new JsonPath(resString);
	//	assertEquals(js.get(key).toString(), value);
		assertEquals(getJsonPath(finalresp, key), value);
	}
	
	@Then("verify {string} created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using( String key, String name, String resource) throws IOException {
		
		placeid=getJsonPath(finalresp, key);
		
		res = given().log().all().spec(RequestSpecification()).queryParam("place_id",placeid);
		
		user_call_with_post_http_request( resource , "get");
		
		String getname=getJsonPath(finalresp, "name");
		System.out.println(" Name is " + getname);
	   
		assertEquals(getname, name);
	}
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	   
		res = given().log().all().spec(RequestSpecification()).body(td.deletePayLoad(placeid));
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}


}
