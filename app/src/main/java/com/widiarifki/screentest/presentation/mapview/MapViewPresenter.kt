package com.widiarifki.screentest.presentation.mapview

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import com.widiarifki.screentest.model.Event
import java.util.*

/**
 * Created by widiarifki on 27/02/2018.
 */

class MapViewPresenter(internal var mView: IMapView) {
    var eventFragmentList: List<Fragment>? = null
    var deviceScreen: String? = null
    lateinit var eventList: List<Event>

    fun initializeScreen(context: Context, savedInstaceState: Bundle?, eventList: List<Event>) {
        this.eventList = eventList
        // Create fragments for Event Icon/Image
        generateEventFragments(eventList)
        // Determine value for device screen
        setDeviceScreen(context)
        // View's role:
        if(eventFragmentList != null)
            mView.showHorizontalEventImg(eventFragmentList!!)

        mView.initializeMap(savedInstaceState)
    }

    // Create fragments for Event Icon/Image
    private fun generateEventFragments(eventList: List<Event>?) {
        val fragmentList = ArrayList<Fragment>()
        for (event in eventList.orEmpty()) {
            fragmentList.add(HorizontalImageFragment.newInstance(event.nama, event.imgResId))
        }
        this.eventFragmentList = fragmentList
    }

    // Action when on select event fragment
    fun selectEvent(position: Int) {
        eventList.let {
            val event = it[position]
            mView.updateMapBasedOnEvent(event)
        }

    }

    // Set value for device screen (to enable the proper view of horizontal scrollable)
    fun setDeviceScreen(context: Context?) {
        var value = 20
        var str = ""
        if (context!!.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            when (context.resources.displayMetrics.densityDpi) {
                DisplayMetrics.DENSITY_LOW -> {
                    str = "small-ldpi"
                    // Log.e("small 1","small-ldpi");
                    value = 20
                }
                DisplayMetrics.DENSITY_MEDIUM -> {
                    str = "small-mdpi"
                    // Log.e("small 1","small-mdpi");
                    value = 20
                }
                DisplayMetrics.DENSITY_HIGH -> {
                    str = "small-hdpi"
                    // Log.e("small 1","small-hdpi");
                    value = 20
                }
                DisplayMetrics.DENSITY_XHIGH -> {
                    str = "small-xhdpi"
                    // Log.e("small 1","small-xhdpi");
                    value = 20
                }
                DisplayMetrics.DENSITY_XXHIGH -> {
                    str = "small-xxhdpi"
                    // Log.e("small 1","small-xxhdpi");
                    value = 20
                }
                DisplayMetrics.DENSITY_XXXHIGH -> {
                    str = "small-xxxhdpi"
                    //Log.e("small 1","small-xxxhdpi");
                    value = 20
                }
                DisplayMetrics.DENSITY_TV -> {
                    str = "small-tvdpi"
                    // Log.e("small 1","small-tvdpi");
                    value = 20
                }
                else -> {
                    str = "small-unknown"
                    value = 20
                }
            }

        } else if (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            when (context.resources.displayMetrics.densityDpi) {
                DisplayMetrics.DENSITY_LOW -> {
                    str = "normal-ldpi"
                    // Log.e("normal-ldpi 1","normal-ldpi");
                    deviceScreen = "normal-ldpi"
                    value = 82
                }
                DisplayMetrics.DENSITY_MEDIUM -> {
                    // Log.e("normal-mdpi 1","normal-mdpi");
                    str = "normal-mdpi"
                    value = 82
                    deviceScreen = "normal-mdpi"
                }
                DisplayMetrics.DENSITY_HIGH -> {
                    // Log.e("normal-hdpi 1","normal-hdpi");
                    str = "normal-hdpi"
                    deviceScreen = "normal-hdpi"
                    value = 82
                }
                DisplayMetrics.DENSITY_XHIGH -> {
                    //Log.e("normal-xhdpi 1","normal-xhdpi");
                    str = "normal-xhdpi"
                    deviceScreen = "normal-xhdpi"
                    value = 90
                }
                DisplayMetrics.DENSITY_XXHIGH -> {
                    // Log.e("normal-xxhdpi 1","normal-xxhdpi");
                    str = "normal-xxhdpi"
                    deviceScreen = "normal-xxhdpi"
                    value = 96
                }
                DisplayMetrics.DENSITY_XXXHIGH -> {
                    //Log.e("normal-xxxhdpi","normal-xxxhdpi");
                    str = "normal-xxxhdpi"
                    deviceScreen = "normal-xxxhdpi"
                    value = 96
                }
                DisplayMetrics.DENSITY_TV -> {
                    //Log.e("DENSITY_TV 1","normal-mdpi");
                    str = "normal-tvdpi"
                    deviceScreen = "normal-tvmdpi"
                    value = 96
                }
                else -> {
                    // Log.e("normal-unknown","normal-unknown");
                    str = "normal-unknown"
                    deviceScreen = "normal-unknown"
                    value = 82
                }
            }
        } else if (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            when (context.resources.displayMetrics.densityDpi) {
                DisplayMetrics.DENSITY_LOW -> {
                    str = "large-ldpi"
                    // Log.e("large-ldpi 1","normal-ldpi");
                    value = 78
                }
                DisplayMetrics.DENSITY_MEDIUM -> {
                    str = "large-mdpi"
                    //Log.e("large-ldpi 1","normal-mdpi");
                    value = 78
                }
                DisplayMetrics.DENSITY_HIGH -> {
                    //Log.e("large-ldpi 1","normal-hdpi");
                    str = "large-hdpi"
                    value = 78
                }
                DisplayMetrics.DENSITY_XHIGH -> {
                    // Log.e("large-ldpi 1","normal-xhdpi");
                    str = "large-xhdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_XXHIGH -> {
                    //Log.e("large-ldpi 1","normal-xxhdpi");
                    str = "large-xxhdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_XXXHIGH -> {
                    // Log.e("large-ldpi 1","normal-xxxhdpi");
                    str = "large-xxxhdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_TV -> {
                    //Log.e("large-ldpi 1","normal-tvdpi");
                    str = "large-tvdpi"
                    value = 125
                }
                else -> {
                    str = "large-unknown"
                    value = 78
                }
            }

        } else if (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            when (context.resources.displayMetrics.densityDpi) {
                DisplayMetrics.DENSITY_LOW -> {
                    // Log.e("large-ldpi 1","normal-ldpi");
                    str = "xlarge-ldpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_MEDIUM -> {
                    // Log.e("large-ldpi 1","normal-mdpi");
                    str = "xlarge-mdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_HIGH -> {
                    //Log.e("large-ldpi 1","normal-hdpi");
                    str = "xlarge-hdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_XHIGH -> {
                    // Log.e("large-ldpi 1","normal-hdpi");
                    str = "xlarge-xhdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_XXHIGH -> {
                    // Log.e("large-ldpi 1","normal-xxhdpi");
                    str = "xlarge-xxhdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_XXXHIGH -> {
                    // Log.e("large-ldpi 1","normal-xxxhdpi");
                    str = "xlarge-xxxhdpi"
                    value = 125
                }
                DisplayMetrics.DENSITY_TV -> {
                    //Log.e("large-ldpi 1","normal-tvdpi");
                    str = "xlarge-tvdpi"
                    value = 125
                }
                else -> {
                    str = "xlarge-unknown"
                    value = 125
                }
            }
        }
    }
}
