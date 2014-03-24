package com.example.ticktaktopple.listener;

import com.example.ticktaktopple.activity.ScoreWidget;
import com.example.ticktaktopple.score.Goal;
import com.example.ticktaktopple.score.Goal.Colour;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ScoreComponentOnClickListener implements OnClickListener{

	private Goal.Colour colour;
	private Goal goal;
	private int amount;
	private ScoreWidget sc;
	
	public ScoreComponentOnClickListener(ScoreWidget s,Goal.Colour c,int amount){
		this.colour = c;
		this.goal = s.getGoal();
		this.amount = amount;
		this.sc = s;
	}
	
	
	@Override
	public void onClick(View v) {
		goal.addObjectsCount(this.colour,amount);
		sc.invalidate();
		
	}

}
