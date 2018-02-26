package com.widiarifki.screentest.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.widiarifki.screentest.R;
import com.widiarifki.screentest.adapter.GuestAdapter;
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
 * Created by widiarifki on 24/02/2018.
 */

public class GuestFragment extends Fragment {

    Context mContext;
    Activity mContextActivity;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRvSwipeLayout;
    ProgressDialog mProgressDialog;
    public static String TITLE = "Guest";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mContextActivity = (Activity)mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRvSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.rvSwipeLayout);
        mRvSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Mengambil data..");
        mProgressDialog.show();

        fetchData();
        return view;
    }

    private void fetchData() {
        OkHttpClient httpClient = new OkHttpClient();
        Request httpReq = new Request.Builder().url("http://dry-sierra-6832.herokuapp.com/api/people").build();
        Call httpCall = httpClient.newCall(httpReq);
        httpCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mContextActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressDialog.dismiss();
                        new AlertDialog.Builder(mContext).setMessage("Gagal terhubung ke server").create().show();
                        if(mRvSwipeLayout.isRefreshing()){
                            mRvSwipeLayout.setRefreshing(false);
                        }
                    }
                });
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

                            mContextActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GuestAdapter adapter = new GuestAdapter(mContext, guestList);
                                    mRecyclerView.setAdapter(adapter);
                                    mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                                    mProgressDialog.dismiss();
                                    if(mRvSwipeLayout.isRefreshing()){
                                        mRvSwipeLayout.setRefreshing(false);
                                    }
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
