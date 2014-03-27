package com.example.ticktaktopple.score;

public class ConeGoal extends Goal {

	
	@Override
	public int getPoints(Colour goal) {
		return 3*this.getObjects(goal)+this.getBonus(goal);
	}
	


}
