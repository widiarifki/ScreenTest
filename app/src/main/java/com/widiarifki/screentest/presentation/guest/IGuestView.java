package com.widiarifki.screentest.presentation.guest;

import com.widiarifki.screentest.model.Guest;

import java.util.List;

/**
 * Created by widiarifki on 27/02/2018.
 */

public interface IGuestView {
    void showList(List<Guest> guestList);
    void showGetDataFail();
    void showProgressDialogue(boolean isShowing);
    void showDialogue(String title, String info);
}
