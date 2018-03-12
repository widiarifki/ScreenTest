package com.widiarifki.screentest.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.widiarifki.screentest.MainActivity;
import com.widiarifki.screentest.R;
import com.widiarifki.screentest.model.Event;
import com.widiarifki.screentest.presentation.menu.MenuFragment;

import java.util.List;

/**
 * Created by widiarifki on 24/02/2018.
 */

public class EventAdapter extends RecyclerView.Adapter {

    List<Event> mObjects;
    Context mContext;

    public EventAdapter(Context context, List<Event> objects){
        mContext = context;
        mObjects = objects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_event, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((RecyclerView)parent).getChildLayoutPosition(v);
                Event eventData = mObjects.get(position);

                Bundle args = new Bundle();
                args.putString("EVENT", eventData.getNama());
                Fragment secondFrag = new MenuFragment();
                secondFrag.setArguments(args);
                ((MainActivity)mContext).setFragment(secondFrag, MenuFragment.TITLE);
            }
        });
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Event dataItem = mObjects.get(position);
        EventViewHolder viewHolder = (EventViewHolder) holder;
        viewHolder.txtEvent.setText(dataItem.getNama());
        viewHolder.txtEventDate.setText(dataItem.getTanggal());
        viewHolder.txtDesc.setText(dataItem.getDesc());
        viewHolder.imgEvent.setImageResource(dataItem.getImgResId());
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    private class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEvent;
        TextView txtEvent;
        TextView txtEventDate;
        TextView txtDesc;

        public EventViewHolder(View view) {
            super(view);
            imgEvent = (ImageView) view.findViewById(R.id.imgEvent);
            txtEvent = (TextView) view.findViewById(R.id.txtEvent);
            txtEventDate = (TextView) view.findViewById(R.id.txtEventDate);
            txtDesc = (TextView) view.findViewById(R.id.txtDesc);
        }
    }
}
