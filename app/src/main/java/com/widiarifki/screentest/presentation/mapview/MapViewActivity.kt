package com.widiarifki.screentest.presentation.mapview

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.widiarifki.screentest.R
import com.widiarifki.screentest.adapter.ImgPagerAdapter
import com.widiarifki.screentest.model.Event
import kotlinx.android.synthetic.main.fragment_mapview.*


/**
 * Created by widiarifki on 15/03/2018.
 */

class MapViewActivity : AppCompatActivity(), IMapView, OnMapReadyCallback {

    var presenter: MapViewPresenter = MapViewPresenter(this)

    private var mGoogleMap: GoogleMap? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mapview)

        presenter.initializeScreen(this.applicationContext, savedInstanceState, Event.dummyEvents());
    }

    override fun showHorizontalEventImg(eventFragmentList: List<Fragment>) {
        val adapter = ImgPagerAdapter(this.supportFragmentManager, eventFragmentList)
        myviewpager.adapter = adapter
        myviewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(fragmentPosition: Int) {
                presenter.selectEvent(fragmentPosition)
            }
        })
        myviewpager.clipToPadding = false

        val deviceScreen = presenter.deviceScreen
        if (deviceScreen == "normal-hdpi") {
            myviewpager.setPadding(160, 0, 160, 0)
        } else if (deviceScreen == "normal-mdpi") {
            myviewpager.setPadding(160, 0, 160, 0)
        } else if (deviceScreen == "normal-xhdpi") {
            myviewpager.setPadding(160, 0, 160, 0)
        } else if (deviceScreen == "normal-xxhdpi") {
            myviewpager.setPadding(180, 0, 180, 0)
        } else if (deviceScreen == "normal-xxxhdpi") {
            myviewpager.setPadding(180, 0, 180, 0)
        } else if (deviceScreen == "normal-unknown") {
            myviewpager.setPadding(160, 0, 160, 0)
        } else {
        }

        myviewpager.setPageTransformer(true) { page, position ->
            var pagerPosition = position
            val maxScale = 1.0f
            val minScale = 0.8f
            pagerPosition = if (pagerPosition < -1) (-1).toFloat() else pagerPosition
            pagerPosition = if (pagerPosition > 1) (1).toFloat() else pagerPosition

            val tempScale = if (pagerPosition < 0) 1 + pagerPosition else 1 - pagerPosition
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
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        mGoogleMap = map
        presenter.selectEvent(myviewpager.currentItem)
    }

    override fun updateMapBasedOnEvent(event: Event) {
        mGoogleMap?.clear()

        val latLong = event.latLong
        val koord = latLong?.split(",")?.toTypedArray()
        val latitude = koord?.get(0)?.toDouble()
        val longitude = koord?.get(1)?.toDouble()

        if(latitude != null && longitude != null){
            var place: LatLng? = null
            place = LatLng(latitude, longitude)
            mGoogleMap?.addMarker(
                    MarkerOptions()
                            .position(place)
                            .title(event.nama)
                            .snippet(event.latLong)
            )?.showInfoWindow()
            mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(place))
            mGoogleMap?.animateCamera(CameraUpdateFactory.zoomTo(12.0f))
        }

    }
}
