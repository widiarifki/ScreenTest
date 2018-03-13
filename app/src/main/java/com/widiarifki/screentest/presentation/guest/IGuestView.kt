package com.widiarifki.screentest.presentation.guest

import com.widiarifki.screentest.model.Guest

/**
 * Created by widiarifki on 27/02/2018.
 */

interface IGuestView {
    fun showList(guestList: List<Guest>)
    fun showGetDataFail()
    fun showProgressDialogue(isShowing: Boolean)
    fun showDialogue(title: String, info: String)
}
