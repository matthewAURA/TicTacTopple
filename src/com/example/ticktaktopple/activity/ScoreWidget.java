package com.example.ticktaktopple.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.ticktaktopple.listener.ResetOnLongClickListener;
import com.example.ticktaktopple.listener.ScoreComponentOnClickListener;
import com.example.ticktaktopple.listener.ScoreInvalidateListener;
import com.example.ticktaktopple.score.Goal;
import com.example.ticktaktopple.score.ScoreController;
import com.example.ticktaktopple.score.Goal.Colour;
import com.example.tiptoptumble.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreWidget extends LinearLayout{

	private TextView redText;
	private TextView blueText;
	private Goal goal;
	private List<ScoreInvalidateListener> scoreInvalid;
	
	public ScoreWidget(Context context,AttributeSet attrs){
		super(context,attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){      
        	inflater.inflate(R.layout.score_component, this);
        }
        
        scoreInvalid = new ArrayList<ScoreInvalidateListener>();
        
        redText = (TextView) this.findViewById(R.id.redPoints);
        blueText= (TextView) this.findViewById(R.id.bluePoints);
        redText.setText("0");
        blueText.setText("0");
        redText.setTextSize(this.getResources().getDimension(R.dimen.scoreTextSize));
        blueText.setTextSize(this.getResources().getDimension(R.dimen.scoreTextSize));
        this.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Log.d("ScoreComponent","OnClick");
				
			}
        	
        });
       
	}
	
	public void updateListener(boolean simpleMode, final ScoreController control){
		final ScoreWidget self = this;
		
		if (simpleMode){
		 redText.setOnClickListener(new ScoreComponentOnClickListener(this,Colour.Red,1));
		 blueText.setOnClickListener(new ScoreComponentOnClickListener(this,Colour.Blue,1));
		 redText.setOnLongClickListener(new ResetOnLongClickListener(this,Goal.Colour.Red));
		 blueText.setOnLongClickListener(new ResetOnLongClickListener(this,Goal.Colour.Blue));
		 this.setOnClickListener(null);
		 this.selected(false);
		}else{
			redText.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					control.onClick(self);
				}
				
				
			});		
			blueText.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					control.onClick(self);
				}
				
				
			});
			this.setOnClickListener(control);
		}
		redText.setClickable(simpleMode);
		blueText.setClickable(simpleMode);
		redText.setFocusable(simpleMode);
		blueText.setFocusable(simpleMode);
		
	}
	
	public void invalidate(){
		super.invalidate();
		redText.setText(Integer.toString(goal.getObjects(Colour.Red)));
		blueText.setText(Integer.toString(goal.getObjects(Colour.Blue)));
		for (ScoreInvalidateListener l:this.scoreInvalid){
			l.update();
		}
	}

	
	public void setGoalType(Goal g){
		this.goal = g;
	}
	
	public Goal getGoal(){
		return this.goal;
	}

	public void selected(boolean selected){
		if(selected){
			this.setBackgroundColor(this.getResources().getColor(R.color.backgroundGrey));
		}else{
			this.setBackgroundColor(this.getResources().getColor(R.color.white));
		}
		this.invalidate();
	}
	
	public void addInvalidateListener(ScoreInvalidateListener l){
		this.scoreInvalid.add(l);
	}
	
	
}