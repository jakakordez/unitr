package net.unitr.unitr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import net.unitr.unitr.Meeting.MeetingReview;
import net.unitr.unitr.Model.Api;
import net.unitr.unitr.Model.Meeting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
	LinearLayout meetingContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		meetingContent = findViewById(R.id.meeting_content);


		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//						.setAction("Action", null).show();
				Intent i = new Intent(getApplicationContext(), Login.class);
				startActivity(i);
			}
		});
		getSupportActionBar().setTitle("");
		getSupportActionBar().setIcon(R.drawable.ic_logo_light);
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadMeetings();
	}

	private void loadMeetings(){

		Api.Download("meeting", new Api.PCallable() {
			@Override
			public void call(JSONArray arr) {
				try {
					Meeting.storage = new ArrayList<>();

					for (int i = 0; i < arr.length(); i++) {
						Meeting meeting = new Meeting(arr.getJSONObject(i));
						Meeting.storage.add(meeting);
						MeetingItem itm = new MeetingItem(meeting, getApplicationContext());
						meetingContent.addView(itm);
					}
					//Meeting.current = new Meeting(arr.getJSONObject(0));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

	}
}
