package net.unitr.unitr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Registration extends AppCompatActivity {
	EditText txtHobby;
	LinearLayout lstHobbies;
	final ArrayList<String> items = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		txtHobby = findViewById(R.id.txtHobby);

		lstHobbies = findViewById(R.id.lstHobbies);

		items.add("Climbing");
		items.add("Cycling");
		items.add("Hiking");

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, items);
		final AutoCompleteTextView txtHobby = (AutoCompleteTextView) findViewById(R.id.txtHobby);
		txtHobby.setAdapter(adapter);
		txtHobby.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				txtHobby.showDropDown();

			}
		});

		txtHobby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				final HobbyView hobbyView = new HobbyView(getApplicationContext(), null);
				hobbyView.setHobby(adapter.getItem(i));
				hobbyView.removeRunnable = new Runnable() {
					@Override
					public void run() {
						adapter.add(hobbyView.hobby);
						txtHobby.setAdapter(adapter);
						lstHobbies.removeView(hobbyView);
						txtHobby.refreshDrawableState();
						txtHobby.invalidate();
					}
				};
				lstHobbies.addView(hobbyView);
				txtHobby.setText("");
				adapter.remove(adapter.getItem(i));
				txtHobby.setAdapter(adapter);
				txtHobby.refreshDrawableState();
				txtHobby.invalidate();
			}
		});
	}
}
