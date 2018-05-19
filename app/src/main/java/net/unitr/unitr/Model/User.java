package net.unitr.unitr.Model;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class User  extends Model{
	public String Name;
	public ArrayList<String> Hobbies;

	public static User me;

	public User(JSONObject object) {
		super(object);
		Name = GetString("Name");
		Hobbies = new ArrayList<>();
		
	}

	@Override
	public String toString() {
		return Name;
	}
}
