package com.widiarifki.screentest.presentation.event;

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
import com.widiarifki.screentest.presentation.mapview.MapViewFragment;

import java.util.List;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class EventFragment extends Fragment implements IEventView {

    public static String TITLE = "Event";

    private Context mContext;
    private MainActivity mMainActivity;
    private EventPresenter eventPresenter;

    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mMainActivity = ((MainActivity) mContext);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        eventPresenter = new EventPresenter(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        eventPresenter.setEventList(Event.dummyEvents());

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
            goToMapview();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showList(List<Event> eventList) {
        EventAdapter adapter = new EventAdapter(mContext, eventList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void goToMapview() {
        mMainActivity.addStackedFragment(this, TITLE, new MapViewFragment(), MapViewFragment.TITLE);
    }
}
