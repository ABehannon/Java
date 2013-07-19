package com.behannon.javaweek2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.behannon.lib.JSON;

public class MainActivity extends Activity {
	
	//setup
	String[] names = {"catName0", "catName1", "catName2", "catName3", "catName4", "catName5", "catName6", "catName7", "catName8", "catName9"};
	
	//import linear layout
	LinearLayout ll;
	LinearLayout ll2;

	//linear layout params
	LinearLayout.LayoutParams lp;
	
	//Image view start
	ImageView titleImage;
	ImageView catImage;

	//Text View start
	TextView instructionText;
	TextView printoutText;

	//edit text start
	EditText nameAmountField;
	
	//int start
	int nameAmountRequested;
	
	//float start
	float tempNum;
	
	//string start
	String rec;
	String tempNames;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		initialLayoutSetup();

		super.onCreate(savedInstanceState);

	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //-----------------------------------
    //enum Usage
    //-----------------------------------
    public enum nameType {
        
    }
    
    //-----------------------------------
    //initial layout
    //-----------------------------------
    private void initialLayoutSetup() {
    	
    	//-----------------------------------
    	//set up linear layout and params
    	//-----------------------------------
    	ll = new LinearLayout(this);
    	ll.setOrientation(LinearLayout.VERTICAL);
    			
    	lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

    	ll.setLayoutParams(lp);
    	ll.setGravity(Gravity.CENTER_HORIZONTAL);
    	
    	//-----------------------------------
    	//edit text variable
    	//-----------------------------------
    	nameAmountField = new EditText(this);
    	nameAmountField.setHint("ENTER NUMBER HERE");
    	
    	//-----------------------------------
    	//new text view variable
    	//-----------------------------------
    	instructionText = new TextView(this);
    	instructionText.setText("ENTER ANY NUMBER FROM 1 TO 10 TO VIEW CAT NAMES");
    	instructionText.setGravity(Gravity.CENTER_HORIZONTAL);
    	
    	printoutText = new TextView(this);
    	printoutText.setGravity(Gravity.CENTER_HORIZONTAL);
    		
    	//-----------------------------------
    	//Button setup
    	//-----------------------------------    	
    	Button b = new Button(this);
    	b.setText("PRESS TO SHOW NAMES");
    	
    	//-----------------------------------
		//On click handler
    	//-----------------------------------
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//turns text edit into float value
				String rec = nameAmountField.getText().toString();
				boolean recCheck;

				//check if text box is a number
				try {
					nameAmountRequested = Integer.parseInt(rec);
					recCheck = true;

				} catch (NumberFormatException e){
					recCheck = false;
				}
				

				//statements depending on what number the box is
				if(nameAmountRequested == 0 && recCheck == true){
					printoutText.setText("You cannot use zero. Please try again.");
				} else if (nameAmountRequested < 0 && recCheck == true){
					printoutText.setText("Number is too low. Please try again.");
				}else if (nameAmountRequested > 10 && recCheck == true){
					printoutText.setText("Number is too high. Please try again.");
				} else if (nameAmountRequested > 0 && nameAmountRequested <= 10 && recCheck == true){
						
					int tempInt = Math.round(nameAmountRequested) - 1;
					String s = new Integer(tempInt).toString();
					String selected = "catName" + s;
					printoutText.setText(JSON.readJSON(selected));
					
				}else{
					printoutText.setText("Please enter a valid number.");
				}
			}
		});
    	
    	//-----------------------------------
    	//add views
    	//-----------------------------------
    	setContentView(ll);
    	ll.addView(instructionText); 
    	ll.addView(nameAmountField);
    	ll.addView(b);
    	ll.addView(printoutText);
    }
}


