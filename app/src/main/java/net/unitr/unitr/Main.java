package net.unitr.unitr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import net.unitr.unitr.Meeting.MeetingReview;
import net.unitr.unitr.Model.Api;
import net.unitr.unitr.Model.Meeting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main extends AppCompatActivity {
	Button btnMeeting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		btnMeeting = (Button)findViewById(R.id.btnMeeting);
		btnMeeting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			openMeeting();
			}
		});

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

	private void openMeeting(){

		Api.Download("meeting", new Api.PCallable() {
			@Override
			public void call(JSONArray obj) {

				try {
					Meeting.current = new Meeting(obj.getJSONObject(0));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Intent i = new Intent(getApplicationContext(), MeetingReview.class);
				startActivity(i);
			}
		});

	}
}
