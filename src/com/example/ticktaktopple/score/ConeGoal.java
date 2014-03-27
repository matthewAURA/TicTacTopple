package com.example.ticktaktopple.score;

public class ConeGoal extends Goal {

	private Colour lastColour;
	
	public void addObjectsCount(Colour colour,int objects){
		super.addObjectsCount(colour, objects);
		this.lastColour = colour;
	}
	
	@Override
	public int getPoints(Colour goal) {
		return 3*this.getObjects(goal)+this.getBonus(goal);
	}
	
	public Colour hasBonus(){
		return this.lastColour;
	}


}
