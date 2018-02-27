package com.widiarifki.screentest.presentation.menu;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class MenuPresenter {
    IMenuView mView;
    String mEventName;
    String mGuestName;

    public MenuPresenter(IMenuView view) {
        mView = view;
    }

    public void setEventName(String eventName) {
        mEventName = eventName;
        mView.writeEvent(mEventName);
    }

    public void setGuestName(String guestName) {
        mGuestName = guestName;
        mView.writeGuest(mGuestName);
    }
}
