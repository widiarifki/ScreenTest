package com.widiarifki.screentest.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.widiarifki.screentest.Constant
import com.widiarifki.screentest.R
import com.widiarifki.screentest.model.Event
import kotlinx.android.synthetic.main.item_list_event.view.*

/**
 * Created by widiarifki on 24/02/2018.
 */

class EventAdapter(internal var mContext: Context?, internal var mObjects: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder? {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_list_event, null)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder?, position: Int) {
        val dataItem = mObjects[position]
        holder?.bind(dataItem, position)
    }

    override fun getItemCount(): Int {
        return mObjects.size
    }

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public fun bind(event: Event, position: Int){
            itemView.txtEvent.text = event.nama
            itemView.txtEventDate.text = event.tanggal
            itemView.txtDesc.text = event.desc
            itemView.imgEvent.setImageResource(event.imgResId)
            itemView.setOnClickListener { v ->
                val intent = Intent()
                intent.putExtra(Constant.extraTagEventName, event.nama)
                (mContext as Activity).setResult(Activity.RESULT_OK, intent)
                (mContext as Activity).finish()
            }
        }
    }
}
