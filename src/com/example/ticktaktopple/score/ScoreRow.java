package com.example.ticktaktopple.score;

import java.util.List;
import java.util.ArrayList;

import com.example.ticktaktopple.activity.ScoreWidget;
import com.example.ticktaktopple.score.Goal.Colour;

public class ScoreRow {
	
	private List<Goal> components;
	
	public ScoreRow(Goal one,Goal two, Goal three){
		components = new ArrayList<Goal>();
		components.add(one);
		components.add(two);
		components.add(three);
	}
	
	public int calculateBonus(Colour c){
		for (Goal g: components){
			if (g.hasBonus() != c){
				return 0;
			}
		}
		return 15;
	}

}
