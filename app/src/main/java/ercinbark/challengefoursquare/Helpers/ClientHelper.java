package ercinbark.challengefoursquare.Helpers;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Casper on 23.12.2017.
 */

public class ClientHelper {
    public static OkHttpClient getClient() {
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(5, TimeUnit.MINUTES);
        b.writeTimeout(5, TimeUnit.MINUTES);
        return b.build();
    }
}
