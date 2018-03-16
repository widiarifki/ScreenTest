package com.widiarifki.screentest.presentation.event

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.widiarifki.screentest.R
import com.widiarifki.screentest.adapter.EventAdapter
import com.widiarifki.screentest.model.Event
import com.widiarifki.screentest.presentation.mapview.MapViewActivity
import kotlinx.android.synthetic.main.fragment_event.*

/**
 * Created by widiarifki on 15/03/2018.
 */

class EventActivity : AppCompatActivity(), IEventView {

    val presenter: EventPresenter = EventPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_event)
        
        presenter.setEventList(Event.dummyEvents())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.event_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_add_map) {
            goToMapview()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showList(eventList: List<Event>) {
        val adapter = EventAdapter(this, eventList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun goToMapview() {
        val intent = Intent(this, MapViewActivity::class.java)
        startActivity(intent)
    }
}
