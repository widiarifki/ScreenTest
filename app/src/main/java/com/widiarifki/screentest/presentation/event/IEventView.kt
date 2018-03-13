package com.widiarifki.screentest.presentation.event

import com.widiarifki.screentest.model.Event

/**
 * Created by widiarifki on 27/02/2018.
 */

interface IEventView {
    fun showList(eventList: List<Event>)
    fun goToMapview()
}
