package com.anthony.utils;

import java.util.HashMap;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestUtils {
	
	//����״̬���ǲ���200��������״̬��,���糣��404��
    public void checkStatusCode (Response res, int statusCode) {
        Assert.assertEquals(res.getStatusCode(), statusCode, "״̬����ʧ��!");
    }
    
    //��ӡ��Ӧȫ������,debug��Ӧ�þ����õ����
    public void printAllResponseText(Response res) {
    	System.out.println(res.then().log().all());
    }
    
    //ֻ��ӡ��Ӧ����
    public void printResponseBody(Response res) {
    	System.out.println(res.then().log().body());
    }
}
