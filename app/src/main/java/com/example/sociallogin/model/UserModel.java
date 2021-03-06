package com.example.sociallogin.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

    public String userName;
    public String userEmail;
    public String profilePic;
    public String gender;

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel parcel) {
            return new UserModel(parcel);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public UserModel()
    {

    }

    public UserModel(Parcel parcel) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeString(userName);
        parcel.writeString(userEmail);
        parcel.writeString(profilePic);
        parcel.writeString(gender);
    }
}
