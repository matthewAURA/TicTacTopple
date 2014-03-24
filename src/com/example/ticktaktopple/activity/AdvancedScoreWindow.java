package com.example.ticktaktopple.activity;

import com.example.tiptoptumble.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class AdvancedScoreWindow extends View{

	public AdvancedScoreWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){      
        	inflater.inflate(R.layout.score_controls, null);
        }

	}
	
}
