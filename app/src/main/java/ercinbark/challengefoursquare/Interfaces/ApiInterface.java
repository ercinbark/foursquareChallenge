package ercinbark.challengefoursquare.Interfaces;

import ercinbark.challengefoursquare.Models.SearchModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Casper on 23.12.2017.
 */

public interface ApiInterface {
    String ll = "41.025706,28.976096";
    String client_id = "TCIIHL05HBMV3HBWCWA3ZKEKFVNADVOKYK32D1041XAKX05Q";
    String client_secret = "3I1UXWACJORKM505MDJGQ0OZENXKR3FSJRIW2GZJQWR2SNP3";
    String v = "20181212";


//    @Headers({"ll: "+ll,"client_id: "+client_id,"client_secret: "+client_secret,"v: "+v})
//    @GET("venues/search")
//    Call<SearchModel> getSearch(@Header("near") String near,@Header("query") String query);


    //https://api.foursquare.com/v2/venues/search?ll=41.025706,28.976096&
    // client_id=TCIIHL05HBMV3HBWCWA3ZKEKFVNADVOKYK32D1041XAKX05Q&
    // client_secret=3I1UXWACJORKM505MDJGQ0OZENXKR3FSJRIW2GZJQWR2SNP3&v=20181212&
    // near=İstanbul&query=Cafe


    @GET("venues/search")
    Call<SearchModel> getSearch(@Query("ll") String ll,@Query("client_id") String client_id,@Query("client_secret") String client_secret,@Query("v") String v,
                                @Query("near") String near,@Query("query") String query);

}
