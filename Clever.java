import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;

import java.io.*;

public class Clever {

	public static void main(String[] args) {
		try {
		    String body = Request.Get("https://api.clever.com/v1.1/sections")
                     .addHeader("Authorization", "Bearer DEMO_TOKEN")
                     .execute().returnContent().asString();
		
			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(body);

			JSONObject structure = (JSONObject) jsonObject.get("paging");
			System.out.println("Total " + structure.get("total"));
			System.out.println("Count " + structure.get("count"));

			JSONArray response = (JSONArray) jsonObject.get("links");

			for(int i = 0; i < response.size(); i ++)
			{
				System.out.println("The " + i + " element of the array: "+response.get(i));
			}

			// System.out.println(body);

		} catch (IOException e) {
		    e.printStackTrace();
		    // Or ask the user for a different filename...
		} catch(ParseException pe){
		    System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
	}
}

// commands to run
// javac -cp \* Clever.java
// java -cp .:\* Clever
// http://examples.javacodegeeks.com/core-java/json/java-json-parser-example/ *** GOD BLESS THIS PERSON ***