package com.example.ticktaktopple.score;

import java.util.HashMap;

import android.util.Log;

public abstract class Goal {

	public static enum Colour implements Comparable<Colour> {
		Red,Blue;
	}


	private HashMap<Colour,Integer> objects;
	
	public Goal(){
		this.objects = new HashMap<Colour,Integer>();
		objects.put(Colour.Red,0);
		objects.put(Colour.Blue,0);
	}
	
	public abstract int getPoints(Goal.Colour goal);
	
	public int getObjects(Colour colour){
		return this.objects.get(colour);
	}
	
	public void reset(){
		this.objects = new HashMap<Colour,Integer>();
		objects.put(Colour.Red,0);
		objects.put(Colour.Blue,0);
	}
	
	public Colour hasBonus(){
		if (this.getObjects(Colour.Red) > this.getObjects(Colour.Blue)){
			return Colour.Red;
		}else if (this.getObjects(Colour.Red) < this.getObjects(Colour.Blue)){
			return Colour.Blue;
		}else{
			return null;
		}
	}
	
	public void reset(Colour colour){
		objects.put(colour, 0);
	}
	
	protected int getBonus(Colour colour){
		if (colour == this.hasBonus()){
			return 5;
		}else{
			return 0;
		}
	}
	
	public void addObjectsCount(Colour colour,int objects){
		
		int newInt = this.objects.get(colour);
		if (newInt+objects >= 0){
			this.objects.put(colour, newInt+objects);
		}
	}
	
	
}
