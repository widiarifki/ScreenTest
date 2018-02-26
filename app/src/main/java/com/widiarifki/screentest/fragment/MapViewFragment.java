package com.widiarifki.screentest.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by widiarifki on 26/02/2018.
 */

public class MapViewFragment extends Fragment {

    public static String TITLE = "Map View";
    Context mContext;
    MainActivity mainActivity;
    String strDevice;
    MapView mMapView;
    GoogleMap mGoogleMap;
    List<Event> mEventList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mainActivity = (MainActivity)mContext;
        mEventList = Event.dummyEvents();
        differentDensityAndScreenSize(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapview, container, false);
        List<Fragment> fragments = imageFragmentList();
        PagerAdapter adapter = new ImgPagerAdapter(mainActivity.getSupportFragmentManager(), fragments);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.myviewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            public void onPageSelected(int position) {
                updateMap(mEventList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setClipToPadding(false);
        if (strDevice.equals("normal-hdpi")){
            viewPager.setPadding(160, 0, 160, 0);
        }else if (strDevice.equals("normal-mdpi")){
            viewPager.setPadding(160, 0, 160, 0);
        }else if (strDevice.equals("normal-xhdpi")){
            viewPager.setPadding(160, 0, 160, 0);
        }else if (strDevice.equals("normal-xxhdpi")){
            viewPager.setPadding(180, 0, 180, 0);
        }else if (strDevice.equals("normal-xxxhdpi")){
            viewPager.setPadding(180, 0, 180, 0);
        }else if (strDevice.equals("normal-unknown")){
            viewPager.setPadding(160, 0, 160, 0);
        }else {}
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
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

        /** Maps **/
        mMapView = (MapView) view.findViewById(R.id.mapView);
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
                int posImage = viewPager.getCurrentItem();
                updateMap(mEventList.get(posImage));
            }
        });

        return view;
    }

    private List<Fragment> imageFragmentList() {
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        for(Event event : mEventList){
            fragmentList.add(ImageSlideFragment.newInstance(event.getNama(), event.getImgResId()));
        }

        return fragmentList;
    }

    void updateMap(Event event){
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

    public int differentDensityAndScreenSize(Context context) {
        int value = 20;
        String str = "";
        if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    str = "small-ldpi";
                    // Log.e("small 1","small-ldpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    str = "small-mdpi";
                    // Log.e("small 1","small-mdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    str = "small-hdpi";
                    // Log.e("small 1","small-hdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    str = "small-xhdpi";
                    // Log.e("small 1","small-xhdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    str = "small-xxhdpi";
                    // Log.e("small 1","small-xxhdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    str = "small-xxxhdpi";
                    //Log.e("small 1","small-xxxhdpi");
                    value = 20;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    str = "small-tvdpi";
                    // Log.e("small 1","small-tvdpi");
                    value = 20;
                    break;
                default:
                    str = "small-unknown";
                    value = 20;
                    break;
            }

        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    str = "normal-ldpi";
                    // Log.e("normal-ldpi 1","normal-ldpi");
                    strDevice = "normal-ldpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    // Log.e("normal-mdpi 1","normal-mdpi");
                    str = "normal-mdpi";
                    value = 82;
                    strDevice = "normal-mdpi";
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    // Log.e("normal-hdpi 1","normal-hdpi");
                    str = "normal-hdpi";
                    strDevice = "normal-hdpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    //Log.e("normal-xhdpi 1","normal-xhdpi");
                    str = "normal-xhdpi";
                    strDevice = "normal-xhdpi";
                    value = 90;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    // Log.e("normal-xxhdpi 1","normal-xxhdpi");
                    str = "normal-xxhdpi";
                    strDevice = "normal-xxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    //Log.e("normal-xxxhdpi","normal-xxxhdpi");
                    str = "normal-xxxhdpi";
                    strDevice = "normal-xxxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    //Log.e("DENSITY_TV 1","normal-mdpi");
                    str = "normal-tvdpi";
                    strDevice = "normal-tvmdpi";
                    value = 96;
                    break;
                default:
                    // Log.e("normal-unknown","normal-unknown");
                    str = "normal-unknown";
                    strDevice = "normal-unknown";
                    value = 82;
                    break;
            }
        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    str = "large-ldpi";
                    // Log.e("large-ldpi 1","normal-ldpi");
                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    str = "large-mdpi";
                    //Log.e("large-ldpi 1","normal-mdpi");
                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    //Log.e("large-ldpi 1","normal-hdpi");
                    str = "large-hdpi";
                    value = 78;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    // Log.e("large-ldpi 1","normal-xhdpi");
                    str = "large-xhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    //Log.e("large-ldpi 1","normal-xxhdpi");
                    str = "large-xxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    // Log.e("large-ldpi 1","normal-xxxhdpi");
                    str = "large-xxxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    //Log.e("large-ldpi 1","normal-tvdpi");
                    str = "large-tvdpi";
                    value = 125;
                    break;
                default:
                    str = "large-unknown";
                    value = 78;
                    break;
            }

        } else if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            switch (context.getResources().getDisplayMetrics().densityDpi) {
                case DisplayMetrics.DENSITY_LOW:
                    // Log.e("large-ldpi 1","normal-ldpi");
                    str = "xlarge-ldpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    // Log.e("large-ldpi 1","normal-mdpi");
                    str = "xlarge-mdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    //Log.e("large-ldpi 1","normal-hdpi");
                    str = "xlarge-hdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    // Log.e("large-ldpi 1","normal-hdpi");
                    str = "xlarge-xhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    // Log.e("large-ldpi 1","normal-xxhdpi");
                    str = "xlarge-xxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    // Log.e("large-ldpi 1","normal-xxxhdpi");
                    str = "xlarge-xxxhdpi";
                    value = 125;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    //Log.e("large-ldpi 1","normal-tvdpi");
                    str = "xlarge-tvdpi";
                    value = 125;
                    break;
                default:
                    str = "xlarge-unknown";
                    value = 125;
                    break;
            }
        }

        return value;
    }
}
