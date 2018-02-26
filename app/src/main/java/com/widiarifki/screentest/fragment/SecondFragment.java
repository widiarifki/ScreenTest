package com.widiarifki.screentest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.widiarifki.screentest.MainActivity;
import com.widiarifki.screentest.R;

public class SecondFragment extends Fragment {

    private Fragment mThis;
    private Context mContext;
    private String mGuest;
    private String mEvent;
    private MainActivity mMainActivity;
    public static String TITLE = "Screen Test";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThis = this;
        mContext = getContext();
        mMainActivity = ((MainActivity)mContext);
        mMainActivity.showActionBar(true);

        if (getArguments() != null) {
            mGuest = getArguments().getString("GUEST");
            mEvent = getArguments().getString("EVENT");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        txtName.setText("Nama: " + mMainActivity.getName());

        Button btnEvent = (Button) view.findViewById(R.id.btnEvent);
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.addStackedFragment(mThis, TITLE, new EventFragment(), EventFragment.TITLE);
            }
        });
        if(mEvent != null) btnEvent.setText("SELECTED EVENT: " + mEvent);

        Button btnGuest = (Button) view.findViewById(R.id.btnGuest);
        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.addStackedFragment(mThis, TITLE, new GuestFragment(), GuestFragment.TITLE);
            }
        });
        if(mGuest != null) btnGuest.setText("SELECTED GUEST: " + mGuest);

        return view;
    }
}
