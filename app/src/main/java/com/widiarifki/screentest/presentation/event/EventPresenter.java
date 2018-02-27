package com.widiarifki.screentest.presentation.event;

import com.widiarifki.screentest.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class EventPresenter {

    IEventView mView;
    List<Event> mEventList;

    public EventPresenter(IEventView view){
        mView = view;
        mEventList = new ArrayList<>();
    }

    public void setEventList(List<Event> eventList) {
        mEventList = eventList;
        mView.showList(mEventList);
    }
}
