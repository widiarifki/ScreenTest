package com.widiarifki.screentest.model;

import com.widiarifki.screentest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by widiarifki on 24/02/2018.
 */

public class Event {
    int mImgResId;
    String mNama;
    String mTanggal;
    String mDesc;
    String mLatLong;

    public int getImgResId() {
        return mImgResId;
    }

    public void setImgResId(int imgResId) {
        this.mImgResId = imgResId;
    }

    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        this.mNama = nama;
    }

    public String getTanggal() {
        return mTanggal;
    }

    public void setTanggal(String tanggal) {
        this.mTanggal = tanggal;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getLatLong() {
        return mLatLong;
    }

    public void setLatLong(String latLong) {
        mLatLong = latLong;
    }

    public static List<Event> dummyEvents(){
        String[] namaList = new String[]{"The Revenant", "Revenge", "The Scorch Trials", "The Exorcist", "The Surrender Call"};
        String[] tglList = new String[]{"1 Feb 2018", "2 Feb 2018", "3 Feb 2018", "4 Feb 2018", "5 Feb 2018"};
        String[] descList = new String[]{
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam",
        };
        int[] imgList = new int[]{R.drawable.news1, R.drawable.news2, R.drawable.news3, R.drawable.news4, R.drawable.news5};
        String[] latlongList = new String[]{
                "-7.0330161,107.698488",
                "-7.0337087,107.6995915",
                "-7.0303852,107.6930857",
                "-7.028716,107.6884254",
                "-7.0357416,107.6932892"
        };

        List<Event> dummies = new ArrayList<Event>();
        for(int i = 0; i < namaList.length; i++){
            Event event = new Event();
            event.setNama(namaList[i]);
            event.setTanggal(tglList[i]);
            event.setDesc(descList[i]);
            event.setImgResId(imgList[i]);
            event.setLatLong(latlongList[i]);
            dummies.add(event);
        }

        return dummies;
    }
}
