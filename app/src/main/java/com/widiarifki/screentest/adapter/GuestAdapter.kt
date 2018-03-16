package com.widiarifki.screentest.adapter

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widiarifki.screentest.Constant
import com.widiarifki.screentest.R
import com.widiarifki.screentest.model.Guest
import kotlinx.android.synthetic.main.item_list_guest.view.*

/**
 * Created by widiarifki on 24/02/2018.
 */

class GuestAdapter(internal var mContext: Context?, internal var mObjects: List<Guest>) : RecyclerView.Adapter<GuestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_list_guest, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val guestData = mObjects[position]
        holder.bind(guestData, position)
    }

    override fun getItemCount(): Int = mObjects.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public fun bind(guest: Guest, position: Int){
            itemView.txtName.text = guest.name
            itemView.txtBirthDate.text = guest.birthdate

            // Check whether month prime/not
            val month = getMonth(guest.birthdate)
            itemView.txtIsPrime.text = "Month Number: " + if (isPrime(month)) "PRIME" else "NOT PRIME"

            // Click listener on item
            itemView.setOnClickListener{v ->
                val date = getDate(guest.birthdate)

                val intent = Intent()
                intent.putExtra(Constant.extraTagGuestName, guest.name)
                intent.putExtra(Constant.extraTagDialogMsg, displayTextBasedOnDate(date))
                (mContext as Activity).setResult(RESULT_OK, intent)
                (mContext as Activity).finish()
            }
        }
    }

    fun getDate(date: String?): Int {
        val birthDateSplit = date?.split("-".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        return Integer.parseInt(birthDateSplit?.get(2))
    }

    fun getMonth(date: String?): Int {
        val birthDateSplit = date?.split("-".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        return Integer.parseInt(birthDateSplit?.get(1))
    }

    fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        for (i in 2 until n)
            if (n % i == 0)
                return false
        return true
    }

    fun displayTextBasedOnDate(date: Int): String {
        var displayedTxt = ""
        if (date % 2 == 0 && date % 3 == 0)
            displayedTxt = "iOS"
        else if (date % 2 == 0)
            displayedTxt = "Blackberry"
        else if (date % 3 == 0)
            displayedTxt = "Android"
        else
            displayedTxt = "Featured Phone"

        return displayedTxt
    }
}
