package com.widiarifki.screentest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.widiarifki.screentest.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (mFragmentManager.getBackStackEntryCount() > 0) {
                    if(getSupportActionBar() != null)
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // show back button
                } else {
                    if(getSupportActionBar() != null)
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });

        setFragment(new HomeFragment());
    }

    public void setFragment(Fragment fragment) {
        resetStack();
        mFragmentManager.beginTransaction()
                .replace(R.id.content_layout, fragment)
                .commit();
    }

    public void setFragment(Fragment fragment, String fragmentTitle){
        resetStack();
        mFragmentManager.beginTransaction()
                .replace(R.id.content_layout, fragment)
                .commit();
        if (fragmentTitle != null) setTitle(fragmentTitle);
    }

    public void resetStack(){
        // reset stack
        int fragmentCount = mFragmentManager.getBackStackEntryCount();
        if (fragmentCount > 0){
            for(int i = 0; i < fragmentCount; ++i) {
                mFragmentManager.popBackStack();
            }
        }
    }

    public void addStackedFragment(Fragment stackedFragment, String stackedFragmentTitle, Fragment newFragment, String newFragmentTitle) {
        mFragmentManager.beginTransaction()
                .hide(stackedFragment)
                .add(R.id.content_layout, newFragment, newFragmentTitle)
                .addToBackStack(stackedFragmentTitle)
                .commit();

        if (newFragmentTitle != null) setTitle(newFragmentTitle);
    }

    @Override
    public void onBackPressed() {
        // Check fragment back stack so we can set the right action bar title
        int fragmentCount = mFragmentManager.getBackStackEntryCount();
        if (fragmentCount > 0) {
            FragmentManager.BackStackEntry backEntry = mFragmentManager.getBackStackEntryAt(fragmentCount - 1);
            String fragmentTag = backEntry.getName();
            if (fragmentTag != null) {
                setTitle(fragmentTag);
            }
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showActionBar(boolean isShow){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            if (isShow) actionBar.show();
            else actionBar.hide();
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
