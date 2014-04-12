package com.example.ticktaktopple.score;

import android.util.Log;

public class ConeGoal extends Goal {

	private Colour lastColour;
	
	public void addObjectsCount(Colour colour,int objects){
		super.addObjectsCount(colour, objects);
		Log.d("ConeGoal Add","Added Objects: " + Integer.toString(objects));
		
		if (this.getObjects(colour) > 0){
			this.lastColour = colour;
		}else{
			this.lastColour = null;
		}
		
	}
	
	@Override
	public int getPoints(Colour goal) {
		return 3*this.getObjects(goal)+this.getBonus(goal);
	}
	
	public Colour hasBonus(){
		return this.lastColour;
	}
	
	public void reset(){
		super.reset();
		this.lastColour = null;
	}
	
	public void reset(Colour c){
		super.reset(c);
		this.lastColour = null;
	}
	


}
