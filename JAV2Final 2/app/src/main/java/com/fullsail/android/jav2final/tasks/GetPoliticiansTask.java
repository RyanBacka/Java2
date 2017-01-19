// Ryan Backa
// JAV2 - 1612
// GetPoliticiansTask

package com.fullsail.android.jav2final.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.fullsail.android.jav2final.data.Politician;
import com.fullsail.android.jav2final.util.PoliticiansHelper;

import java.util.ArrayList;


public class GetPoliticiansTask extends AsyncTask<Integer, Void, ArrayList<Politician>> {

    public static final int FILTER_ALL = 0x01001;
    public static final int FILTER_FAVORITES = 0x01002;

    public interface PoliticianDownloadListener {
        public void handlePoliticianData(ArrayList<Politician> politicians);
    }

    private Context mContext;
    private PoliticianDownloadListener mListener;

    public GetPoliticiansTask(Context context, PoliticianDownloadListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    protected ArrayList<Politician> doInBackground(Integer... params) {

        int filter = params[0];

        ArrayList<Politician> politicians = null;

        if(filter == FILTER_ALL) {
            politicians = PoliticiansHelper.getAllPoliticians();
        } else if(filter == FILTER_FAVORITES) {
            politicians = PoliticiansHelper.getFavoritePoliticians(mContext);
        }

        return politicians;
    }

    @Override
    protected void onPostExecute(ArrayList<Politician> politicians) {
        super.onPostExecute(politicians);

        if(mListener != null) {
            mListener.handlePoliticianData(politicians);
        }
    }
}
