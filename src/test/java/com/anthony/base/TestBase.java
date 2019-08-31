package com.anthony.base;

import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public Logger logger;
	
	public static String serverHost;
	public static String port;
	
	static {
		// 用于加载properties文件
		// 注意这里不需要文件扩展名.properties
		ResourceBundle rb = ResourceBundle.getBundle("config"); 
		serverHost = rb.getString("Host");
		port = rb.getString("Host");
	}

	@BeforeClass
	public void setup() {
		logger = Logger.getLogger("RestAPI");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		//logger.info("host: " + serverHost);
		//logger.info("port: " + port);
	}
	
}
