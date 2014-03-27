package com.example.ticktaktopple.score;


public class RampGoal extends Goal {

	private Colour hasGoal;
	
	@Override
	public int getPoints(Colour goal) {
		if (this.hasGoal == goal){
			return 10;
		}else{
			return 0;
		}
	}

	public Colour hasBonus(){
		return hasGoal;
		
	}
	
	public void setColour(Colour c){
		this.hasGoal = c;
	}
	
	public void reset(){
		this.setColour(null);
		super.reset();
	}
	
}
