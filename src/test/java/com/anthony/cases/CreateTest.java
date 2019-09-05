package com.anthony.cases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.anthony.base.TestBase;

public class CreateTest extends TestBase {
	// post https://reqres.in/api/users
	// parameters: name, job
	@Test
	public void test01_Create() {
		res = req.param("name","anthony@163.com")
		.param("job", "tester")
		.header("Content-Type", "text/html")
		.when().post("/users");
		testUtils.checkStatusCode(res, 201);
		testUtils.printResponseBody(res);
	}
	
	@Test
	public void test02_Create() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("name","anthony12@126.com");
		map.put("job","dev");
		res = req.formParams(map)
				.header("Content-Type", "text/html")
				.when().post("/users");
		testUtils.checkStatusCode(res, 201);
		testUtils.printResponseBody(res);
		
	}

}
