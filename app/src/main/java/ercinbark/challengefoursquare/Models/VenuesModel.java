package ercinbark.challengefoursquare.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Casper on 23.12.2017.
 */

public class VenuesModel implements Parcelable {
    private String id;
    private String name;
    private location location;
    private String url;
    private stats stats;

    public VenuesModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        location = in.readParcelable(ercinbark.challengefoursquare.Models.location.class.getClassLoader());
        url = in.readString();
        stats=in.readParcelable(ercinbark.challengefoursquare.Models.stats.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeParcelable(location, flags);
        dest.writeString(url);
        dest.writeParcelable(stats,flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VenuesModel> CREATOR = new Creator<VenuesModel>() {
        @Override
        public VenuesModel createFromParcel(Parcel in) {
            return new VenuesModel(in);
        }

        @Override
        public VenuesModel[] newArray(int size) {
            return new VenuesModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ercinbark.challengefoursquare.Models.location getLocation() {
        return location;
    }

    public void setLocation(ercinbark.challengefoursquare.Models.location location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ercinbark.challengefoursquare.Models.stats getStats() {
        return stats;
    }

    public void setStats(ercinbark.challengefoursquare.Models.stats stats) {
        this.stats = stats;
    }
}
