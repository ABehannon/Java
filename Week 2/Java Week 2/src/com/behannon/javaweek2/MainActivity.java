package com.behannon.javaweek2;

import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	//import linear layout
	LinearLayout ll;

	//linear layout params
	LinearLayout.LayoutParams lp;

	//Image view start
	ImageView titleImage;
	ImageView catImage;

	//Text View start
	TextView instructionText;
	TextView testText;
	
	//set up random numbers
	Random rand = new Random();

	//String start
	//String instuctionString;

	//int start
	int randomNumber1;
	int randomNumber2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		//calls layout setup function
		mainLayoutSetup();

		super.onCreate(savedInstanceState);

	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void mainLayoutSetup(){
    	
    	//set up linear layout and params
    			ll = new LinearLayout(this);
    			ll.setOrientation(LinearLayout.VERTICAL);
    			
    			lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    			ll.setLayoutParams(lp); 
    			
    			//new text view variable
    			instructionText = new TextView(this);
    			instructionText.setText("");
    			
    			testText = new TextView(this);
    			testText.setText("");
    			
    			//Button setup
    			Button b = new Button(this);
    			b.setText("PRESS TO START");
    			
    			//On click handler
    			b.setOnClickListener(new View.OnClickListener() {
    				public void onClick(View v) {
    					
    					//Select random numbers from 0-9 to choose names from JSON.
    					randomNumber1 = rand.nextInt(10); 
    					randomNumber2 = rand.nextInt(10);

    					testText.setText(randomNumber1 + " " + randomNumber2);
    				}
    			});
    			
    			//add views
    			ll.addView(instructionText);
    			ll.addView(testText);
    			ll.addView(b);
    			setContentView(ll);
    }
    
}
