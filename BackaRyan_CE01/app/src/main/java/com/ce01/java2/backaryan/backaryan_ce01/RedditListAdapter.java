// Ryan Backa
// Jav2 - 1611
// RedditListAdapter

package com.ce01.java2.backaryan.backaryan_ce01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ryankbacka on 11/30/16.
 */

class RedditListAdapter extends BaseAdapter {
    private static final int ID_CONSTANT = 0x01000000;
    private ArrayList<Reddit> reddits;
    private Context context;

    RedditListAdapter(MainActivity _mainActivity, ArrayList<Reddit> _reddits) {
        context = _mainActivity;
        reddits = _reddits;
    }

    @Override
    public int getCount() {
        if (reddits != null) {
            return reddits.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (reddits != null && position >= 0 && position < reddits.size()) {
            return reddits.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (reddits != null && position >= 0 && position < reddits.size()) {
            return ID_CONSTANT + position;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
            holder.titleView = (TextView) convertView.findViewById(R.id.titleRV);
            holder.authorView = (TextView) convertView.findViewById(R.id.authorRv);
            holder.commentsView = (TextView) convertView.findViewById(R.id.commentsRV);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Reddit reddit = (Reddit) getItem(position);
        holder.titleView.setText(reddit.title);
        holder.authorView.setText(reddit.author);
        if (holder.commentsView != null) {
            String comments = String.valueOf(reddit.comments);
            holder.commentsView.setText(comments);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView commentsView;
    }
}
