package com.widiarifki.screentest.presentation.menu;

/**
 * Created by widiarifki on 27/02/2018.
 */

public interface IMenuView {
    void writeUserName(String username);
    void goToEvent();
    void goToGuest();
    void writeEvent(String eventName);
    void writeGuest(String guestName);
}
