package com.example.tes1;

import android.os.Parcel;
import android.os.Parcelable;

public class Events implements Parcelable {

    private int gambarId;
    private String namaEvent;
    private String tanggalEvent;

    public Events(int gambarId, String namaEvent, String tanggalEvent) {
        this.gambarId = gambarId;
        this.namaEvent = namaEvent;
        this.tanggalEvent = tanggalEvent;
    }

    protected Events(Parcel in) {
        gambarId = in.readInt();
        namaEvent = in.readString();
        tanggalEvent = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gambarId);
        dest.writeString(namaEvent);
        dest.writeString(tanggalEvent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Events> CREATOR = new Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel in) {
            return new Events(in);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };

    public int getGambarId() {
        return gambarId;
    }

    public String getNamaEvent() {
        return namaEvent;
    }

    public String getTanggalEvent() {
        return tanggalEvent;
    }

    public void setGambarId(int gambarId) {
        this.gambarId = gambarId;
    }

    public void setNamaEvent(String namaEvent) {
        this.namaEvent = namaEvent;
    }

    public void setTanggalEvent(String tanggalEvent) {
        this.tanggalEvent = tanggalEvent;
    }
}
