package com.widiarifki.screentest.adapter

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.widiarifki.screentest.MainActivity
import com.widiarifki.screentest.R
import com.widiarifki.screentest.model.Event
import com.widiarifki.screentest.presentation.menu.MenuFragment

/**
 * Created by widiarifki on 24/02/2018.
 */

class EventAdapter(internal var mContext: Context?, internal var mObjects: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder? {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_list_event, null)
        view.setOnClickListener { v ->
            val position = (parent as RecyclerView).getChildLayoutPosition(v)
            val eventData = mObjects[position]

            val args = Bundle()
            args.putString("EVENT", eventData.nama)
            val secondFrag = MenuFragment()
            secondFrag.arguments = args
            (mContext as MainActivity).setFragment(secondFrag, MenuFragment.TITLE)
        }
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder?, position: Int) {
        val dataItem = mObjects[position]
        holder!!.txtEvent.text = dataItem.nama
        holder.txtEventDate.text = dataItem.tanggal
        holder.txtDesc.text = dataItem.desc
        holder.imgEvent.setImageResource(dataItem.imgResId)
    }

    /*override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val dataItem = mObjects[position]
        val viewHolder = holder as EventViewHolder
        viewHolder.txtEvent.text = dataItem.nama
        viewHolder.txtEventDate.text = dataItem.tanggal
        viewHolder.txtDesc.text = dataItem.desc
        viewHolder.imgEvent.setImageResource(dataItem.imgResId)
    }*/

    override fun getItemCount(): Int {
        return mObjects.size
    }

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var imgEvent: ImageView
        internal var txtEvent: TextView
        internal var txtEventDate: TextView
        internal var txtDesc: TextView

        init {
            imgEvent = view.findViewById(R.id.imgEvent) as ImageView
            txtEvent = view.findViewById(R.id.txtEvent) as TextView
            txtEventDate = view.findViewById(R.id.txtEventDate) as TextView
            txtDesc = view.findViewById(R.id.txtDesc) as TextView
        }
    }
}
