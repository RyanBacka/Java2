// Ryan Backa
// JAV2 - 1612
// PoliticianListFragment

package com.fullsail.android.jav2final.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fullsail.android.jav2final.R;
import com.fullsail.android.jav2final.SettingsActivity;
import com.fullsail.android.jav2final.data.Politician;
import com.fullsail.android.jav2final.tasks.GetPoliticiansTask;
import com.fullsail.android.jav2final.util.SettingsHelper;

import java.util.ArrayList;


public class PoliticianListFragment extends ListFragment implements GetPoliticiansTask.PoliticianDownloadListener {

    public static final String TAG = "PoliticianListFragment.TAG";
    public static final int FILTER_ALL = 0x02001;
    public static final int FILTER_FAVORITES = 0x02002;

    private static final String ARG_FILTER = "PoliticianListFragment.ARG_FILTER";

    public static PoliticianListFragment newInstance(int filter) {
        PoliticianListFragment frag = new PoliticianListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FILTER, filter);
        frag.setArguments(args);
        return frag;
    }

    public interface PoliticianSelectedListener {
        public void politicianSelected(Politician politician);
    }

    private GetPoliticiansTask mTask;
    private PoliticianSelectedListener mListener;
    private int mFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof PoliticianSelectedListener) {
            mListener = (PoliticianSelectedListener)activity;
        } else {
            throw new IllegalArgumentException("Containing activity must implement " +
                    "the PoliticianSelectedListener interface.");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if(args != null) {
            mFilter = args.getInt(ARG_FILTER, FILTER_ALL);
            startTask();
        }

        if(mFilter == FILTER_FAVORITES) {
            setEmptyText(getString(R.string.no_favorites));
        } else {
            setEmptyText(getString(R.string.no_data_to_display));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        stopTask();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Politician politician = (Politician)l.getAdapter().getItem(position);
        if(mListener != null) {
            mListener.politicianSelected(politician);
        }
    }

    public void startTask() {
        stopTask();

        mTask = new GetPoliticiansTask(getActivity(), this);
        if(mFilter == FILTER_ALL) {
            mTask.execute(GetPoliticiansTask.FILTER_ALL);
        } else if(mFilter == FILTER_FAVORITES) {
            mTask.execute(GetPoliticiansTask.FILTER_FAVORITES);
        }
    }

    private void stopTask() {
        if(mTask != null) {
            mTask.cancel(false);
            mTask = null;
        }
    }

    @Override
    public void handlePoliticianData(ArrayList<Politician> politicians) {
        if(politicians == null) {
            politicians = new ArrayList<>();
        }

        ListAdapter adapter = null;
        if(SettingsHelper.getListType(getActivity()) == SettingsHelper.LIST_TYPE_DETAILED) {
            adapter = new PoliticianAdapter(getActivity(), politicians);
        }
        setListAdapter(adapter);
    }

    private static class PoliticianAdapter extends BaseAdapter {

        private static final int ID_CONSTANT = 0x01010101;

        private Context mContext;
        private ArrayList<Politician> mPoliticians;

        public PoliticianAdapter(Context context, ArrayList<Politician> politicians) {
            mContext = context;
            mPoliticians = politicians;
        }

        @Override
        public int getCount() {
            if(mPoliticians!=null){
                return mPoliticians.size();
            } else {
                return 0;
            }
        }

        @Override
        public Politician getItem(int position) {
            if (mPoliticians != null && position >= 0 && position < mPoliticians.size()) {
                return mPoliticians.get(position);
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            if (mPoliticians != null && position >= 0 && position < mPoliticians.size()) {
                return ID_CONSTANT + position;
            } else {
                return 0;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.politicians_list_item, parent, false);
            }

            Politician politician = getItem(position);

            TextView tv = (TextView)convertView.findViewById(R.id.name);
            tv.setText(politician.getName());

            tv = (TextView)convertView.findViewById(R.id.party);
            tv.setText(politician.getParty());

            tv = (TextView)convertView.findViewById(R.id.description);
            tv.setText(politician.getDescription());

            return convertView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
