package com.example.ticktaktopple.score;

import java.util.ArrayList;
import java.util.List;

import com.example.ticktaktopple.activity.ScoreWidget;
import com.example.ticktaktopple.listener.ScoreComponentOnClickListener;
import com.example.tiptoptumble.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

public class ScoreController extends LinearLayout implements OnClickListener {

	private List<ScoreWidget> components;
	private ScoreWidget focused;
	
	
	public ScoreController(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){      
        	inflater.inflate(R.layout.score_controls, this);
        }
        components = new ArrayList<ScoreWidget>();
	}
	
	public void addComponent(ScoreWidget s){
		components.add(s);
	}

	
	private void setFocus(){
		
		Button redPlus = (Button)this.findViewById(R.id.redPlus);
		Button redMinus = (Button)this.findViewById(R.id.redMinus);
		Button bluePlus = (Button)this.findViewById(R.id.bluePlus);
		Button blueMinus = (Button)this.findViewById(R.id.blueMinus);
		
		redPlus.setOnClickListener(new ScoreComponentOnClickListener(this.focused,Goal.Colour.Red,1));
		bluePlus.setOnClickListener(new ScoreComponentOnClickListener(this.focused,Goal.Colour.Blue,1));
		redMinus.setOnClickListener(new ScoreComponentOnClickListener(this.focused,Goal.Colour.Red,-1));
		blueMinus.setOnClickListener(new ScoreComponentOnClickListener(this.focused,Goal.Colour.Blue,-1));
		
	}

	@Override
	public void onClick(View v) {
		ScoreWidget clicked = (ScoreWidget)v;
		Log.d("ScoreController",v.toString());
		if (components.contains(clicked)){
			if(this.focused != null){
				this.focused.selected(false);
			}
			this.focused = clicked;
			this.setFocus();
			this.focused.selected(true);
		}
		
	}
	
	
}
