import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Clever {

	public static void main(String[] args) {
		try {
		    String body = Request.Get("https://api.clever.com/v1.1/sections")
                     .addHeader("Authorization", "Bearer DEMO_TOKEN")
                     .execute().returnContent().asString();
		
			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(body);

			JSONObject sections = (JSONObject) jsonObject.get("paging");
			String totalSections = sections.get("total").toString();
			String totalStudents = sections.get("count").toString();

			// parse string values
			int numberOfSections = Integer.parseInt(totalSections);
			double numberOfStudents = Double.parseDouble(totalStudents);

			double average = numberOfStudents / numberOfSections;

			System.out.println("Total sections: " + numberOfSections);
			System.out.println("Total students: " + numberOfStudents);

			// average number of students per section
			System.out.println("Average number of students per section: " + average);

		} catch (IOException e) {
		    e.printStackTrace();
		} catch(ParseException pe){
		    System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
	}
}
