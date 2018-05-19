package net.unitr.unitr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Registration extends AppCompatActivity {
	EditText txtHobby;
	LinearLayout lstHobbies;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		txtHobby = findViewById(R.id.txtHobby);


		lstHobbies = findViewById(R.id.lstHobbies);

		final String[] items = new String[]{"Cycling", "Skiing", "Climbing"};
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
				HobbyView hobbyView = new HobbyView(getApplicationContext(), null);
				hobbyView.setHobby(adapter.getItem(i));
				lstHobbies.addView(hobbyView);
				txtHobby.setText("");
			}
		});


	}
}
