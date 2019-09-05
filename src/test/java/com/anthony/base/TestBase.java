package com.anthony.base;

import java.util.ResourceBundle;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.anthony.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public Logger logger;
	
	public static String serverHost;
	public static String port;
	//Global Setup Variables
	public Response res = null; //Response
	public JsonPath jp = null; //JsonPath
    //���������ж��Դ�������������� testUtils����
  	public static TestUtils testUtils = new TestUtils();
  	//��ʼ���������
  	public RequestSpecification req;
  	
	
	static {
		// ���ڼ���properties�ļ�
		// ע�����ﲻ��Ҫ�ļ���չ��.properties
		ResourceBundle rb = ResourceBundle.getBundle("config"); 
		serverHost = rb.getString("Host");
		port = rb.getString("Port");
	}
	
	@BeforeClass
	public void setup() {
		String className = this.getClass().getName();
		logger = Logger.getLogger(className);
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		setBaseURI(); //����Base URI
		//����Base Path����������api��https://reqres.in/�ӿڵ�ַ����api��ͷ��
		//��������basepath����api����ַ������������������Լ���Ŀ�����ַ�ṹ
        setBasePath("api"); 
        req = RestAssured.given().contentType(ContentType.JSON);
	}
	
	@AfterClass
	public void afterTest (){
	        //����֮��ָ�һЩֵ���趨
	        resetBaseURI();
	        resetBasePath();
	}
	
    //���� base URI
    public static void setBaseURI (){
    	if("80".equals(port)) {
    		RestAssured.baseURI = serverHost;
    	}else {
    		RestAssured.baseURI = serverHost+":"+port;
    	}
        //System.out.println(RestAssured.baseURI);
    }

    //����base path
    public static void setBasePath(String basePath){
        RestAssured.basePath = basePath;
    }

    //ִ������Ժ����� Base URI
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    //ִ������Ժ����� base path
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    //���� JsonPath����
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        //System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }
}
