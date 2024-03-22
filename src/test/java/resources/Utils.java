package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public static String keyvalue;
	JsonPath js;
	
	public RequestSpecification RequestSpecification  () throws IOException
	{	
		if(req==null)
		{
		PrintStream log= new PrintStream(new FileOutputStream("logging.txt"));
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseurl")).
				addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		 		 return req;
		}
		return req;
	}
		
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties");
		prop.load(fs);
		//System.out.println(System.getProperty("user.dir")+"\\src\\test\\java\\resources");
		
		String url= prop.getProperty(key);
		return url;
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String resString= response.asString();
		 js = new JsonPath(resString);
		keyvalue = js.get(key);
		System.out.println(key + " is " + keyvalue);
		return keyvalue;
	}

}
