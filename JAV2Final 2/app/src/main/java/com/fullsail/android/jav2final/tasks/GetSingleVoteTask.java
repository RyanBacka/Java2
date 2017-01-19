// Ryan Backa
// JAV2 - 1612
// GetSingleVoteTask

package com.fullsail.android.jav2final.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.fullsail.android.jav2final.data.Vote;
import com.fullsail.android.jav2final.util.VoteHelper;


public class GetSingleVoteTask extends AsyncTask<Integer, Void, Vote> {

    private static final String TAG = "GetSingleVoteTask";

    public interface VoteDownloadListener {
        public void handleVoteData(Vote vote);
    }

    private VoteDownloadListener mListener;

    public GetSingleVoteTask(VoteDownloadListener listener) {
        mListener = listener;
    }

    @Override
    protected Vote doInBackground(Integer... params) {

        int voteId = params[0];
        Vote vote = VoteHelper.getVoteForId(voteId);
        if(vote!=null) {
            Log.d(TAG, vote.getOutcome());
        }
        return vote;
    }

    @Override
    protected void onPostExecute(Vote vote) {
        super.onPostExecute(vote);

        if(mListener != null) {
            mListener.handleVoteData(vote);
        }
    }
}
