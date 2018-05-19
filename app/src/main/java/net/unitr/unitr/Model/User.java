package net.unitr.unitr.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class User  extends Model{
	public String Name;
	public ArrayList<String> Hobbies;

	public static User me;
	public Bitmap profilePicture;

	public User(JSONObject object) {
		super(object);
		Name = GetString("Name");
		Hobbies = new ArrayList<>();
		
		String profileURL = GetString("ProfilePicture");
		try {
			profilePicture = drawable_from_url(profileURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Bitmap drawable_from_url(String url) throws java.net.MalformedURLException, java.io.IOException {

		HttpURLConnection connection = (HttpURLConnection)new URL(url) .openConnection();
		connection.setRequestProperty("User-agent","Mozilla/4.0");

		connection.connect();
		InputStream input = connection.getInputStream();

		return BitmapFactory.decodeStream(input);
	}

	@Override
	public String toString() {
		return Name;
	}
}
