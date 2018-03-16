package com.widiarifki.screentest.services

import com.widiarifki.screentest.model.Guest
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by widiarifki on 14/03/2018.
 */
interface GuestService {
    @GET("people")
    fun getGuests(): Observable<List<Guest>>
}