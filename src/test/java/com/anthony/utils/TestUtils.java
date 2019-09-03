package com.anthony.utils;

import java.util.HashMap;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestUtils {
	
	//断言状态码是不是200或者其他状态码,例如常见404等
    public void checkStatusCode (Response res, int statusCode) {
        Assert.assertEquals(res.getStatusCode(), statusCode, "状态码检查失败!");
    }
    
    //打印响应全部内容,debug中应该经常用到这个
    public void printAllResponseText(Response res) {
    	System.out.println(res.then().log().all());
    }
    
    //只打印响应正文
    public void printResponseBody(Response res) {
    	System.out.println(res.then().log().body());
    }
}
