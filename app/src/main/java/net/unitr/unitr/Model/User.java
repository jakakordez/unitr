package net.unitr.unitr.Model;

import org.json.JSONObject;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class User  extends Model{
	public String Name;

	public User(JSONObject object) {
		super(object);
		Name = GetString("Name");
	}

	@Override
	public String toString() {
		return Name;
	}
}
