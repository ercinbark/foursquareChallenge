package ercinbark.challengefoursquare;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
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

    String near, query;
    Dialog infDialog;
    Button infDialog_dismiss;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        Retrofit retrofit = new Retrofit.Builder().client(ClientHelper.getClient()).baseUrl(BaseApplication.API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        apiInterface = retrofit.create(ApiInterface.class);
        dialog = new ProgressDialog(this, R.style.ProgressBarTheme);
        dialog.setCancelable(false);
        dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
    }

    private void getViews() {
        et_exp = findViewById(R.id.et_exp);
        et_cityOrState = findViewById(R.id.et_cityOrState);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        infDialog = new Dialog(this);
        infDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        infDialog.setContentView(R.layout.inf_dialog);
        infDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        infDialog_dismiss = infDialog.findViewById(R.id.infDialog_dismiss);
        infDialog_dismiss.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                near = et_cityOrState.getText().toString();
                query = et_exp.getText().toString();
                if (query.equals("")) {
                    infDialog.show();
                } else if (et_exp.getText().toString().length() < 3) {
                    infDialog.show();
                } else if (!et_exp.getText().toString().matches("[a-zA-Z ]+")) {
                    infDialog.show();
                } else {
                    getSearch();
                }
                break;
            case R.id.infDialog_dismiss:
                infDialog.dismiss();
                break;
            default:
        }
    }
    private void getSearch() {
        dialog.show();
        Call<SearchModel> call = apiInterface.getSearch(ll, client_id, client_secret, v, near, query);
        call.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                dialog.dismiss();
                if (response.body() != null) {
                    //response true geldi ve result activitesine gönderildi.
                    Intent i = new Intent(MainActivity.this, SearchResultActivity.class);
                    i.putExtra("model", response.body().getResponse().getVenues());
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Bir hata oluştu.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
            }
        });
    }
}
