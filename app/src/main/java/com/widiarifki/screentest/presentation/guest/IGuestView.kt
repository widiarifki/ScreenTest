package com.widiarifki.screentest.presentation.guest

import com.widiarifki.screentest.model.Guest

/**
 * Created by widiarifki on 27/02/2018.
 */

interface IGuestView {
    fun showList(guestList: List<Guest>)
    fun showDialogMessage(title: String?, info: String)
}
