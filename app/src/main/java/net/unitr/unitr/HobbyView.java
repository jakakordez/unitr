package net.unitr.unitr;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class HobbyView extends LinearLayout{
	public View view;
	public String hobby;
	TextView lblName;
	double mark = 1.0;
	LinearLayout lstStars;
	ImageButton btnRemove;

	public Runnable removeRunnable;

	public HobbyView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		init();
	}

	ArrayList<ImageButton> stars;
	final Drawable yellowStar = getResources().getDrawable(R.drawable.ic_yellow_star);
	final Drawable star = getResources().getDrawable(R.drawable.ic_white_star);
	void init(){
		//inflate(getContext(), R.layout.view_hobby, null);

		//view = LayoutInflater.from(getContext()).inflate(R.layout.view_hobby, null);
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.view_hobby, null);
		lblName = view.findViewById(R.id.lblName);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		lstStars = view.findViewById(R.id.lstStars);
		stars = new ArrayList<>();
		final int starsCount = 5;
		for(int i = 0;  i < starsCount; i++) {
			ImageButton img = new ImageButton(getContext());
			float factor = getResources().getDisplayMetrics().density;
			LayoutParams params = new LayoutParams((int)(40 * factor), (int)(40 * factor));
			params.setMargins(0, 0, 0, 0);
			img.setLayoutParams(params);
			img.setBackgroundResource(0);
			img.setTag(i+1);
			img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					ImageButton v = (ImageButton)view;
					mark = (float)(int)v.getTag()/starsCount;
					updateStars();
				}
			});
			lstStars.addView(img);
			stars.add(img);
		}
		btnRemove = (ImageButton)view.findViewById(R.id.btnRemove);
		btnRemove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if(removeRunnable != null) removeRunnable.run();
			}
		});
		updateStars();
		addView(view);
	}

	public void updateStars(){
		for(int i = 0; i < stars.size(); i++){
			if((float)i/stars.size() < mark){
				stars.get(i).setImageDrawable(yellowStar);
			}
			else{
				stars.get(i).setImageDrawable(star);
			}
		}
	}

	public void setHobby(String hobby){
		this.hobby = hobby;
		lblName.setText(hobby);
	}
}
