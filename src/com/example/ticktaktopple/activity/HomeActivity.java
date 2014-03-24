package com.example.ticktaktopple.activity;


import com.example.tiptoptumble.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;



public class HomeActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    //Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		this.setContentView(R.layout.activity_home);
		

		
		Button rulesButton = (Button)this.findViewById(R.id.rulesButton);
		Button calcButton = (Button)this.findViewById(R.id.calcButton);
		
		rulesButton.setText("Rules");
		calcButton.setText("Score Calculator");
		
		calcButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), ScoreActivity.class);
				startActivity(intent);
			}
			
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}

	
}
