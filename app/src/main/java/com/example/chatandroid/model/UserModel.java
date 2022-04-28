package com.example.chatandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by luyenphong on 21/03/2021
 * 0358844343
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Parcelable {
    private String id;
    private String username;
    private String imageURL;

    protected UserModel(Parcel in) {
        id = in.readString();
        username = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(username);
        parcel.writeString(imageURL);
    }
}
