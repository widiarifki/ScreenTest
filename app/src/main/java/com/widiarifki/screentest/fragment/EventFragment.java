package com.widiarifki.screentest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.widiarifki.screentest.R;
import com.widiarifki.screentest.adapter.EventAdapter;
import com.widiarifki.screentest.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by widiarifki on 24/02/2018.
 */

public class EventFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        Context context = getContext();

        List<Event> eventList = new ArrayList<>();
        eventList.add(new Event("Liburan", "1 Feb 2018", R.mipmap.payung));
        eventList.add(new Event("Jalan-Jalan", "2 Feb 2018", R.mipmap.car));
        eventList.add(new Event("Ngegym", "3 Feb 2018", R.mipmap.gym));
        eventList.add(new Event("Ngopi", "4 Feb 2018", R.mipmap.kopi));

        EventAdapter adapter = new EventAdapter(context, eventList);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }
}
