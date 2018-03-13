package com.widiarifki.screentest.presentation.guest

import com.widiarifki.screentest.model.Guest
import com.widiarifki.screentest.services.GuestService
import com.widiarifki.screentest.services.ServiceBuilder

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by widiarifki on 27/02/2018.
 */

class GuestPresenter(internal var mView: IGuestView) {

    fun fetchGuestData() {
        mView.showProgressDialogue(true)

        val guestService = ServiceBuilder.buildService(GuestService::class.java)
        val guestRequest = guestService.guest
        guestRequest.enqueue(object : Callback<List<Guest>> {
            override fun onResponse(request: Call<List<Guest>>, response: Response<List<Guest>>) {
                mView.showList(response.body()!!)
            }

            override fun onFailure(request: Call<List<Guest>>, t: Throwable) {
                mView.showGetDataFail()
            }
        })

        /*OkHttpClient httpClient = new OkHttpClient();
        Request httpReq = new Request.Builder().url("http://dry-sierra-6832.herokuapp.com/api/people").build();
        Call httpCall = httpClient.newCall(httpReq);
        httpCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mView.showGetDataFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful() && response.code() == 200){
                    String dataString = response.body().string();
                    final List<Guest> guestList = new ArrayList<>();
                    try {
                        JSONArray dataJson = new JSONArray(dataString);
                        if(dataJson.length() > 0){
                            for(int i = 0; i < dataJson.length(); i++){
                                JSONObject record = dataJson.getJSONObject(i);
                                Guest guest = new Guest();
                                guest.setId(record.getInt("id"));
                                guest.setName(record.getString("name"));
                                guest.setBirthdate(record.getString("birthdate"));
                                guestList.add(guest);
                            }

                            mView.showList(guestList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });*/
    }
}
