package com.widiarifki.screentest.model

import com.widiarifki.screentest.R
import java.util.*

/**
 * Created by widiarifki on 24/02/2018.
 */

class Event {
    var imgResId: Int = 0
    var nama: String? = null
    var tanggal: String? = null
    var desc: String? = null
    var latLong: String? = null

    companion object {

        fun dummyEvents(): List<Event> {
            val namaList = arrayOf("The Revenant", "Revenge", "The Scorch Trials", "The Exorcist", "The Surrender Call")
            val tglList = arrayOf("1 Feb 2018", "2 Feb 2018", "3 Feb 2018", "4 Feb 2018", "5 Feb 2018")
            val descList = arrayOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam")
            val imgList = intArrayOf(R.drawable.news1, R.drawable.news2, R.drawable.news3, R.drawable.news4, R.drawable.news5)
            val latlongList = arrayOf("-7.0330161,107.698488", "-7.0337087,107.6995915", "-7.0303852,107.6930857", "-7.028716,107.6884254", "-7.0357416,107.6932892")

            val dummies = ArrayList<Event>()
            for (i in namaList.indices) {
                val event = Event()
                event.nama = namaList[i]
                event.tanggal = tglList[i]
                event.desc = descList[i]
                event.imgResId = imgList[i]
                event.latLong = latlongList[i]
                dummies.add(event)
            }

            return dummies
        }
    }
}
