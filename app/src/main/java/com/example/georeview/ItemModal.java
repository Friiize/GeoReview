package com.example.georeview;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemModal implements Parcelable {
    private String name;
    private String image;
    private String note;
    private String geoloc;
    private String id;

    ItemModal(String id, String name, String image, String note, String geoloc) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.note = note;
        this.geoloc = geoloc;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setGeoloc(String geoloc) {
        this.geoloc = geoloc;
    }

    public String getGeoloc() {
        return geoloc;
    }

    public ItemModal(Parcel in) {
        String[] data = new String[5];

        in.readStringArray(data);
        this.id = data[0];
        this.name = data[1];
        this.image = data[2];
        this.note = data[3];
        this.geoloc = data[4];

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(note);
        dest.writeString(geoloc);
        dest.writeString(id);
    }

    public static final Creator<ItemModal> CREATOR = new Creator<ItemModal>() {
        @Override
        public ItemModal createFromParcel(Parcel in) {
            return new ItemModal(in);
        }

        @Override
        public ItemModal[] newArray(int size) {
            return new ItemModal[size];
        }
    };
}
