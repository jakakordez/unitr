package net.unitr.unitr.Model;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class Api {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static SimpleDateFormat outDateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm");
	public interface PCallable {
		void call(JSONArray s);
	}
	public static String address = "http://getunitr.com/api/v1/";

	public static void Download(String endpoint, final PCallable callable) {
		AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... strings) {
				try {
					URLConnection yc = new URL(address + strings[0]).openConnection();
					yc.setReadTimeout(5000);
					yc.setConnectTimeout(5000);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(yc.getInputStream()));
					String inputLine;
					StringBuilder result = new StringBuilder();
						while ((inputLine = in.readLine()) != null)
							result.append(inputLine);
					in.close();
					return result.toString();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String s) {
				if(s != null) {
					try {
						callable.call(new JSONObject(s).getJSONArray("objects"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}.execute(endpoint);

	}
}
