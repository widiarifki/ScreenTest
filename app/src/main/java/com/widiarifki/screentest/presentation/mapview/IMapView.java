package com.widiarifki.screentest.presentation.mapview;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.widiarifki.screentest.model.Event;

import java.util.List;
/**
 * Created by widiarifki on 27/02/2018.
 */

public interface IMapView {
    void showHorizontalImgFragment(List<Fragment> eventList);
    void initializeMap(Bundle savedInstanceState);
    void updateMapBasedOnEvent(Event event);
}
