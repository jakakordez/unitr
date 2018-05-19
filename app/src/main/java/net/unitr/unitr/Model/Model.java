package net.unitr.unitr.Model;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class Model {
	JSONObject object;

	public Model(JSONObject object){
		this.object = object;
	}

	String GetString(String name){
		try{
			return object.getString(name);
		}
		catch (Exception e){}
		return null;
	}

	Date GetDate(String name){
		try{
			return Api.dateFormat.parse(object.getString(name));
		}
		catch (Exception e){}
		return null;
	}

	double GetDouble(String name){
		try{
			return object.getDouble(name);
		}
		catch (Exception e){}
		return Double.NaN;
	}

	JSONObject GetObject(String name){
		try{
			return object.getJSONObject(name);
		}
		catch (Exception e){}
		return null;
	}
}
