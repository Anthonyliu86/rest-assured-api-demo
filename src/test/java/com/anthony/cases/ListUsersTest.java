package com.anthony.cases;

import org.testng.annotations.Test;

import com.anthony.base.TestBase;

public class ListUsersTest extends TestBase {
	
	// get https://reqres.in/api/users?page=2
	@Test
	public void test01_ListUsers() {
		res = req.get("/users?page=2");
		jp = getJsonPath(res);
		testUtils.checkStatusCode(res, 200);
		testUtils.printAllResponseText(res);
	}

}
