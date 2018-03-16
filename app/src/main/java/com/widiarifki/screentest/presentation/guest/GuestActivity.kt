package com.widiarifki.screentest.presentation.guest

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.widiarifki.screentest.R
import com.widiarifki.screentest.adapter.GuestAdapter
import com.widiarifki.screentest.model.Guest
import kotlinx.android.synthetic.main.fragment_guest.*


/**
 * Created by widiarifki on 15/03/2018.
 */

class GuestActivity : AppCompatActivity(), IGuestView, SwipeRefreshLayout.OnRefreshListener {

    val presenter: GuestPresenter = GuestPresenter(this)

    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_guest)

        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setMessage("Mengambil data..")

        if(!mProgressDialog.isShowing) mProgressDialog.show()
        presenter.fetchGuestData()
    }

    override fun onRefresh() {
        presenter.fetchGuestData()
    }

    override fun showList(guestList: List<Guest>) {
        val mAdapter = GuestAdapter(this, guestList)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?

        // Turn off swipe refresh
        if(rvSwipeLayout.isRefreshing)
            rvSwipeLayout.isRefreshing = false;

        if(mProgressDialog.isShowing) mProgressDialog.dismiss()
    }

    override fun showDialogMessage(title: String?, info: String) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(info)
                .create()
                .show()
    }
}
