package com.widiarifki.screentest.presentation.menu

/**
 * Created by widiarifki on 27/02/2018.
 */

class MenuPresenter(internal var mView: IMenuView) {
    internal var mEventName: String? = null
    internal var mGuestName: String? = null

    fun setEventName(eventName: String) {
        mEventName = eventName
        mView.writeEvent(mEventName)
    }

    fun setGuestName(guestName: String) {
        mGuestName = guestName
        mView.writeGuest(mGuestName)
    }
}
