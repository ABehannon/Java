//Alex Behannon
//Java Week 3
//07-26-2013

package com.behannon.wordsearch;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//import linear layout
	LinearLayout ll;
	LinearLayout ll2;

	//linear layout params
	LinearLayout.LayoutParams lp;
		
	//TextView setup
	TextView instructionText;
	TextView resultsText;
	
	//EditText setup
	EditText searchBox;
	
	//Button setup
	Button searchButton;
	Button saveButton;
	Button loadButton;
	
	Context _context;
	Boolean _connected;
	Boolean internetConnection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
						
		initialLayoutSetup();
		
	}

	//initial layout of UI
		private void initialLayoutSetup() {
			
			_context = this;
			
			//Linear Layout setup for UI
	    	ll = new LinearLayout(this);
	    	ll.setOrientation(LinearLayout.VERTICAL);
	    			
	    	lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

	    	ll.setLayoutParams(lp);
	    	ll.setGravity(Gravity.CENTER_HORIZONTAL);
	    	
	    	//TextView setup for app
	    	instructionText = new TextView(this);
	    	instructionText.setText("To retreive a definition, enter a word below.\n Note that the amount of API calling is limited. \n Too many requests will lock out the API key.");
	    	instructionText.setGravity(Gravity.CENTER_HORIZONTAL);
	    	
	    	resultsText = new TextView(this);
	    	resultsText.setText("");
	    	resultsText.setGravity(Gravity.CENTER_HORIZONTAL);
	    	resultsText.setPadding(10,100,10,100);
	    	
	    	//EditText setup for search
	    	searchBox = new EditText(this);
	    	searchBox.setHint("Enter Word Here");
	    	searchBox.setGravity(Gravity.CENTER_HORIZONTAL);
	    	
	    	//Buttons for app
	    	searchButton = new Button(this);
	    	searchButton.setText("Search");
	    	
	    	saveButton = new Button(this);
	    	saveButton.setText("Save Definition");
	    	
	    	loadButton = new Button(this);
	    	loadButton.setText("Load Definition");
	        
	    	//On click handler for search
	    	searchButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				
					testConnection();
					
					if(internetConnection = true){
						String keyword = searchBox.getText().toString();
						getDefinition(keyword);
					}else {
						Toast.makeText(getApplicationContext(), "You are not currently connected to the internet. Searching will not work, but you may still be able to load your last saved definition.", Toast.LENGTH_LONG).show();	
					}
				}
			});
	    	
	    	//On click handler for save
	    	saveButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					try {
						saveDefinition();
						System.out.println("SAVED");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
	    	
	    	//On click handler for load
	    	loadButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					loadDefinition();
				}
			});
	    	
	    	//Add views
	    	setContentView(ll);
	    	ll.addView(instructionText); 
	    	ll.addView(searchBox);
	    	ll.addView(resultsText);
	    	ll.addView(searchButton);
	    	ll.addView(saveButton);
	    	ll.addView(loadButton);
		}
		
		//Called to test internet when button pressed or app started
		private void testConnection() {
			
			_connected = Web.getConnectionStatus(_context);
	        if (_connected) {
	            internetConnection = true;
	            System.out.println("ONLINE");
	            
	        } else {
	        	Toast.makeText(getApplicationContext(), "You are not currently connected to the internet. Searching will not work, but you may still be able to load your last saved definition.", Toast.LENGTH_LONG).show();
	        	internetConnection = false;
	        	System.out.println("OFFLINE");
	        }
		}
		
		//Save current definition to cache
		private void saveDefinition() throws IOException{
			
			//init strings
			String text = resultsText.getText().toString();
			String text2 = searchBox.getText().toString();
			String def = text2 + ": " + resultsText;
			String error1 = "Definition not found.";
			String error2 = "";
			String FILENAME = "definition_file";
			
			if (text != error1 && text != error2){

				    //Write string
				    DataOutputStream out;
					try {
						out = new DataOutputStream(openFileOutput(FILENAME, Context.MODE_PRIVATE));
						
						out.writeUTF(def);
					    out.close();
					    System.out.println("SAVE WORKED");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("SAVE FAILED");
					}
				} else {
				Toast.makeText(getApplicationContext(), "Please complete a successful search before attempting to save.", Toast.LENGTH_LONG).show();
			}
		}
		
		//Load cached definition
		private void loadDefinition(){
		
			String FILENAME = "def_file";
			try{
			    // Read data
			    DataInputStream in = new DataInputStream(openFileInput(FILENAME));
			    try {
			        for (;;) {
			          Log.i("Data Input Sample", in.readUTF());
			        }
			    } catch (EOFException e) {
			        Log.i("Data Input Sample", "End of file reached");
			    }
			    in.close();
			} catch (IOException e) {
			    Log.i("Data Input Sample", "I/O Error");
			}
			
		}
		
		
		
		//Get the definition of the word requested
		@SuppressWarnings("unused")
		private void getDefinition(String keyword){
			
			//Create init variables and fix URL info
			String URL = "http://api.wordreference.com/0.8/e105d/json/enfr/";
			String baseURL = keyword.replaceAll("\\s", "+");
			String moddedURL = URL + baseURL;
			String encodeURL;
			
			try{
				encodeURL = URLEncoder.encode(baseURL, "UTF-8");
			}catch (Exception e){
				Log.e("Encoding Failure", "Bad URL");
				encodeURL = "";
			}
			
			URL finalURL;
			try{
				finalURL = new URL(moddedURL);
				definitionRequest newRequest = new definitionRequest();
				newRequest.execute(finalURL);
				System.out.println("Modded URL: "+ moddedURL);
			}catch (MalformedURLException e){
				Log.e("Bad URL", "Malformed URL");
				finalURL = null;
			}
		}
		
		//Background tasks going on when using tool
		private class definitionRequest extends AsyncTask<URL, Void, String> {

			@Override
			protected String doInBackground(URL... urls) {
				String response = "";
				for (URL url:urls) {
					response = Web.getURLStringResponse(url);
					
				}
				return response;
			}
			
			@Override
			protected void onPostExecute(String result){
				
				//Get JSON info
				try{
					JSONObject json = new JSONObject(result);
					JSONObject results = json.getJSONObject("term0").getJSONObject("PrincipalTranslations").getJSONObject("0").getJSONObject("OriginalTerm");
					String definition = results.getString("sense");
					resultsText.setText(definition);
					System.out.println("JSON SUCCESSFUL");
				}catch (JSONException e){
					Log.e("JSON", "JSON OBJECT EXCEPTION");
					resultsText.setText("Definition not found.");
				}
				
			}
			
		}

}
