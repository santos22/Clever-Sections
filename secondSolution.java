import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class secondSolution {

	public static void main(String[] args) {
		try {
		    String body = Request.Get("https://api.clever.com/v1.1/sections")
                     .addHeader("Authorization", "Bearer DEMO_TOKEN")
                     .execute().returnContent().asString();
		
			JSONParser parser = new JSONParser();

			JSONObject jsonObject = (JSONObject) parser.parse(body);

			double totalStudents = 0;
			int totalSections;

			// messing with the array
			JSONArray response = (JSONArray) jsonObject.get("data");

			// iterate through the Data JSONArray
			for(int i = 0; i < response.size(); i ++)
			{
				JSONObject responseData = (JSONObject) response.get(i);
				JSONObject data = (JSONObject) responseData.get("data");

				// get the Students JSONArray within the Data JSONArray
				JSONArray students = (JSONArray) data.get("students");

				// increment total number of students by the size of each students array
				totalStudents += students.size();
			}

			// counted 6 sections in the response
			// e.g. "Group Guidance - 101 - B. Greene (Section 1)" is one section...
			totalSections = 6;
			System.out.println("Total sections: " + totalSections);
			System.out.println("Total students: " + totalStudents);

			// average number of students per section
			System.out.println("Average: " + totalStudents/totalSections);

		} catch (IOException e) {
		    e.printStackTrace();
		} catch(ParseException pe){
		    System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		}
	}
}
