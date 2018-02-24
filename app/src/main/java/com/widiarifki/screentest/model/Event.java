package com.widiarifki.screentest.model;

/**
 * Created by widiarifki on 24/02/2018.
 */

public class Event {
    int mImgResId;
    String mNama;
    String mTanggal;

    public Event(String nama, String tanggal, int imgResId){
        mNama = nama;
        mTanggal = tanggal;
        mImgResId = imgResId;
    }

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
}
