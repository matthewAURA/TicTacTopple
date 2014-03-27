package com.example.ticktaktopple.activity;

import com.example.ticktaktopple.listener.ScoreInvalidateListener;
import com.example.ticktaktopple.score.Goal;
import com.example.ticktaktopple.score.RampGoal;
import com.example.ticktaktopple.score.Goal.Colour;
import com.example.tiptoptumble.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RampGoalWidget extends RelativeLayout {

	private ImageView goalImage;
	private RampGoal goal;
	private ScoreInvalidateListener invalidate;
	
	public RampGoalWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    if(inflater != null){      
	    	inflater.inflate(R.layout.ramp_goal, this);
	    }
	    
	    this.goal = new RampGoal();
	    
	    //Set up listeners
	    goalImage = (ImageView)this.findViewById(R.id.goalImage);
	    goalImage.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if (goal.hasBonus() == null){
					goal.setColour(Colour.Red);
				}else if (goal.hasBonus() == Colour.Red){
					goal.setColour(Colour.Blue);
				}else if(goal.hasBonus() == Colour.Blue){
					goal.setColour(null);
				}
				colourGoal();

			}
	    });
	}
	
	private void colourGoal(){
		if (goal.hasBonus() == Colour.Red){
			goalImage.setColorFilter(Color.parseColor("#FFFF8585"), PorterDuff.Mode.MULTIPLY );
		}else if (goal.hasBonus() == Colour.Blue){
			goalImage.setColorFilter(Color.parseColor("#FF8585FF"), PorterDuff.Mode.MULTIPLY );
		}else if(goal.hasBonus() == null){
			goalImage.setColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.MULTIPLY );
		}
		//Update Image and invalidate here
		if (invalidate != null){
			invalidate.update();
		}
	}
	
	public void setInvlidateListener(ScoreInvalidateListener s){
		this.invalidate = s;
	}
	
	public void resetScore(){
		goal.reset();
		colourGoal();
		this.invalidate();
	}
	
	public Goal getGoal(){
		return this.goal;
	}
}
