package com.widiarifki.screentest.presentation.mapview

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.widiarifki.screentest.MainActivity
import com.widiarifki.screentest.R
import com.widiarifki.screentest.adapter.ImgPagerAdapter
import com.widiarifki.screentest.model.Event

/**
 * Created by widiarifki on 27/02/2018.
 */

class MapViewFragment : Fragment(), IMapView {
    internal var mContext: Context? = null
    internal var mainActivity: MainActivity? = null
    internal var mapViewPresenter: MapViewPresenter? = null

    private var mViewPager: ViewPager? = null
    private var mMapView: MapView? = null
    private var mGoogleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context
        mainActivity = mContext as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_mapview, container, false)
        mapViewPresenter = MapViewPresenter(this)

        mViewPager = view.findViewById(R.id.myviewpager) as ViewPager
        mMapView = view.findViewById(R.id.mapView) as MapView
        initializeMap(savedInstanceState)

        mapViewPresenter?.eventList = Event.dummyEvents()

        return view
    }

    override fun showHorizontalImgFragment(fragments: List<Fragment>) {
        mapViewPresenter?.setDeviceScreen(mContext)

        val adapter = ImgPagerAdapter(mainActivity?.supportFragmentManager, fragments)
        mViewPager!!.adapter = adapter
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {
                updateMapBasedOnEvent(mapViewPresenter!!.mEventList!![position])
            }
        })
        mViewPager!!.clipToPadding = false
        val deviceScreen = mapViewPresenter?.deviceScreen
        if (deviceScreen == "normal-hdpi") {
            mViewPager!!.setPadding(160, 0, 160, 0)
        } else if (deviceScreen == "normal-mdpi") {
            mViewPager!!.setPadding(160, 0, 160, 0)
        } else if (deviceScreen == "normal-xhdpi") {
            mViewPager!!.setPadding(160, 0, 160, 0)
        } else if (deviceScreen == "normal-xxhdpi") {
            mViewPager!!.setPadding(180, 0, 180, 0)
        } else if (deviceScreen == "normal-xxxhdpi") {
            mViewPager!!.setPadding(180, 0, 180, 0)
        } else if (deviceScreen == "normal-unknown") {
            mViewPager!!.setPadding(160, 0, 160, 0)
        } else {
        }

        mViewPager!!.setPageTransformer(true) { page, position ->
            var position = position
            val maxScale = 1.0f
            val minScale = 0.8f
            //position = if (position < -1) -1 else position
            position = if (position < -1) (-1).toFloat() else position
            //position = if (position > 1) 1 else position
            position = if (position > 1) (1).toFloat() else position

            val tempScale = if (position < 0) 1 + position else 1 - position
            val slope = (maxScale - minScale) / 1
            val scaleValue = minScale + tempScale * slope

            page.scaleX = scaleValue
            page.scaleY = scaleValue

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                page.parent.requestLayout()
            }
        }
    }

    override fun initializeMap(savedInstanceState: Bundle?) {
        if (mMapView != null) {
            mMapView!!.onCreate(savedInstanceState)
            mMapView!!.onResume()
            try {
                MapsInitializer.initialize(activity.applicationContext)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            mMapView!!.getMapAsync { mMap ->
                mGoogleMap = mMap
                val posImage = mViewPager!!.currentItem
                updateMapBasedOnEvent(mapViewPresenter!!.mEventList!![posImage])
            }

            mapViewPresenter?.eventList = Event.dummyEvents()
            mapViewPresenter?.setDeviceScreen(mContext)
        }
    }

    override fun updateMapBasedOnEvent(event: Event) {
        mGoogleMap!!.clear()

        val koord = event.latLong!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val latStr = koord[0]
        val longStr = koord[1]
        val latVal = java.lang.Float.valueOf(latStr)!!
        val longVal = java.lang.Float.valueOf(longStr)!!
        val place = LatLng(latVal.toDouble(), longVal.toDouble())
        mGoogleMap!!.addMarker(MarkerOptions().position(place)
                .title(event.nama)
                .snippet(event.latLong)
        ).showInfoWindow()

        val builder = LatLngBounds.Builder()
        builder.include(place)

        val bounds = builder.build()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, 175)
        mGoogleMap!!.moveCamera(cu)
        mGoogleMap!!.animateCamera(CameraUpdateFactory.zoomTo(12.0f))
    }

    companion object {

        var TITLE = "Map View"
    }
}
