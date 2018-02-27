package com.widiarifki.screentest.presentation.menu;

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
import com.widiarifki.screentest.presentation.event.EventFragment;
import com.widiarifki.screentest.presentation.guest.GuestFragment;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class MenuFragment extends Fragment implements IMenuView,View.OnClickListener {
    public static String TITLE = "Menu";

    private Context mContext;
    private MainActivity mMainActivity;
    private MenuPresenter menuPresenter;

    TextView txtName;
    Button btnEvent;
    Button btnGuest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mMainActivity = ((MainActivity)mContext);
        mMainActivity.showActionBar(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        menuPresenter = new MenuPresenter(this);

        txtName = (TextView) view.findViewById(R.id.txtName);
        btnEvent = (Button) view.findViewById(R.id.btnEvent);
        btnEvent.setOnClickListener(this);
        btnGuest = (Button) view.findViewById(R.id.btnGuest);
        btnGuest.setOnClickListener(this);

        writeUserName("Nama: " + mMainActivity.getName());

        Bundle args = getArguments();
        if(args != null){
            if(args.getString("EVENT") != null)
                menuPresenter.setEventName(args.getString("EVENT"));

            if(args.getString("GUEST") != null)
                menuPresenter.setGuestName(args.getString("GUEST"));
        }

        return view;
    }


    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        switch (btn.getId())
        {
            case R.id.btnEvent:
                goToEvent();
                break;
            case R.id.btnGuest:
                goToGuest();
                break;
        }
    }

    @Override
    public void writeUserName(String username) {
        txtName.setText(username);
    }

    @Override
    public void goToEvent() {
        mMainActivity.addStackedFragment(this, TITLE, new EventFragment(), EventFragment.TITLE);
    }

    @Override
    public void goToGuest() {
        mMainActivity.addStackedFragment(this, TITLE, new GuestFragment(), GuestFragment.TITLE);
    }

    @Override
    public void writeEvent(String eventName) {
        btnEvent.setText("SELECTED EVENT: " + eventName);
    }

    @Override
    public void writeGuest(String guestName) {
        btnGuest.setText("SELECTED GUEST: " + guestName);
    }
}
