package com.behannon.javaweek1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

	//import linear layout
	LinearLayout ll;
	
	//linear layout params
	LinearLayout.LayoutParams lp;
	
	//Edit text start
	EditText et;
	EditText et2;
	
	//Text View start
	TextView tv;
	TextView tv2;
	TextView tv3;
	
	//String start
	String st;
	
	//int start
	int count1;
	int count2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//calls layout setup function
		linearLayoutSetup();
		
		super.onCreate(savedInstanceState);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void linearLayoutSetup(){
	
		//set up linear layout and params
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		
		//new text view variable
		tv = new TextView(this);
		tv.setText(R.string.intro_text);
		
		tv2 = new TextView(this);
		tv2.setText("");
		
		tv3 = new TextView(this);
		tv3.setText("");
		
		//edit text setup
		et = new EditText(this);
		et.setHint("Type first number here.");
		
		et2 = new EditText(this);
		et2.setHint("Type second number here.");
		
		//Button setup
		Button b = new Button(this);
		b.setText("OK");
		
		//On click handler
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String rec = et.getText().toString();
				String rec2 = et2.getText().toString();
				boolean recCheck;
				
				//check if text box is a number
				try {
					count1 = Integer.parseInt(rec);
					count2 = Integer.parseInt(rec2);
					recCheck = true;
					
				} catch (NumberFormatException e){
					recCheck = false;
				}
				
				
				
				//statements depending on what number the box is
				if(count1 == 0 || count2 == 0 && recCheck == true){
					tv2.setText("You cannot use zero. Please try again.");
					tv3.setText("");
				} else if (count1 < 0 || count2 < 0  && recCheck == true){
					tv2.setText("You cannot use a negative number. Please try again.");
					tv3.setText("");
				} else if (count1 > 0 || count2 > 0 && recCheck == true){
					tv2.setText("Counting difference..");
					loopCounter();
				}else{
					tv2.setText("Please enter a valid number.");
					tv3.setText("");
				}
			}
		});
		
		//add views
		ll.addView(tv);
		ll.addView(et);
		ll.addView(et2);
		ll.addView(b);
		ll.addView(tv2);
		ll.addView(tv3);
		setContentView(ll);
		
	}
	
	private void loopCounter(){
		//Loop to count down (could have used timer, but oh well)
		if(count1 > count2){
			int c1 = count1 - count2;
			int c2 = 0;
			for (int i = c2; i <= c1; i++){
				tv3.setText("Difference: " + i);
			}
		}else if(count2 > count1){
			int c1 = count2 - count1;
			int c2 = 0;
			for (int i = c2; i <= c1; i++){
				tv3.setText("Difference: " + i);
			}
		}else{
			tv3.setText("Numbers are equal.");
		}
	
	}
	
}
