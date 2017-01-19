// Ryan Backa
// JAV2 - 1612
// PoliticiansHelper

package com.fullsail.android.jav2final.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.fullsail.android.jav2final.data.Politician;
import com.fullsail.android.jav2final.fragment.PoliticianListFragment;
import com.fullsail.android.jav2final.fragment.SettingsFragment;

public class PoliticiansHelper implements PoliticianListFragment.PoliticianSelectedListener{

    private static final String FAVORITES_FILE = "favorites.dat";
	private static final String  TAG = "PoliticiansHelper" ;

	public static ArrayList<Politician> getAllPoliticians() {
		ArrayList<Politician> politicians = new ArrayList<>();

		try {
			Log.d(TAG,"You are trying to download Politicians");
			URL url = new URL("https://www.govtrack.us/api/v2/role?current=true");

			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.connect();

			InputStream is = connection.getInputStream();
			String data = IOUtils.toString(is);
			is.close();
			connection.disconnect();

			JSONObject response = new JSONObject(data);
			JSONArray objects = response.getJSONArray("objects");

			for(int i = 0; i < objects.length(); i++) {
				JSONObject obj = objects.getJSONObject(i);
				JSONObject person = obj.getJSONObject("person");

				int id = person.getInt("id");
				String name = person.getString("name");
				String party = obj.getString("party");
				String description = obj.getString("description");
				politicians.add(new Politician(name, party, description, id));
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return politicians;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Politician> getFavoritePoliticians(Context context) {
		ArrayList<Politician> politicians = new ArrayList<>();

		// TODO: Load in favorite politicians from a file as an ArrayList of Politician objects.

		return politicians;
	}

	@SuppressWarnings("unchecked")
	public static void saveToFavorites(Context context, Politician politician) {

        if(politician == null) {
            throw new IllegalArgumentException("Cannot save a null politician.");
        }

		// TODO: Save out politician using an ArrayList of Politician objects.
		try {
			FileOutputStream fos = context.openFileOutput("politician", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(politician);
			oos.close();
			fos.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

    // Nothing wrong with this method. Leave it alone.
    public static void deleteAllFavorites(Context context) {
        context.deleteFile(FAVORITES_FILE);
    }

    @Override
    public void politicianSelected(Politician politician) {

    }
}