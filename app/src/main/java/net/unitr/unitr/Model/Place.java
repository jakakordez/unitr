package net.unitr.unitr.Model;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class Place extends Model{
	public String Name, Address;
	public double Latitude, Longitude;

	public Place(JSONObject object) {
		super(object);

		Name = GetString("name");
		Address = GetString("address");
		Latitude = GetDouble("latitude");
		Longitude = GetDouble("longitude");
	}

	public LatLng GetLatLng(){
		return new LatLng(Latitude, Longitude);
	}
}
