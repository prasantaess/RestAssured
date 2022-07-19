package com.qa.test.rest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;

@Test(enabled=true)
public class PostCall {
	
	public void postGet() throws InterruptedException {
		int length=20;
		String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" + "1234567890";
	    String email = "";
	    String temp = RandomStringUtils.random(length, allowedChars);
	    email = temp.substring(0, temp.length() - 9) + "@testdata.com";
		System.out.println("Newly Created email id :: " +email);
		
		// Code for the Get method
		System.out.println("Code for the Post Method....................................");
		RestAssured.baseURI = "https://gorest.co.in";
		String Query= "{ \r\n"
				+ "\"name\":\"Prasanta\",\r\n"
				+ "\"gender\":\"male\",\r\n"
				+ "\"status\":\"inactive\",\r\n"
				+ "\"email\":\""+email+"\"\r\n"
				+ "}";
			String TokenID ="41e821b9946fd77b3459e8532ab05232d918a98ed0e4f21b4f09cbd782545030";	
		Response Response = given().log().all().contentType("application/json").header("Authorization","Bearer 41e821b9946fd77b3459e8532ab05232d918a98ed0e4f21b4f09cbd782545030").
		body(Query).when().log().all().post("/public/v2/users").then().log().all().statusCode(201).extract().response();
		System.out.println(Response.getStatusLine());
		System.out.println(Response.getStatusCode());
		System.out.println(Response.getBody().asString());
		JsonPath jsonpath = Response.jsonPath();
		int id = jsonpath.get("id");
		System.out.println(id);
		System.out.println("Current Status is:: "+jsonpath.get("status"));
		System.out.println("Current name is:: "+jsonpath.get("name"));
		System.out.println("Current email is:: "+jsonpath.get("email"));
		Thread.sleep(10000);
		// Code for the Get 
		System.out.println("Code for the Get Method....................................");
		Response GetResponse = given().log().all().contentType("application/json").header("Authorization","Bearer 41e821b9946fd77b3459e8532ab05232d918a98ed0e4f21b4f09cbd782545030").
				               get("/public/v2/users/"+id+"").then().log().all().statusCode(200).extract().response();
		System.out.println(GetResponse.statusLine());
		System.out.println(GetResponse.sessionId());
		System.out.println(GetResponse.getStatusCode());
		JsonPath getJsonPath = GetResponse.jsonPath();
		System.out.println(getJsonPath.get("id"));
		System.out.println(getJsonPath.get("name"));
		System.out.println(getJsonPath.get("email"));
		System.out.println(getJsonPath.get("status"));
		System.out.println(getJsonPath.get("gender"));
		String GCStatus=getJsonPath.get("status");
		String gEmail= getJsonPath.get("email");
		Assert.assertEquals(GCStatus.contains("inactive"), true);
		Assert.assertEquals(gEmail.contains(email), true);
		System.out.println("Code for the Put Method....................................");
		
		
		
		
		// Code for the Put Method..
		String putQuery= "{\r\n"
				+ "       \"status\" : \"active\" \r\n"
				+ "      }";
		Response Responseptu = given().log().all().contentType("application/json").header("Authorization","Bearer 41e821b9946fd77b3459e8532ab05232d918a98ed0e4f21b4f09cbd782545030")
								.body(putQuery).put("/public/v2/users/"+id+"").then().log().all().statusCode(200).extract().response();
		
		JsonPath Jsonpathput = Responseptu.jsonPath();
		String Cstatus =Jsonpathput.get("status");
		System.out.println("Current Status is:: "+Jsonpathput.get("status"));
		Assert.assertEquals(Cstatus.contains("active"), true);
		
		System.out.println("Code for the Delete Method....................................");
		
		Response DeleteResponse = given().log().all().contentType("application/json").header("Authorization","Bearer 41e821b9946fd77b3459e8532ab05232d918a98ed0e4f21b4f09cbd782545030")
									.delete("/public/v2/users/"+id+"").then().log().all().statusCode(204).extract().response();
		
		JsonPath delJsonPath = DeleteResponse.jsonPath();
		System.out.println(DeleteResponse.asString());
		
		System.out.println("Code for the Get Method....................................");
		Response GetResponse2 = given().log().all().contentType("application/json").header("Authorization","Bearer 41e821b9946fd77b3459e8532ab05232d918a98ed0e4f21b4f09cbd782545030").
				               get("/public/v2/users/"+id+"").then().log().all().statusCode(404).extract().response();
		System.out.println(GetResponse2.statusLine());
		System.out.println(GetResponse2.sessionId());
		System.out.println(GetResponse2.getStatusCode());
		JsonPath getJsonPath2 = GetResponse2.jsonPath();
		System.out.println(getJsonPath2.get("message"));
		String message=getJsonPath2.get("message");
		Assert.assertEquals(message.contains("Resource not found"), true);
		
		
		
		
	}
	
	
@Test(enabled=false)
public void GetAPIOperations() {
	
	 RestAssured.baseURI = "https://gorest.co.in";
	 
	 Response Response = given().log().all().get("/public/v2/users/4565").then().log().all().statusCode(200).and().log().all().body("id", equalTo(3334)).
	 body("name", equalTo("Prasanta")).body("email", equalTo("prasanta30@yopmail.com"))
	 .body("gender", equalTo("male")).
	 extract().response();
	 ResponseBody body = Response.getBody();
	 System.out.println("----------------------------------------------------");
	 System.out.println("The Response Status is: "+Response.getStatusLine());
	 System.out.println("The Response Status is: "+Response.getStatusCode());
	 System.out.println("The Response Body is: "+body.asString());
	 //System.out.println("The Response Body is: "+body));
	
	//System.out.println(Response);
	
	
}

@Test(enabled=false)
public void GetAPIOperations2() {
	
	 RestAssured.baseURI = "https://gorest.co.in";
	 String Path ="/public/v2/users/3299";
	 
	 Response Response = given().log().all().get(Path).then().log().all().statusCode(200).
	 extract().response();
	 ResponseBody body = Response.getBody();
	 System.out.println("----------------------------------------------------");
	 System.out.println("The Response Status is: "+Response.getStatusLine());
	 System.out.println("The Response Status is: "+Response.getStatusCode());
	 System.out.println("The Response Body is: "+body.asString());
	 String bodyString = body.asString();
	 Assert.assertEquals(bodyString.contains("Vidur Mukhopadhyay"), true,"contain the name");
	 JsonPath jsonpath = Response.jsonPath();
	 System.out.println("The email ID is: "+jsonpath.get("email"));
	
	//System.out.println(Response);
	
	
}

}
