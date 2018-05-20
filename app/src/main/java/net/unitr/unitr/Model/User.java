package net.unitr.unitr.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import net.unitr.unitr.Meeting.ParticipantItem;

import org.json.JSONObject;

import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class User extends Model{
	public String Name, Username;
	public ArrayList<String> Hobbies;

	public static User me;
	public Bitmap profilePicture;

	public User(JSONObject object) {
		super(object);
		Name = GetString("name");
		Username = GetString("username");
		Hobbies = new ArrayList<>();

		
		String profileURL = GetString("profile_image");
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

	public ParticipantItem getParticipatingItem(Context ctx){
		return new ParticipantItem(this, ctx);
	}

	static String[] colors = new String[]{"#ffeb3b", "#4caf50", "#f44336", "#ff5722", "#673ab7"};

	public String getColor(){
		int sum = 0;
		char[] chars = new char[Name.length()];
		Name.getChars(0, Name.length(), chars, 0);
		for(int i = 0; i < Name.length(); i++){
			sum += chars[i];
		}
		return colors[sum%colors.length];
	}

}
