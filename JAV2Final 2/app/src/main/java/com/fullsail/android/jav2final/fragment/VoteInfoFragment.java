// Ryan Backa
// JAV2 - 1612
// VoteInfoFragment

package com.fullsail.android.jav2final.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fullsail.android.jav2final.R;
import com.fullsail.android.jav2final.data.Vote;
import com.fullsail.android.jav2final.tasks.GetSingleVoteTask;


public class VoteInfoFragment extends Fragment implements GetSingleVoteTask.VoteDownloadListener{

    public static final String TAG = "VoteInfoFragment.TAG";

    private static final String ARG_VOTE_ID = "VoteInfoFragment.ARG_VOTE_ID";

    public static VoteInfoFragment newInstance(int voteId) {
        VoteInfoFragment frag = new VoteInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_VOTE_ID,voteId);
        frag.setArguments(args);
        return frag;
    }

    private GetSingleVoteTask mTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vote_info_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if(args != null) {
            int voteId = args.getInt(ARG_VOTE_ID, -1);
            startTask(voteId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        stopTask();
    }

    private void startTask(int voteId) {
        stopTask();

        mTask = new GetSingleVoteTask(this);
        mTask.execute(voteId);
    }

    private void stopTask() {
        if(mTask != null) {
            mTask.cancel(false);
            mTask = null;
        }
    }

    // Nothing wrong with this method. Do not alter.
    private void updateUI(Vote vote) {
        View root = getView();
        if(root == null) {
            return;
        }

        TextView tv = (TextView)root.findViewById(R.id.chamber);
        tv.setText(vote.getChamber());

        tv = (TextView)root.findViewById(R.id.question);
        tv.setText(vote.getQuestion());

        tv = (TextView)root.findViewById(R.id.session);
        tv.setText(vote.getSession());

        tv = (TextView)root.findViewById(R.id.outcome);
        tv.setText(vote.getOutcome());
    }

    @Override
    public void handleVoteData(Vote vote) {
        Log.d(TAG,vote.getQuestion());
        updateUI(vote);
    }
}
