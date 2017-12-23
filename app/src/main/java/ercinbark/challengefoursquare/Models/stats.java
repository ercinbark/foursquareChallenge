package ercinbark.challengefoursquare.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Casper on 23.12.2017.
 */

public class stats implements Parcelable{
    private int checkinsCount;
    private int usersCount;
    private int tipCount;

    protected stats(Parcel in) {
        checkinsCount = in.readInt();
        usersCount = in.readInt();
        tipCount = in.readInt();
    }

    public static final Creator<stats> CREATOR = new Creator<stats>() {
        @Override
        public stats createFromParcel(Parcel in) {
            return new stats(in);
        }

        @Override
        public stats[] newArray(int size) {
            return new stats[size];
        }
    };

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(int checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getTipCount() {
        return tipCount;
    }

    public void setTipCount(int tipCount) {
        this.tipCount = tipCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(checkinsCount);
        parcel.writeInt(usersCount);
        parcel.writeInt(tipCount);
    }
}
