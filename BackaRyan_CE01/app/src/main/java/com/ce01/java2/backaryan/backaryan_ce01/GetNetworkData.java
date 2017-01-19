// Ryan Backa
// Jav2 - 1611
// GetNetworkData

package com.ce01.java2.backaryan.backaryan_ce01;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ryankbacka on 11/30/16.
 */

class GetNetworkData {

    String getNetworkData(String urlString){
        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String data = IOUtils.toString(inputStream);
            inputStream.close();
            connection.disconnect();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
