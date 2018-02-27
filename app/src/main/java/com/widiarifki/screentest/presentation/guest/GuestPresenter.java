package com.widiarifki.screentest.presentation.guest;

import com.widiarifki.screentest.model.Guest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        OkHttpClient httpClient = new OkHttpClient();
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
                                guest.setBirthDate(record.getString("birthdate"));
                                guestList.add(guest);
                            }

                            mView.showList(guestList);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
