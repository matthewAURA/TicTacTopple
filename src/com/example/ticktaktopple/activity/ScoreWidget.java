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

	protected TextView redText;
	protected TextView blueText;
	protected Goal goal;
	protected List<ScoreInvalidateListener> scoreInvalid;
	private boolean selected;
	
	public ScoreWidget(Context context,AttributeSet attrs){
		super(context,attrs);
		
		this.inflateView(context);
        
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
	
	protected void inflateView(Context context){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){      
        	inflater.inflate(R.layout.score_component, this);
        }
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
	
	private void setBackgroundColour(){
		if (goal.hasBonus() != null){
		switch (goal.hasBonus()){
		case Red:
			this.setBackgroundColor(this.getResources().getColor(R.color.red_underlay));
			break;
		case Blue:
			this.setBackgroundColor(this.getResources().getColor(R.color.blue_underlay));
			break;
		}
		}else{
			if(this.selected){
				this.setBackgroundColor(this.getResources().getColor(R.color.backgroundGrey));
			}else{
				this.setBackgroundColor(this.getResources().getColor(R.color.white));
			}
			
		}
	}
	
	public void invalidate(){
		super.invalidate();
		redText.setText(Integer.toString(goal.getObjects(Colour.Red)));
		if(redText.getText().toString().length() < 2){
			redText.setText(" " + Integer.toString(goal.getObjects(Colour.Red)));
		}
		blueText.setText(Integer.toString(goal.getObjects(Colour.Blue)));
		if(blueText.getText().toString().length() < 2){
			blueText.setText(Integer.toString(goal.getObjects(Colour.Blue))+ " ");
		}
		this.setBackgroundColour();
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
	
	public void resetScore(){
		goal.reset();
		this.invalidate();
	}

	public void selected(boolean changeSelected){
		this.selected = changeSelected;
		this.setBackgroundColour();
		this.invalidate();
	}
	
	public void addInvalidateListener(ScoreInvalidateListener l){
		this.scoreInvalid.add(l);
	}
	
	
}
