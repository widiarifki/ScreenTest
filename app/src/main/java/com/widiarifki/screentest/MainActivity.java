package com.widiarifki.screentest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.widiarifki.screentest.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        setFragment(new HomeFragment());
    }

    public void setFragment(Fragment fragment){
        mFragmentManager.beginTransaction()
                .replace(R.id.content_layout, fragment)
                .commit();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
