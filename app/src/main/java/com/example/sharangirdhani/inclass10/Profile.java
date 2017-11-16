/*
    InClass 10
    Salman Mujtaba 800969897
    Sharan Giridhani 800960333
    Profile
*/

package com.example.sharangirdhani.inclass10;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sharangirdhani on 11/13/17.
 */

public class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
    public String name, department, email, phone, image, profileId;

    public Profile(String name, String department, String email, String phone, String image, String profileId) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.profileId = profileId;
    }

    protected Profile(Parcel in) {
        this.name = in.readString();
        this.department = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.image = in.readString();
        this.profileId = in.readString();
    }

    public Profile() {

    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(department);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(image);
    }
}
