import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

// import org.json.simple.JSONObject;
// import org.json.simple.JSONArray;
// import org.json.simple.parser.ParseException;
// import org.json.simple.parser.JSONParser;

import java.io.*;

public class Clever {

	public static void main(String[] args) {
		try {
		    String body = Request.Get("https://api.clever.com/v1.1/districts/4fd43cc56d11340000000005/students")
                     .addHeader("Authorization", "Bearer DEMO_TOKEN")
                     .execute().returnContent().asString();
		
			//System.out.println(body);
			//String jsonEX = {"balance": 1000.21, "num":100, "is_vip":true, "name":"foo"}

		} catch (IOException e) {
		    e.printStackTrace();
		    // Or ask the user for a different filename...
		}
	}
}

// commands to run
// javac -cp \* Clever.java
// java -cp .:\* Clever