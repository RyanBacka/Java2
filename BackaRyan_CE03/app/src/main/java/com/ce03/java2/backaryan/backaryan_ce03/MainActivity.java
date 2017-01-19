//Ryan Backa
//Java 1612
//MainActivity
package com.ce03.java2.backaryan.backaryan_ce03;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ce03.java2.backaryan.backaryan_ce03.Objects.Music;
import com.ce03.java2.backaryan.backaryan_ce03.fragments.FirstDetailFragment;
import com.ce03.java2.backaryan.backaryan_ce03.fragments.OptionsFragment;
import com.ce03.java2.backaryan.backaryan_ce03.fragments.SecondDetailFragment;
import com.ce03.java2.backaryan.backaryan_ce03.fragments.ThirdDetailFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        if(spinner!=null){
            spinner.setOnItemSelectedListener(this);
        }

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null && info.isConnected()){
            GetData task = new GetData();
            String urlString = "https://api.spotify.com/v1/search?q=Rage&type=artist";
            task.execute(urlString);
        } else {
            boolean saved = true;
            if(saved) {
                Toast.makeText(MainActivity.this, "Saved data because phone is disconnected", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Phone is disconnected", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(position){
            case 0:
                Fragment firstFrag = FirstDetailFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details,firstFrag)
                        .commit();
                break;
            case 1:
                Fragment secondFrag = SecondDetailFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details,secondFrag)
                        .commit();
                break;
            case 2:
                Fragment thirdFrag = ThirdDetailFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details,thirdFrag)
                        .commit();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class GetData extends AsyncTask<String, Void, ArrayList<Music>>  {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Music> doInBackground(String... params) {
            ArrayList<Music> artistList = new ArrayList<>();
            GetNetworkData getData = new GetNetworkData();
            String data = getData.getNetworkData(params[0]);
            try {
                JSONObject fullObject = new JSONObject(data);
                JSONObject artists = fullObject.getJSONObject("artists");
                JSONArray items = artists.getJSONArray("items");
                for(int i=0; i<items.length(); i++){
                    JSONObject artistObj = items.getJSONObject(i);
                    String name = artistObj.getString("name");
                    JSONArray genreArray = artistObj.getJSONArray("genres");
                    ArrayList<String> genres = new ArrayList<>();
                    if(genreArray.length()>0) {
                        for (int x = 0; x < genreArray.length(); x++) {
                            genres.add(genreArray.getString(x));
                        }
                    }
                    JSONObject followersObj = artistObj.getJSONObject("followers");
                    int followers = followersObj.getInt("total");
                    int popularity = artistObj.getInt("popularity");
                    Music music = new Music(name,genres,followers,popularity);
                    artistList.add(music);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SaveData save = new SaveData();
            save.saveData(artistList, getApplicationContext());

            return artistList;
        }

        @Override
        protected void onPostExecute(ArrayList<Music> musics) {
            super.onPostExecute(musics);
            ArrayList<String> names = new ArrayList<>();
            if(!musics.isEmpty()) {
                for (Music object : musics) {
                    names.add(object.getName());
                }
                Fragment optionsFrag = OptionsFragment.newInstance(names);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragList, optionsFrag)
                        .commit();
            }
        }
    }
}
