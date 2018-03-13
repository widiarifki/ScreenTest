package com.widiarifki.screentest.services

import com.widiarifki.screentest.model.Guest

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by widiarifki on 08/03/2018.
 */

interface GuestService {
    @get:GET("people")
    val guest: Call<List<Guest>>
}
