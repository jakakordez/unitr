package net.unitr.unitr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.unitr.unitr.Model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Registration extends AppCompatActivity {
	EditText txtHobby, txtName;
	LinearLayout lstHobbies;
	ImageView imgView;
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

		imgView = findViewById(R.id.imgProfile);
		imgView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dispatchTakePictureIntent();
			}
		});

		Button btnSave = (Button)findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

		txtName.setText(User.me.Name);

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

	static final int REQUEST_IMAGE_CAPTURE = 1;

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");

			imgView.setImageBitmap(imageBitmap);
		}
	}
}
