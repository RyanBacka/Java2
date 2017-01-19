// Ryan Backa
// JAV2 - 1612
// PoliticianActivity

package com.fullsail.android.jav2final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.fullsail.android.jav2final.data.Politician;
import com.fullsail.android.jav2final.fragment.PoliticianListFragment;
import com.fullsail.android.jav2final.fragment.VoteHistoryListFragment;
import com.fullsail.android.jav2final.util.PoliticiansHelper;


public class PoliticianActivity extends Activity implements VoteHistoryListFragment.VoteSelectedListener{

    public static final String EXTRA_POLITICIAN = "PoliticianActivity.EXTRA_POLITICIAN";
    private static final String ACTION_VIEW_VOTE = "com.fullsail.android.ACTION_VIEW_VOTE";
    private static final String TAG = "PoliticianActivity";

    private Politician mPolitician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        Intent intent = getIntent();

        if(!intent.hasExtra(EXTRA_POLITICIAN)) {
            finish();
            throw new IllegalArgumentException("Must pass a politician to the " +
                    "PoliticianActivity to view voting history.");
        }

        mPolitician = (Politician)intent.getSerializableExtra(EXTRA_POLITICIAN);
        setTitle(mPolitician.getName());

        if(savedInstanceState == null) {
            VoteHistoryListFragment frag = VoteHistoryListFragment.newInstance(mPolitician);
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, frag, VoteHistoryListFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onVoteSelected(int voteId) {
        Intent intent = new Intent(getApplicationContext(),VoteInfoActivity.class);
        intent.putExtra(VoteInfoActivity.EXTRA_VOTE_ID,voteId);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_save) {
            PoliticiansHelper.saveToFavorites(this, mPolitician);
            Toast.makeText(this, R.string.politician_saved, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_politician,menu);
        return true;
    }

}
