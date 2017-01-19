// Ryan Backa
// JAV2 - 1612
// GetSingleVoteInfoTask

package com.fullsail.android.jav2final.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.fullsail.android.jav2final.data.VoteInfo;
import com.fullsail.android.jav2final.util.VoteInfoHelper;

import java.util.ArrayList;


public class GetVoteInfoTask extends AsyncTask<Integer, Void, ArrayList<VoteInfo>> {

    private static final String TAG = "GetVoteInfoTask";

    public interface VoteInfoDownloadListener {
        public void handleVoteInfoData(ArrayList<VoteInfo> info);
    }

    private VoteInfoDownloadListener mListener;

    public GetVoteInfoTask(VoteInfoDownloadListener listener) {
        mListener = listener;
    }

    @Override
    protected ArrayList<VoteInfo> doInBackground(Integer... params) {

        int politicianId = params[0];
        ArrayList<VoteInfo> voteInfo = VoteInfoHelper.getVoteHistory(politicianId);

        return voteInfo;
    }

    @Override
    protected void onPostExecute(ArrayList<VoteInfo> voteInfo) {
        super.onPostExecute(voteInfo);

        if(mListener != null) {
            mListener.handleVoteInfoData(voteInfo);
        }
    }
}
