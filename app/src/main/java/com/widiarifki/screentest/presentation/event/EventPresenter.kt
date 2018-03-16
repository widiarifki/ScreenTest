package com.widiarifki.screentest.presentation.event

import com.widiarifki.screentest.model.Event
import java.util.*

/**
 * Created by widiarifki on 27/02/2018.
 */

class EventPresenter(internal var mView: IEventView) {
    internal var mEventList: List<Event> = ArrayList()

    fun setEventList(eventList: List<Event>) {
        mEventList = eventList
        mView.showList(mEventList)
    }
}
