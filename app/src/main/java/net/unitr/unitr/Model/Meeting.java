package net.unitr.unitr.Model;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class Meeting extends Model {

	public Place Location;
	public Date Timestamp;

	public ArrayList<User> Users;

	public Meeting(JSONObject object){
		super(object);
		Location = new Place(GetObject("Location"));
		Timestamp = GetDate("Timestamp");

		Users = new ArrayList<>();
		try {
			JSONArray array = object.optJSONArray("Users");
			for (int i = 0; i < array.length(); i++){
				User u = new User(array.getJSONObject(i));
				if(u != null) Users.add(u);
			}
		}
		catch(Exception e){}
	}


}
