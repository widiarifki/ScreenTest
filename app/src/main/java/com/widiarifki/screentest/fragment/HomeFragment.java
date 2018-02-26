package com.widiarifki.screentest.fragment;

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

/**
 * Created by widiarifki on 24/02/2018.
 */

public class HomeFragment extends Fragment {

    Context mContext;
    MainActivity mMainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mMainActivity = ((MainActivity)mContext);
        mMainActivity.showActionBar(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final EditText inputName = (EditText) view.findViewById(R.id.inputName);
        Button btnEnter = (Button) view.findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputName.getText().toString();
                // Show whether name palindrome/not
                new AlertDialog.Builder(mContext).setTitle(name)
                        .setMessage(isPalindrome(name) ? "is Palindrome" : "not Palindrome")
                        .create()
                        .show();

                mMainActivity.setName(name);
                mMainActivity.setFragment(new SecondFragment(), SecondFragment.TITLE);
            }
        });

        return view;
    }

    private boolean isPalindrome(String name) {
        String word = name.replace(" ", "");
        String revWord = new StringBuilder(word).reverse().toString();
        return word.equals(revWord);
    }
}
