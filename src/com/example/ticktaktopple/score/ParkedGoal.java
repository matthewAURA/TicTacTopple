package com.example.ticktaktopple.score;

import android.util.Log;

public class ParkedGoal extends Goal {

	public void addObjectsCount(Colour colour,int objects){
		super.addObjectsCount(colour, objects);
		Log.d("ConeGoal Add","Added Objects: " + Integer.toString(objects));

		
	}
	
	@Override
	public int getPoints(Colour goal) {
		return 10*this.getObjects(goal);
	}

}
