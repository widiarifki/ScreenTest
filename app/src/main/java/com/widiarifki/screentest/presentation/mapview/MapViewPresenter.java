package com.widiarifki.screentest.presentation.mapview;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;

import com.widiarifki.screentest.fragment.ImageSlideFragment;
import com.widiarifki.screentest.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class MapViewPresenter {
    IMapView mView;
    List<Event> mEventList;
    String mDeviceScreen;

    public MapViewPresenter(IMapView view) {
        mView = view;
    }

    public void setEventList(List<Event> eventList) {
        mEventList = eventList;
        List<Fragment> fragments = getFragmentListFormEvent(mEventList);
        mView.showHorizontalImgFragment(fragments);
    }

    public List<Event> getEventList() {
        return mEventList;
    }

    private List<Fragment> getFragmentListFormEvent(List<Event> eventList) {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        for(Event event : mEventList){
            fragmentList.add(ImageSlideFragment.newInstance(event.getNama(), event.getImgResId()));
        }
        return fragmentList;
    }

    public void selectEvent(Event event){
        mView.updateMapBasedOnEvent(event);
    }
    
    public void setDeviceScreen(Context context){
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
                    mDeviceScreen = "normal-ldpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_MEDIUM:
                    // Log.e("normal-mdpi 1","normal-mdpi");
                    str = "normal-mdpi";
                    value = 82;
                    mDeviceScreen = "normal-mdpi";
                    break;
                case DisplayMetrics.DENSITY_HIGH:
                    // Log.e("normal-hdpi 1","normal-hdpi");
                    str = "normal-hdpi";
                    mDeviceScreen = "normal-hdpi";
                    value = 82;
                    break;
                case DisplayMetrics.DENSITY_XHIGH:
                    //Log.e("normal-xhdpi 1","normal-xhdpi");
                    str = "normal-xhdpi";
                    mDeviceScreen = "normal-xhdpi";
                    value = 90;
                    break;
                case DisplayMetrics.DENSITY_XXHIGH:
                    // Log.e("normal-xxhdpi 1","normal-xxhdpi");
                    str = "normal-xxhdpi";
                    mDeviceScreen = "normal-xxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_XXXHIGH:
                    //Log.e("normal-xxxhdpi","normal-xxxhdpi");
                    str = "normal-xxxhdpi";
                    mDeviceScreen = "normal-xxxhdpi";
                    value = 96;
                    break;
                case DisplayMetrics.DENSITY_TV:
                    //Log.e("DENSITY_TV 1","normal-mdpi");
                    str = "normal-tvdpi";
                    mDeviceScreen = "normal-tvmdpi";
                    value = 96;
                    break;
                default:
                    // Log.e("normal-unknown","normal-unknown");
                    str = "normal-unknown";
                    mDeviceScreen = "normal-unknown";
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
    }

    public String getDeviceScreen() {
        return mDeviceScreen;
    }
}
