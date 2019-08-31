package com.anthony.cases;


import org.testng.annotations.Test;

import com.anthony.base.TestBase;

public class CaseDemo2 extends TestBase {
	
	@Test
	public void test1() {
		logger.info("Second test case ");
		logger.info(serverHost);
		logger.info(port);
	}

}
