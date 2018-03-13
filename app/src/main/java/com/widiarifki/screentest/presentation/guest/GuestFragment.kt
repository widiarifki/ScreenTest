package com.widiarifki.screentest.presentation.guest

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.widiarifki.screentest.R
import com.widiarifki.screentest.adapter.GuestAdapter
import com.widiarifki.screentest.model.Guest

/**
 * Created by widiarifki on 27/02/2018.
 */

class GuestFragment : Fragment(), IGuestView {

    internal var guestPresenter: GuestPresenter? = null
    internal var mContext: Context? = null
    internal var mContextActivity: Activity? = null

    internal var mRecyclerView: RecyclerView? = null
    internal var mRvSwipeLayout: SwipeRefreshLayout? = null
    internal var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context
        mContextActivity = mContext as Activity
        mProgressDialog = ProgressDialog(mContext)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_guest, container, false)
        guestPresenter = GuestPresenter(this)

        mRecyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        mRvSwipeLayout = view.findViewById(R.id.rvSwipeLayout) as SwipeRefreshLayout
        mRvSwipeLayout?.setOnRefreshListener { guestPresenter?.fetchGuestData() }

        guestPresenter?.fetchGuestData()
        return view
    }

    override fun showList(guestList: List<Guest>) {
        mContextActivity?.runOnUiThread {
            val adapter = GuestAdapter(mContext, guestList)
            if (mRecyclerView?.adapter == null) {
                mRecyclerView?.adapter = adapter
                mRecyclerView?.layoutManager = GridLayoutManager(mContext, 2)
            } else {
                mRecyclerView?.swapAdapter(adapter, false)
            }

            showProgressDialogue(false)
            if (mRvSwipeLayout!!.isRefreshing) {
                mRvSwipeLayout!!.isRefreshing = false
            }
        }
    }

    override fun showProgressDialogue(isShowing: Boolean) {
        if (mProgressDialog != null) {
            if (isShowing) {
                mProgressDialog!!.setMessage("Mengambil data..")
                mProgressDialog!!.show()
            } else {
                mProgressDialog!!.dismiss()
            }
        }
    }

    override fun showGetDataFail() {
        mContextActivity?.runOnUiThread {
            showProgressDialogue(false)
            showDialogue("Error", "Gagal terhubung ke server")
            if (mRvSwipeLayout!!.isRefreshing) {
                mRvSwipeLayout!!.isRefreshing = false
            }
        }
    }

    override fun showDialogue(title: String, info: String) {
        /*AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(info)
                .create()
                .show()*/

        mContext?.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(info)
                .create()
                .show()
        }
    }

    companion object {

        var TITLE = "Guest"
    }
}
