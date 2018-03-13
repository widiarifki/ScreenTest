package com.widiarifki.screentest.presentation.event

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.widiarifki.screentest.MainActivity
import com.widiarifki.screentest.R
import com.widiarifki.screentest.adapter.EventAdapter
import com.widiarifki.screentest.model.Event
import com.widiarifki.screentest.presentation.mapview.MapViewFragment

/**
 * Created by widiarifki on 27/02/2018.
 */

class EventFragment : Fragment(), IEventView {

    private var mContext: Context? = null
    private var mMainActivity: MainActivity? = null
    private var eventPresenter: EventPresenter? = null

    private var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = context
        mMainActivity = mContext as MainActivity?
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_event, container, false)
        eventPresenter = EventPresenter(this)

        mRecyclerView = view.findViewById(R.id.recyclerView) as RecyclerView

        eventPresenter!!.setEventList(Event.dummyEvents())

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.event_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        if (id == R.id.action_add_map) {
            goToMapview()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showList(eventList: List<Event>) {
        val adapter = EventAdapter(mContext, eventList)
        mRecyclerView!!.adapter = adapter
        mRecyclerView!!.layoutManager = LinearLayoutManager(mContext)
    }

    override fun goToMapview() {
        mMainActivity!!.addStackedFragment(this, TITLE, MapViewFragment(), MapViewFragment.TITLE)
    }

    companion object {

        var TITLE = "Event"
    }
}
