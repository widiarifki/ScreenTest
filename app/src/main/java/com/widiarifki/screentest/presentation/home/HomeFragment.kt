package com.widiarifki.screentest.presentation.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.widiarifki.screentest.MainActivity
import com.widiarifki.screentest.R
import com.widiarifki.screentest.presentation.menu.MenuFragment

/**
 * Created by widiarifki on 27/02/2018.
 */

class HomeFragment : Fragment(), IHomeView {

    private var homePresenter: HomePresenter? = null
    private var mContext: Context? = null
    private var mMainActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context
        mMainActivity = mContext as MainActivity?
        mMainActivity!!.showActionBar(false)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)
        homePresenter = HomePresenter(this)

        val inputName = view.findViewById(R.id.inputName) as EditText
        val btnEnter = view.findViewById(R.id.btnEnter) as Button
        btnEnter.setOnClickListener {
            val name = inputName.text.toString()
            mMainActivity!!.name = name
            homePresenter!!.checkPalindrome(name)
            goToMenu()
        }

        return view
    }

    override fun showDialogue(title: String, info: String) {
        AlertDialog.Builder(mContext!!)
                .setTitle(title)
                .setMessage(info)
                .create()
                .show()
    }

    override fun goToMenu() {
        mMainActivity!!.setFragment(MenuFragment(), MenuFragment.TITLE)
    }
}
