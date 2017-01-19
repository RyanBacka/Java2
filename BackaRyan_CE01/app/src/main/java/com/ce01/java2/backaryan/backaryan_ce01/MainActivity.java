// Ryan Backa
// Jav2 - 1611
// MainActivity

package com.ce01.java2.backaryan.backaryan_ce01;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        if (spinner!=null) {
            spinner.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        choice = spinner.getSelectedItem().toString();
        checkNetwork(choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private class GetData extends AsyncTask<String, Void, ArrayList<Reddit>> {

        @Override
        protected ArrayList<Reddit> doInBackground(String... params) {
            ArrayList<Reddit> reddits = new ArrayList<>();
            GetNetworkData getData = new GetNetworkData();
            String data = getData.getNetworkData(params[0]);
            try {
                JSONObject fullObject = new JSONObject(data);
                JSONObject objectData = fullObject.getJSONObject("data");
                JSONArray children = objectData.getJSONArray("children");
                for(int i=0; i<children.length(); i++){
                    JSONObject postObject = children.getJSONObject(i);
                    JSONObject postData = postObject.getJSONObject("data");
                    String title = postData.getString("title");
                    String author = postData.getString("author");
                    int comments = postData.getInt("num_comments");
                    Reddit reddit = new Reddit(title, author, comments);
                    reddits.add(reddit);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            saveReddits(reddits,choice);


            return reddits;
        }

        @Override
        protected void onPostExecute(ArrayList<Reddit> reddits) {
            super.onPostExecute(reddits);
            setList(reddits);
        }
    }


    private void checkNetwork(String choice){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null && info.isConnected()){
            GetData task = new GetData();
            String urlString = "https://www.reddit.com/r/"+choice+"/hot.json";
            task.execute(urlString);
        } else {
            ArrayList<Reddit> savedReddits = readReddits(choice);
            boolean saved = setList(savedReddits);
            if(saved) {
                Toast.makeText(MainActivity.this, "Saved data because phone is disconnected", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Phone is disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveReddits(ArrayList<Reddit> reddits, String choice){
        try {
            FileOutputStream fos = openFileOutput(choice, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(reddits);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Reddit> readReddits(String choice){
        ArrayList<Reddit> reddits = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(choice);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ois.close();
            fis.close();
            if(obj instanceof ArrayList<?>){
                ArrayList<?> arrayList = (ArrayList<?>) obj;
                if(arrayList.size() > 0){
                    for(int i=0; i<arrayList.size(); i++){
                        Object myObject = arrayList.get(i);
                        if(myObject instanceof Reddit){
                            Reddit item = (Reddit) myObject;
                            reddits.add(item);
                        }
                    }
                }
            }
            return reddits;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean setList(ArrayList<Reddit> reddits){
        if(reddits!=null){
            ListView redditList = (ListView) findViewById(R.id.redditList);
            RedditListAdapter redditAdapter = new RedditListAdapter(MainActivity.this, reddits);
            if(redditList!=null)
                redditList.setAdapter(redditAdapter);
            return true;
        }
        return false;
    }
}
