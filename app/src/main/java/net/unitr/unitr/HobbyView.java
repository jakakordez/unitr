package net.unitr.unitr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class HobbyView extends LinearLayout{
	public View view;
	public String hobby;
	TextView lblName;
	double mark = 1.0;
	LinearLayout lstStars;

	public HobbyView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		init();
	}

	void init(){
		//inflate(getContext(), R.layout.view_hobby, null);

		//view = LayoutInflater.from(getContext()).inflate(R.layout.view_hobby, null);
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.view_hobby, null);
		lblName = view.findViewById(R.id.lblName);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		lstStars = view.findViewById(R.id.lstStars);
		for(int i = 0;  i < 5; i++) {
			ImageButton img = new ImageButton(getContext());
			float factor = getResources().getDisplayMetrics().density;
			LayoutParams params = new LayoutParams((int)(40 * factor), (int)(40 * factor));
			params.setMargins(0, 0, 0, 0);
			img.setLayoutParams(params);
			img.setBackgroundResource(0);
			img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {

				}
			});
			img.setImageDrawable(getResources().getDrawable(R.drawable.ic_yellow_star));
			lstStars.addView(img);
		}
		addView(view);
	}

	public void setHobby(String hobby){
		this.hobby = hobby;
		lblName.setText(hobby);
	}
}
