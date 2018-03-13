package com.widiarifki.screentest.presentation.menu

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.widiarifki.screentest.MainActivity
import com.widiarifki.screentest.R
import com.widiarifki.screentest.presentation.event.EventFragment
import com.widiarifki.screentest.presentation.guest.GuestFragment

/**
 * Created by widiarifki on 27/02/2018.
 */

class MenuFragment : Fragment(), IMenuView, View.OnClickListener {

    private var mContext: Context? = null
    private var mMainActivity: MainActivity? = null
    private var menuPresenter: MenuPresenter? = null

    var txtName: TextView? = null
    var btnEvent: Button? = null
    var btnGuest: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context
        mMainActivity = mContext as MainActivity?
        mMainActivity!!.showActionBar(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_second, container, false)
        menuPresenter = MenuPresenter(this)

        txtName = view.findViewById(R.id.txtName) as TextView
        btnEvent = view.findViewById(R.id.btnEvent) as Button
        btnEvent?.setOnClickListener(this)
        btnGuest = view.findViewById(R.id.btnGuest) as Button
        btnGuest?.setOnClickListener(this)

        writeUserName("Nama: " + mMainActivity!!.name!!)

        val args = arguments
        //if (args != null) {
            if (args?.getString("EVENT") != null)
                menuPresenter!!.setEventName(args.getString("EVENT"))

            if (args?.getString("GUEST") != null)
                menuPresenter!!.setGuestName(args.getString("GUEST"))
        //}

        return view
    }


    override fun onClick(v: View) {
        val btn = v as Button
        when (btn.id) {
            R.id.btnEvent -> goToEvent()
            R.id.btnGuest -> goToGuest()
        }
    }

    override fun writeUserName(username: String?) {
        txtName?.text = username
    }

    override fun goToEvent() {
        mMainActivity!!.addStackedFragment(this, TITLE, EventFragment(), EventFragment.TITLE)
    }

    override fun goToGuest() {
        mMainActivity!!.addStackedFragment(this, TITLE, GuestFragment(), GuestFragment.TITLE)
    }

    override fun writeEvent(eventName: String?) {
        btnEvent?.text = "SELECTED EVENT: $eventName"
    }

    override fun writeGuest(guestName: String?) {
        btnGuest?.text = "SELECTED GUEST: $guestName"
    }

    companion object {
        var TITLE = "Menu"
    }
}
