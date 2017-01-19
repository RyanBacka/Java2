// Ryan Backa
// JAV2 - 1612
// VoteInfoActivity

package com.fullsail.android.jav2final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fullsail.android.jav2final.fragment.VoteInfoFragment;


public class VoteInfoActivity extends Activity {

    public static final String EXTRA_VOTE_ID = "VoteInfoActivity.EXTRA_VOTE_ID";
    private static final String TAG = "VoteInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        Intent intent = getIntent();

        int voteId = intent.getIntExtra(EXTRA_VOTE_ID,-1);
        if (voteId == -1) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            VoteInfoFragment frag = VoteInfoFragment.newInstance(voteId);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, frag, VoteInfoFragment.TAG)
                    .commit();
        }

    }
}
