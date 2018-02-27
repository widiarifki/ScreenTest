package com.widiarifki.screentest.presentation.guest;

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

import java.util.List;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class GuestFragment extends Fragment implements IGuestView {

    public static String TITLE = "Guest";

    GuestPresenter guestPresenter;
    Context mContext;
    Activity mContextActivity;

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRvSwipeLayout;
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mContextActivity = (Activity)mContext;
        mProgressDialog = new ProgressDialog(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest, container, false);
        guestPresenter = new GuestPresenter(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRvSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.rvSwipeLayout);
        mRvSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                guestPresenter.fetchGuestData();
            }
        });

        guestPresenter.fetchGuestData();
        return view;
    }

    @Override
    public void showList(final List<Guest> guestList) {
        mContextActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GuestAdapter adapter = new GuestAdapter(mContext, guestList);
                if(mRecyclerView.getAdapter() == null) {
                    mRecyclerView.setAdapter(adapter);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                }
                else {
                    mRecyclerView.swapAdapter(adapter, false);
                }

                showProgressDialogue(false);
                if(mRvSwipeLayout.isRefreshing()){
                    mRvSwipeLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void showProgressDialogue(boolean isShowing) {
        if(mProgressDialog != null) {
            if (isShowing) {
                mProgressDialog.setMessage("Mengambil data..");
                mProgressDialog.show();
            } else {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    public void showGetDataFail() {
        mContextActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showProgressDialogue(false);
                showDialogue("Error", "Gagal terhubung ke server");
                if(mRvSwipeLayout.isRefreshing()){
                    mRvSwipeLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void showDialogue(String title, String info) {
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(info)
                .create()
                .show();
    }
}
