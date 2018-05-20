package net.unitr.unitr;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.unitr.unitr.Meeting.MeetingReview;
import net.unitr.unitr.Model.Meeting;
import net.unitr.unitr.Model.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jakak on 20. 05. 2018.
 */

public class MeetingItem extends LinearLayout {
	public View view;
	Meeting meeting;

	public MeetingItem(Meeting meeting, Context context) {
		super(context);
		this.meeting = meeting;
		init();
	}

	public MeetingItem(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	void init() {
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.view_meeting, null);

		TextView lblName = view.findViewById(R.id.lblName);
		//lblName.setText(meeting.getTitle());

		//CircleImageView imgProfile = (CircleImageView)view.findViewById(R.id.profile_image);
		//if(user.profilePicture != null) imgProfile.setImageBitmap(user.profilePicture);
		addView(view);
		view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Meeting.current = meeting;
				Intent i = new Intent(getContext(), MeetingReview.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				getContext().startActivity(i);
			}
		});
	}

	/*@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}*/
}
