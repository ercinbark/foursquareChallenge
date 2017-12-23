package ercinbark.challengefoursquare.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by erçin on 23.12.2017.
 */

public class location implements Parcelable {
    private String address;
    private String lat;
    private String lng;
    private String city;
    private String state;
    private String country;

    protected location(Parcel in) {
        address = in.readString();
        lat = in.readString();
        lng = in.readString();
        city = in.readString();
        state = in.readString();
        country = in.readString();
    }

    public static final Creator<location> CREATOR = new Creator<location>() {
        @Override
        public location createFromParcel(Parcel in) {
            return new location(in);
        }

        @Override
        public location[] newArray(int size) {
            return new location[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(country);
    }
}
