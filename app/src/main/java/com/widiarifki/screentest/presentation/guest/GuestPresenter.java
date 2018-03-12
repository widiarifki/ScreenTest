package com.widiarifki.screentest.presentation.guest;

import com.widiarifki.screentest.model.Guest;
import com.widiarifki.screentest.services.GuestService;
import com.widiarifki.screentest.services.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class GuestPresenter {
    IGuestView mView;

    public GuestPresenter(IGuestView view) {
        mView = view;
    }

    public void fetchGuestData(){
        mView.showProgressDialogue(true);

        GuestService guestService = ServiceBuilder.buildService(GuestService.class);
        Call<List<Guest>> guestRequest = guestService.getGuest();
        guestRequest.enqueue(new Callback<List<Guest>>() {
            @Override
            public void onResponse(Call<List<Guest>> request, Response<List<Guest>> response) {
                mView.showList(response.body());
            }

            @Override
            public void onFailure(Call<List<Guest>> request, Throwable t) {
                mView.showGetDataFail();
            }
        });

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
