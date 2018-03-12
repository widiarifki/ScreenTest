package com.widiarifki.screentest.services;

import com.widiarifki.screentest.model.Guest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by widiarifki on 08/03/2018.
 */

public interface GuestService {
    @GET("people")
    Call<List<Guest>> getGuest();
}
