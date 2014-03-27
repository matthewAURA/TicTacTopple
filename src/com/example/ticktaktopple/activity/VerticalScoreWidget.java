package com.example.ticktaktopple.activity;

import com.example.ticktaktopple.listener.ScoreInvalidateListener;
import com.example.ticktaktopple.score.Goal.Colour;
import com.example.tiptoptumble.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

public class VerticalScoreWidget extends ScoreWidget {

	public VerticalScoreWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!this.isInEditMode()){
        redText.setText("0");
        blueText.setText("0");
		}
	}

	protected void inflateView(Context context){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){      
        	inflater.inflate(R.layout.score_component_vertical, this);
        }
	}
	
	public void invalidate(){
		super.invalidate();
		redText.setText(Integer.toString(goal.getObjects(Colour.Red)));
		blueText.setText(Integer.toString(goal.getObjects(Colour.Blue)));
		for (ScoreInvalidateListener l:this.scoreInvalid){
			l.update();
		}
	}
	
}
