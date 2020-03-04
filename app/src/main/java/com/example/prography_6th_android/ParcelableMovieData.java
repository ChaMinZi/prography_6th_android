package com.example.prography_6th_android;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableMovieData implements Parcelable {
    String title;
    String description;
    String director;
    String producer;
    String releaseDate;
    String rtScore;

    public ParcelableMovieData(String[] data) {
        this.title = data[0];
        this.description = data[1];
        this.director = data[2];
        this.producer = data[3];
        this.releaseDate = data[4];
        this.rtScore = data[5];
    }

    protected ParcelableMovieData(Parcel src) {
        title = src.readString();
        description = src.readString();
        director = src.readString();
        producer = src.readString();
        releaseDate = src.readString();
        rtScore = src.readString();
    }

    public static final Creator<ParcelableMovieData> CREATOR = new Creator<ParcelableMovieData>() {
        @Override
        public ParcelableMovieData createFromParcel(Parcel src) {
            return new ParcelableMovieData(src);
        }

        @Override
        public ParcelableMovieData[] newArray(int size) {
            return new ParcelableMovieData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(director);
        dest.writeString(producer);
        dest.writeString(releaseDate);
        dest.writeString(rtScore);
    }
}
