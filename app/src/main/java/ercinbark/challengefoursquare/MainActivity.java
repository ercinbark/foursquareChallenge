package ercinbark.challengefoursquare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ercinbark.challengefoursquare.Activities.SearchResultActivity;
import ercinbark.challengefoursquare.Base.BaseApplication;
import ercinbark.challengefoursquare.Helpers.ClientHelper;
import ercinbark.challengefoursquare.Interfaces.ApiInterface;
import ercinbark.challengefoursquare.Models.SearchModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_exp, et_cityOrState;
    Button btn_search;

    private ApiInterface apiInterface;


    String ll = "41.025706,28.976096";
    String client_id = "TCIIHL05HBMV3HBWCWA3ZKEKFVNADVOKYK32D1041XAKX05Q";
    String client_secret = "3I1UXWACJORKM505MDJGQ0OZENXKR3FSJRIW2GZJQWR2SNP3";
    String v = "20181212";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        Retrofit retrofit = new Retrofit.Builder().client(ClientHelper.getClient()).baseUrl(BaseApplication.API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(ApiInterface.class);
        //https://api.foursquare.com/v2/venues/search?ll=41.025706,28.976096&
        // client_id=TCIIHL05HBMV3HBWCWA3ZKEKFVNADVOKYK32D1041XAKX05Q&
        // client_secret=3I1UXWACJORKM505MDJGQ0OZENXKR3FSJRIW2GZJQWR2SNP3&v=20181212&
        // near=İstanbul&query=Cafe

    }

    private void getViews() {
        et_exp = findViewById(R.id.et_exp);
        et_cityOrState = findViewById(R.id.et_cityOrState);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                getSearch();
                break;
            default:
        }
    }

    private void getSearch() {
        Call<SearchModel> call = apiInterface.getSearch(ll, client_id, client_secret, v, "Istanbul", "cafe");
        call.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.body() != null) {
                    Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this, SearchResultActivity.class);
                    i.putExtra("model",response.body().getResponse().getVenues());
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "no", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ss : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
