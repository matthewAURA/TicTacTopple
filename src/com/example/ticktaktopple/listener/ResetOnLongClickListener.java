package com.example.ticktaktopple.listener;

import com.example.ticktaktopple.activity.ScoreWidget;
import com.example.ticktaktopple.score.Goal;
import com.example.ticktaktopple.score.Goal.Colour;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class ResetOnLongClickListener implements OnLongClickListener{

	private Goal.Colour colour;
	private Goal goal;
	private ScoreWidget sc;
	
	public ResetOnLongClickListener(ScoreWidget s,Goal.Colour c){
		this.colour = c;
		this.goal = s.getGoal();
		this.sc = s;
	}
	
	
	@Override
	public boolean onLongClick(View v) {
		goal.reset(colour);
		sc.invalidate();
		return true;
		
	}

}
