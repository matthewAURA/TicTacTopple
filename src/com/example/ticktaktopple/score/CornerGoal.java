package com.example.ticktaktopple.score;

public class CornerGoal extends Goal {

	
	@Override
	public int getPoints(Colour goal) {
		return this.getObjects(goal)+this.getBonus(goal);
	}
	


}
