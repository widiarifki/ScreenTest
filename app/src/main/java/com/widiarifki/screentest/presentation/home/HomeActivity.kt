package com.widiarifki.screentest.presentation.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.widiarifki.screentest.Constant
import com.widiarifki.screentest.R
import com.widiarifki.screentest.presentation.menu.MenuActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by widiarifki on 15/03/2018.
 */

open class HomeActivity : AppCompatActivity(), IHomeView, View.OnClickListener {

    private val presenter = HomePresenter(this)
    var inputNameVal: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        // Hide actionbar
        supportActionBar.let { it?.hide() }
        supportActionBar?.hide()

        // On btnEnter Click
        btnEnter.setOnClickListener{
            inputNameVal = inputName.text.toString()
            presenter.saveUserData(inputNameVal!!)
            goToMenu()
        }
    }

    override fun onClick(v: View) {
        val btn = v as Button
        when (btn.id) {
            R.id.btnEnter -> goToMenu()
        }
    }

    override fun goToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra(Constant.extraTagDialogMsg, presenter.getPalindromeMessage(inputNameVal.toString()))
        startActivity(intent)
    }

}
