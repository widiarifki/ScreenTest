package com.widiarifki.screentest.presentation.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.widiarifki.screentest.Constant
import com.widiarifki.screentest.R
import com.widiarifki.screentest.presentation.event.EventActivity
import com.widiarifki.screentest.presentation.guest.GuestActivity
import kotlinx.android.synthetic.main.fragment_second.*


/**
 * Created by widiarifki on 15/03/2018.
 */

class MenuActivity : AppCompatActivity(), IMenuView, View.OnClickListener {

    private val presenter = MenuPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_second)

        writeUserName("Username: " + presenter.getUsername())

        showDialogFromExtras(intent.extras.getString(Constant.extraTagDialogMsg))

        // Click Listener
        btnEvent.setOnClickListener(this)
        btnGuest.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.codeIntentEvent) {
                writeEvent(data?.getStringExtra(Constant.extraTagEventName))
            }
            else if (requestCode == Constant.codeIntentGuest) {
                writeGuest(data?.getStringExtra(Constant.extraTagGuestName))
                showDialogFromExtras(data?.getStringExtra(Constant.extraTagDialogMsg))
            }
        }
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

    override fun writeEvent(eventName: String?) {
        btnEvent?.text = "SELECTED EVENT: $eventName"
    }

    override fun writeGuest(guestName: String?) {
        btnGuest?.text = "SELECTED GUEST: $guestName"
    }

    override fun goToEvent() {
        val intent = Intent(this, EventActivity::class.java)
        startActivityForResult(intent, Constant.codeIntentEvent)
    }

    override fun goToGuest() {
        val intent = Intent(this, GuestActivity::class.java)
        startActivityForResult(intent, Constant.codeIntentGuest)
    }

    private fun showDialogFromExtras(message: String?) {
        if(message != null){
            AlertDialog.Builder(this)
                    .setMessage(message)
                    .create()
                    .show()
        }
    }
}
