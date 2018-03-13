package com.widiarifki.screentest

//import com.widiarifki.screentest.fragment.HomeFragment;
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.widiarifki.screentest.presentation.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private var mFragmentManager: FragmentManager? = null
    var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFragmentManager = supportFragmentManager
        mFragmentManager!!.addOnBackStackChangedListener {
            if (mFragmentManager!!.backStackEntryCount > 0) {
                if (supportActionBar != null)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true) // show back button
            } else {
                if (supportActionBar != null)
                    supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }
        }

        setFragment(HomeFragment())
    }

    fun setFragment(fragment: Fragment) {
        resetStack()
        mFragmentManager!!.beginTransaction()
                .replace(R.id.content_layout, fragment)
                .commit()
    }

    fun setFragment(fragment: Fragment, fragmentTitle: String?) {
        resetStack()
        mFragmentManager!!.beginTransaction()
                .replace(R.id.content_layout, fragment)
                .commit()
        if (fragmentTitle != null) title = fragmentTitle
    }

    fun resetStack() {
        // reset stack
        val fragmentCount = mFragmentManager!!.backStackEntryCount
        if (fragmentCount > 0) {
            for (i in 0..fragmentCount) {
                mFragmentManager!!.popBackStack()
            }
        }
    }

    fun addStackedFragment(stackedFragment: Fragment, stackedFragmentTitle: String, newFragment: Fragment, newFragmentTitle: String?) {
        mFragmentManager!!.beginTransaction()
                .hide(stackedFragment)
                .add(R.id.content_layout, newFragment, newFragmentTitle)
                .addToBackStack(stackedFragmentTitle)
                .commit()

        if (newFragmentTitle != null) title = newFragmentTitle
    }

    override fun onBackPressed() {
        // Check fragment back stack so we can set the right action bar title
        val fragmentCount = mFragmentManager!!.backStackEntryCount
        if (fragmentCount > 0) {
            val backEntry = mFragmentManager!!.getBackStackEntryAt(fragmentCount - 1)
            val fragmentTag = backEntry.name
            if (fragmentTag != null) {
                title = fragmentTag
            }
        }
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun showActionBar(isShow: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            if (isShow)
                actionBar.show()
            else
                actionBar.hide()
        }
    }
}