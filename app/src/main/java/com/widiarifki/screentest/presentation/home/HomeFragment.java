package com.widiarifki.screentest.presentation.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.widiarifki.screentest.MainActivity;
import com.widiarifki.screentest.R;
import com.widiarifki.screentest.presentation.menu.MenuFragment;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class HomeFragment extends Fragment implements IHomeView {

    private HomePresenter homePresenter;
    private Context mContext;
    private MainActivity mMainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mMainActivity = (MainActivity)mContext;
        mMainActivity.showActionBar(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homePresenter = new HomePresenter(this);

        final EditText inputName = (EditText) view.findViewById(R.id.inputName);
        Button btnEnter = (Button) view.findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString();
                mMainActivity.setName(name);
                homePresenter.checkPalindrome(name);
                goToMenu();
            }
        });

        return view;
    }

    @Override
    public void showDialogue(String title, String info) {
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(info)
                .create()
                .show();
    }

    @Override
    public void goToMenu() {
        mMainActivity.setFragment(new MenuFragment(), MenuFragment.TITLE);
    }
}
