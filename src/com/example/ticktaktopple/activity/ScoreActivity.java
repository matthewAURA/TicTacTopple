package com.example.ticktaktopple.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.ticktaktopple.listener.ScoreInvalidateListener;
import com.example.ticktaktopple.score.ConeGoal;
import com.example.ticktaktopple.score.CornerGoal;
import com.example.ticktaktopple.score.ScoreController;
import com.example.ticktaktopple.score.ScoreRow;
import com.example.ticktaktopple.score.Goal.Colour;
import com.example.tiptoptumble.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ScoreActivity extends Activity implements ScoreInvalidateListener{
	
	private boolean simpleMode = false;
	private List<ScoreWidget> ScoreWidgets;
	private ScoreController scoreControl;

	private ScoreWidget goal1;
	private ScoreWidget goal2;
	private ScoreWidget goal3;
	private ScoreWidget goal4;
	private ScoreWidget goal5;
	private ScoreWidget goal6;
	private ScoreWidget goal7;
	private ScoreWidget goal8;
	
	private RampGoalWidget rampGoal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
		
		setContentView(R.layout.activity_score);
		ScoreWidgets = new ArrayList<ScoreWidget>();
		
		goal1 = (ScoreWidget) this.findViewById(R.id.scoreComponent1);
		goal2 = (ScoreWidget) this.findViewById(R.id.scoreComponent2);
		goal3 = (ScoreWidget) this.findViewById(R.id.scoreComponent3);
		goal4 = (VerticalScoreWidget) this.findViewById(R.id.scoreComponent4);
		goal5 = (VerticalScoreWidget) this.findViewById(R.id.scoreComponent5);
		goal6 = (ScoreWidget) this.findViewById(R.id.scoreComponent6);
		goal7 = (ScoreWidget) this.findViewById(R.id.scoreComponent7);
		goal8 = (ScoreWidget) this.findViewById(R.id.scoreComponent8);
		
		rampGoal = (RampGoalWidget) this.findViewById(R.id.rampGoalWidget1);
		rampGoal.setInvlidateListener(this);
		//Add ScoreWidgets to List
		ScoreWidgets.add(goal1);
		ScoreWidgets.add(goal2);
		ScoreWidgets.add(goal3);
		ScoreWidgets.add(goal4);
		ScoreWidgets.add(goal5);
		ScoreWidgets.add(goal6);
		ScoreWidgets.add(goal7);
		ScoreWidgets.add(goal8);
		
		goal1.setGoalType(new CornerGoal());
		goal3.setGoalType(new CornerGoal());
		goal6.setGoalType(new CornerGoal());
		goal8.setGoalType(new CornerGoal());
		
		goal2.setGoalType(new ConeGoal());
		goal4.setGoalType(new ConeGoal());
		goal5.setGoalType(new ConeGoal());
		goal7.setGoalType(new ConeGoal());
		
		this.scoreControl = (ScoreController) this.findViewById(R.id.scoreContoller1);
		
		for (ScoreWidget score: ScoreWidgets){
			score.addInvalidateListener(this);
	    	this.scoreControl.addComponent(score);
	    	score.updateListener(this.simpleMode,this.scoreControl );
	    }

	}


	
	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.scoreMode:
	            if (item.isChecked()){
	            	item.setChecked(false);
	            	simpleMode = false;
	            }
	            else {
	            	item.setChecked(true);
	            	simpleMode = true;
	            }
	            for (ScoreWidget score: ScoreWidgets){
	    	    	score.updateListener(this.simpleMode,this.scoreControl );
	    	    }
	        case R.id.resetScore:
	        	for (ScoreWidget s:ScoreWidgets){
	        		s.resetScore();
	        	}
	        	rampGoal.resetScore();
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	    
	}

	private void calculateScore(){
		ScoreRow row1 = new ScoreRow(goal1.getGoal(),goal2.getGoal(),goal3.getGoal());
		ScoreRow row2 = new ScoreRow(goal1.getGoal(),goal4.getGoal(),goal6.getGoal());
		ScoreRow row3 = new ScoreRow(goal3.getGoal(),goal5.getGoal(),goal8.getGoal());
		ScoreRow row4 = new ScoreRow(goal6.getGoal(),goal7.getGoal(),goal8.getGoal());
		
		//Do Rows through the middle
		ScoreRow row5 = new ScoreRow(goal1.getGoal(),rampGoal.getGoal(),goal8.getGoal());
		ScoreRow row6 = new ScoreRow(goal2.getGoal(),rampGoal.getGoal(),goal7.getGoal());
		ScoreRow row7 = new ScoreRow(goal3.getGoal(),rampGoal.getGoal(),goal6.getGoal());
		ScoreRow row8 = new ScoreRow(goal4.getGoal(),rampGoal.getGoal(),goal5.getGoal());
		
		List<ScoreRow> rows = new ArrayList<ScoreRow>();
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		rows.add(row4);
		rows.add(row5);
		rows.add(row6);
		rows.add(row7);
		rows.add(row8);
		
		
		
		//Do Red
		int redScore = 0;
		for (ScoreRow r:rows){
			redScore += r.calculateBonus(Colour.Red);
		}
		for (ScoreWidget s:this.ScoreWidgets){
			redScore += s.getGoal().getPoints(Colour.Red);
		}
		redScore += rampGoal.getGoal().getPoints(Colour.Red);
		
		//Do Blue
		int blueScore = 0;
		for (ScoreRow r:rows){
			blueScore += r.calculateBonus(Colour.Blue);
		}
		for (ScoreWidget s:this.ScoreWidgets){
			blueScore  += s.getGoal().getPoints(Colour.Blue);
		}
		blueScore += rampGoal.getGoal().getPoints(Colour.Blue);
		
		TextView redScoreText = (TextView)this.findViewById(R.id.redScore);
		TextView blueScoreText = (TextView)this.findViewById(R.id.blueScore);
		
		redScoreText.setText(Integer.toString(redScore));
		blueScoreText.setText(Integer.toString(blueScore));
		redScoreText.invalidate();
		blueScoreText.invalidate();
	}



	@Override
	public void update() {
		this.calculateScore();	
	}
	

}
