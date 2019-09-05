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
    //测试用例中断言代码能用上这里的 testUtils对象
  	public static TestUtils testUtils = new TestUtils();
  	//初始化请求对象
  	public RequestSpecification req;
  	
	
	static {
		// 用于加载properties文件
		// 注意这里不需要文件扩展名.properties
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
		setBaseURI(); //设置Base URI
		//设置Base Path，我这里是api（https://reqres.in/接口地址都是api开头，
		//所以这里basepath设置api这个字符串），看看具体你自己项目请求地址结构
        setBasePath("api"); 
        req = RestAssured.given().contentType(ContentType.JSON);
	}
	
	@AfterClass
	public void afterTest (){
	        //测试之后恢复一些值的设定
	        resetBaseURI();
	        resetBasePath();
	}
	
    //设置 base URI
    public static void setBaseURI (){
    	if("80".equals(port)) {
    		RestAssured.baseURI = serverHost;
    	}else {
    		RestAssured.baseURI = serverHost+":"+port;
    	}
        //System.out.println(RestAssured.baseURI);
    }

    //设置base path
    public static void setBasePath(String basePath){
        RestAssured.basePath = basePath;
    }

    //执行完测试后重置 Base URI
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    //执行完测试后重置 base path
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    //返回 JsonPath对象
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        //System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }
}
