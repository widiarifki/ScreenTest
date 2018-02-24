package com.widiarifki.screentest.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.widiarifki.screentest.MainActivity;
import com.widiarifki.screentest.R;
import com.widiarifki.screentest.fragment.SecondFragment;
import com.widiarifki.screentest.model.Guest;

import java.util.List;

/**
 * Created by widiarifki on 24/02/2018.
 */

public class GuestAdapter extends RecyclerView.Adapter {

    List<Guest> mObjects;
    Context mContext;

    public GuestAdapter(Context context, List<Guest> objects){
        mObjects = objects;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_guest, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((RecyclerView)parent).getChildLayoutPosition(v);
                Guest guestData = mObjects.get(position);

                // Conditional Display
                String[] birthDateSplit = guestData.getBirthDate().split("-");
                int date = Integer.parseInt(birthDateSplit[2]);

                String displayedTxt = "";
                if(date%2 == 0 && date%3 == 0) displayedTxt = "iOS";
                else if(date%2 == 0) displayedTxt = "Blackberry";
                else if(date%3 == 0) displayedTxt = "Android";
                else displayedTxt = "Featured Phone";

                new AlertDialog.Builder(mContext).setMessage(displayedTxt).create().show();

                // Change Fragment
                Bundle args = new Bundle();
                args.putString("GUEST", guestData.getName());

                Fragment secondFrag = new SecondFragment();
                secondFrag.setArguments(args);
                ((MainActivity)mContext).setFragment(secondFrag);
            }
        });
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Guest dataItem = mObjects.get(position);
        GuestViewHolder viewHolder = (GuestViewHolder) holder;
        viewHolder.txtName.setText(dataItem.getName());
        viewHolder.txtBirthDate.setText(dataItem.getBirthDate());
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    private class GuestViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtBirthDate;

        public GuestViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtBirthDate = (TextView) view.findViewById(R.id.txtBirthDate);
        }
    }
}
