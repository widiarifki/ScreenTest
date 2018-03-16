package com.widiarifki.screentest.presentation.mapview

import android.os.Bundle
import android.support.v4.app.Fragment

import com.widiarifki.screentest.model.Event

/**
 * Created by widiarifki on 27/02/2018.
 */

interface IMapView {
    fun showHorizontalEventImg(eventFragmentList: List<Fragment>)
    fun initializeMap(savedInstanceState: Bundle?)
    fun updateMapBasedOnEvent(event: Event)
}
