package com.qa.test.rest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;

public class GetCallBBD {
	@Test
	public void RestAssureApiBBD() {
		
		given().when().log().all().get("https://reqres.in/api/unknown").then().
		assertThat().statusCode(200).log().all().
		and().body("data.id", hasSize(6)).log().all();
		
	}
	
	
	
	
	
	
	

}
