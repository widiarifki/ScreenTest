package com.widiarifki.screentest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.widiarifki.screentest.MainActivity;
import com.widiarifki.screentest.R;
import com.widiarifki.screentest.adapter.EventAdapter;
import com.widiarifki.screentest.model.Event;

import java.util.List;

/**
 * Created by widiarifki on 24/02/2018.
 */

public class EventFragment extends Fragment {

    private Context mContext;
    private MainActivity mMainActivity;
    public static String TITLE = "Event";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        setHasOptionsMenu(true);
        mMainActivity = ((MainActivity) mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        List<Event> eventList = Event.dummyEvents();
        EventAdapter adapter = new EventAdapter(mContext, eventList);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.event_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_map) {
            mMainActivity.addStackedFragment(this, TITLE, new MapViewFragment(), MapViewFragment.TITLE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
