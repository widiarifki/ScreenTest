package com.widiarifki.screentest.adapter

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.widiarifki.screentest.MainActivity
import com.widiarifki.screentest.R
import com.widiarifki.screentest.model.Guest
import com.widiarifki.screentest.presentation.menu.MenuFragment

/**
 * Created by widiarifki on 24/02/2018.
 */

class GuestAdapter(internal var mContext: Context?, internal var mObjects: List<Guest>) : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder? {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_list_guest, null)
        view.setOnClickListener { v ->
            val position = (parent as RecyclerView).getChildLayoutPosition(v)
            val guestData = mObjects[position]

            // Conditional Display
            val birthDateSplit = guestData.birthdate!!.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val date = Integer.parseInt(birthDateSplit[2])

            var displayedTxt = ""
            if (date % 2 == 0 && date % 3 == 0)
                displayedTxt = "iOS"
            else if (date % 2 == 0)
                displayedTxt = "Blackberry"
            else if (date % 3 == 0)
                displayedTxt = "Android"
            else
                displayedTxt = "Featured Phone"

            mContext?.let { AlertDialog.Builder(it).setMessage(displayedTxt).create().show() }

            // Change Fragment
            val args = Bundle()
            args.putString("GUEST", guestData.name)

            val secondFrag = MenuFragment()
            secondFrag.arguments = args
            (mContext as MainActivity).setFragment(secondFrag, MenuFragment.TITLE)
        }
        return GuestViewHolder(view)
    }

    /*override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = mObjects[position]
        val viewHolder = holder as GuestViewHolder
        viewHolder.txtName.text = dataItem.name
        viewHolder.txtBirthDate.text = dataItem.birthdate
        // Check whether month prime/not
        val birthDateSplit = dataItem.birthdate.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val month = Integer.parseInt(birthDateSplit[1])
        viewHolder.txtIsPrime.text = "Month Number: " + if (isPrime(month)) "PRIME" else "NOT PRIME"
    }*/

    override fun onBindViewHolder(viewHolder: GuestViewHolder?, position: Int) {
        val dataItem = mObjects[position]
        viewHolder!!.txtName.text = dataItem.name
        viewHolder.txtBirthDate.text = dataItem.birthdate
        // Check whether month prime/not
        val birthDateSplit = dataItem.birthdate!!.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val month = Integer.parseInt(birthDateSplit[1])
        viewHolder.txtIsPrime.text = "Month Number: " + if (isPrime(month)) "PRIME" else "NOT PRIME"
    }

    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2 until n)
            if (n % i == 0)
                return false
        return true
    }

    override fun getItemCount(): Int {
        return mObjects.size
    }

    inner class GuestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtName: TextView
        var txtBirthDate: TextView
        var txtIsPrime: TextView

        init {
            txtName = view.findViewById(R.id.txtName) as TextView
            txtBirthDate = view.findViewById(R.id.txtBirthDate) as TextView
            txtIsPrime = view.findViewById(R.id.txtIsPrime) as TextView
        }
    }
}
