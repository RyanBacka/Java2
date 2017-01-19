// Ryan Backa
// JAV2 - 1612
// VoteHelper


package com.fullsail.android.jav2final.util;

import com.fullsail.android.jav2final.data.Vote;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class VoteHelper {

    public static Vote getVoteForId(int voteId) {

        String urlString = "https://www.govtrack.us/api/v2/vote?id=" + voteId;

        try {

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.connect();

            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);
            is.close();

            connection.disconnect();

            JSONObject response = new JSONObject(data);
            JSONArray objects = response.getJSONArray("objects");

            if(objects.length() > 0) {
                JSONObject obj = objects.getJSONObject(0);

                String chamber = obj.getString("chamber_label");
                String question = obj.getString("question");
                String session = obj.getString("session");
                String outcome = obj.getString("result");

                return new Vote(chamber, question, session, outcome);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
