//Alex Behannon
//Java Week 2
//07.18.2013

package com.behannon.lib;

import org.json.JSONException;
import org.json.JSONObject;
import com.behannon.lib.Enum;

//create json object
public class JSON {
	
	//creates main json object
	public static JSONObject buildJSON() {

		JSONObject mainObject = new JSONObject();
		try {
			JSONObject dataObject = new JSONObject();

			// Create object
			for (Enum items : Enum.values()) {
				JSONObject tempObject = new JSONObject();

				tempObject.put("nameCount", items.setNameCount());
				tempObject.put("firstName", items.setFirstName());
				tempObject.put("lastName", items.setLastName());

				dataObject.put(items.name().toString(), tempObject);
			}
			mainObject.put("data", dataObject);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mainObject;
	}
	
	//reads the json from the object
	public static String readJSON(String selected){
		String result, firstName, lastName;

		JSONObject object = buildJSON();

		try {
			firstName = object.getJSONObject("data").getJSONObject(selected).getString("firstName");
			lastName = object.getJSONObject("data").getJSONObject(selected).getString("lastName");

			result = "First Name: "+ firstName + "\r\n" +"Last Name: " + lastName + "\r\n";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
		}		
		
		return result;
	}
}