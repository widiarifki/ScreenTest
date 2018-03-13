package com.widiarifki.screentest.presentation.menu

/**
 * Created by widiarifki on 27/02/2018.
 */

interface IMenuView {
    fun writeUserName(username: String?)
    fun goToEvent()
    fun goToGuest()
    fun writeEvent(eventName: String?)
    fun writeGuest(guestName: String?)
}
