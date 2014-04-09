package com.example.ticktaktopple.activity;


import com.example.tiptoptumble.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;



public class HomeActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
		
		rulesButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Uri path = Uri.parse("android.resource://com.exmaple.tictaktopple/raw/tic_tac_topple_release1.pdf");
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				intent.setDataAndType(path, "application/pdf");
				try {
				    startActivity(intent);
				    Log.e("IR", "No exception");
				} 
				catch (ActivityNotFoundException e) {
				    Log.e("IR", "error: " + e.getMessage());
				    Toast.makeText(HomeActivity.this, 
				        "No Application Available to View PDF", 
				        Toast.LENGTH_SHORT).show();
				}
			}
			
			
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}

	
}
