package com.widiarifki.screentest.presentation.guest

import com.widiarifki.screentest.model.Guest
import com.widiarifki.screentest.services.GuestService
import com.widiarifki.screentest.services.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm



/**
 * Created by widiarifki on 27/02/2018.
 */

class GuestPresenter(internal var mView: IGuestView) {

    fun fetchGuestData() {

        val apiService = ServiceBuilder.buildService(GuestService::class.java)
        val realm = Realm.getDefaultInstance()
        val realmQuery = realm.where(Guest::class.java)
        val savedList: List<Guest>? = realmQuery.findAll()

        //get user if it's already saved
        if (savedList != null) {
            mView.showList(savedList)
        }

        apiService.getGuests()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { guestList ->
                            realm.beginTransaction()
                            realm.copyToRealmOrUpdate(guestList)
                            realm.commitTransaction()
                            mView.showList(guestList)
                        },
                        { error ->
                            if(savedList != null && savedList.count() == 0) {
                                mView.showDialogMessage("Something's Wrong", "Fetching data from server failed")
                            }
                        }
                )
    }

}
