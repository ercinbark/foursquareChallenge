package ercinbark.challengefoursquare.Base;

import android.app.Application;

/**
 * Created by erçin on 23.12.2017.
 */

public class BaseApplication extends Application {
    public static String API_URL = "https://api.foursquare.com/v2/";

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public BaseApplication() {
        super();
        instance = this;
    }
}
