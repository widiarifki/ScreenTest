package com.widiarifki.screentest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final Context context = getContext();

        final EditText inputName = (EditText) view.findViewById(R.id.inputName);
        Button btnEnter = (Button) view.findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).setName(inputName.getText().toString());
                ((MainActivity)context).setFragment(new SecondFragment());
            }
        });

        return view;
    }
}
