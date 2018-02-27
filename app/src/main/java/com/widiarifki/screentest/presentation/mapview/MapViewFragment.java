package com.widiarifki.screentest.presentation.mapview;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.widiarifki.screentest.MainActivity;
import com.widiarifki.screentest.R;
import com.widiarifki.screentest.adapter.ImgPagerAdapter;
import com.widiarifki.screentest.model.Event;

import java.util.List;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class MapViewFragment extends Fragment implements IMapView {

    public static String TITLE = "Map View";
    Context mContext;
    MainActivity mainActivity;
    MapViewPresenter mapViewPresenter;

    private ViewPager mViewPager;
    private MapView mMapView;
    private GoogleMap mGoogleMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mainActivity = (MainActivity)mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapview, container, false);
        mapViewPresenter = new MapViewPresenter(this);
        
        mViewPager = (ViewPager) view.findViewById(R.id.myviewpager);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        initializeMap(savedInstanceState);

        mapViewPresenter.setEventList(Event.dummyEvents());

        return view;
    }

    @Override
    public void showHorizontalImgFragment(List<Fragment> fragments) {
        mapViewPresenter.setDeviceScreen(mContext);

        PagerAdapter adapter = new ImgPagerAdapter(mainActivity.getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageScrollStateChanged(int state) {}

            public void onPageSelected(int position) {
                updateMapBasedOnEvent(mapViewPresenter.mEventList.get(position));
            }
        });
        mViewPager.setClipToPadding(false);
        String deviceScreen = mapViewPresenter.getDeviceScreen();
        if (deviceScreen.equals("normal-hdpi")){
            mViewPager.setPadding(160, 0, 160, 0);
        }else if (deviceScreen.equals("normal-mdpi")){
            mViewPager.setPadding(160, 0, 160, 0);
        }else if (deviceScreen.equals("normal-xhdpi")){
            mViewPager.setPadding(160, 0, 160, 0);
        }else if (deviceScreen.equals("normal-xxhdpi")){
            mViewPager.setPadding(180, 0, 180, 0);
        }else if (deviceScreen.equals("normal-xxxhdpi")){
            mViewPager.setPadding(180, 0, 180, 0);
        }else if (deviceScreen.equals("normal-unknown")){
            mViewPager.setPadding(160, 0, 160, 0);
        }else {}

        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float maxScale = 1.0f;
                float minScale = 0.8f;
                position = position < -1 ? -1 : position;
                position = position > 1 ? 1 : position;

                float tempScale = position < 0 ? 1 + position : 1 - position;
                float slope = (maxScale - minScale) / 1;
                float scaleValue = minScale + tempScale * slope;

                page.setScaleX(scaleValue);
                page.setScaleY(scaleValue);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    page.getParent().requestLayout();
                }
            }
        });
    }

    @Override
    public void initializeMap(Bundle savedInstanceState) {
        if(mMapView != null){
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume();
            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    mGoogleMap = mMap;
                    int posImage = mViewPager.getCurrentItem();
                    updateMapBasedOnEvent(mapViewPresenter.mEventList.get(posImage));
                }
            });

            mapViewPresenter.setEventList(Event.dummyEvents());
            mapViewPresenter.setDeviceScreen(mContext);
        }
    }

    @Override
    public void updateMapBasedOnEvent(Event event) {
        mGoogleMap.clear();

        String[] koord = event.getLatLong().split(",");
        String latStr = koord[0];
        String longStr = koord[1];
        float latVal = Float.valueOf(latStr);
        float longVal = Float.valueOf(longStr);
        LatLng place = new LatLng(latVal, longVal);
        mGoogleMap.addMarker(new MarkerOptions().position(place)
                .title(event.getNama())
                .snippet(event.getLatLong())
        ).showInfoWindow();

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(place);

        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 175);
        mGoogleMap.moveCamera(cu);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
    }
}
