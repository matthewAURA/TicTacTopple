package com.example.ticktaktopple.score;

public class ConeGoal extends Goal {

	private Colour lastColour;
	
	public void addObjectsCount(Colour colour,int objects){
		super.addObjectsCount(colour, objects);
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
		this.lastColour = null;
		super.reset();
	}
	
	


}
