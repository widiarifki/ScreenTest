package com.widiarifki.screentest.presentation.event;

import com.widiarifki.screentest.model.Event;

import java.util.List;

/**
 * Created by widiarifki on 27/02/2018.
 */

public interface IEventView {
    void showList(List<Event> eventList);
    void goToMapview();
}
