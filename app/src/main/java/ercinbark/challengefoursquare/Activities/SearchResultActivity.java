package ercinbark.challengefoursquare.Activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ercinbark.challengefoursquare.Adapters.VenuesAdapter;
import ercinbark.challengefoursquare.Interfaces.ShowVenueDetail;
import ercinbark.challengefoursquare.Models.VenuesModel;
import ercinbark.challengefoursquare.R;

public class SearchResultActivity extends AppCompatActivity implements ShowVenueDetail, OnMapReadyCallback {
    RecyclerView rcyVenues;
    RecyclerView.LayoutManager manager;
    VenuesAdapter venuesAdapter;
    ArrayList<VenuesModel> venueList;

    Dialog detailDialog;
    TextView detailName,detailAddress,detailCheckCount,detailUserCount,detailTipCount;

    MapView map;
    String lat, lng;
    Double mapLat, mapLng;
    GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Bundle bundle = getIntent().getExtras();
        getViews();
        //arama sonuçları listesini aldık
        venueList = bundle.getParcelableArrayList("model");
        venuesAdapter = new VenuesAdapter(getApplicationContext(), venueList, SearchResultActivity.this);
        rcyVenues.setAdapter(venuesAdapter);


    }

    private void getViews() {
        rcyVenues = findViewById(R.id.rcyVenues);
        rcyVenues.setHasFixedSize(true);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcyVenues.setLayoutManager(manager);

        detailDialog = new Dialog(this);
        detailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        detailDialog.setContentView(R.layout.detail_dilaog);
        detailDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        detailName = detailDialog.findViewById(R.id.detailName);
        detailAddress = detailDialog.findViewById(R.id.detailAddress);
        detailCheckCount = detailDialog.findViewById(R.id.detailcheckinsCount);
        detailUserCount = detailDialog.findViewById(R.id.detailusersCount);
        detailTipCount = detailDialog.findViewById(R.id.detailtipCount);
        map = (MapView) detailDialog.findViewById(R.id.mapView);
        map.getMapAsync(this);
        map.onCreate(detailDialog.onSaveInstanceState());
        map.onResume();

    }

    @Override
    public void showDetail(final VenuesModel venuesModel) {
        //interfaceden gelen verilerimizi ekranda gösterdik.
        detailDialog.show();
        detailName.setText(venuesModel.getName());
        lat = venuesModel.getLocation().getLat();
        lng = venuesModel.getLocation().getLng();
        mapLat = Double.valueOf(lat);
        mapLng = Double.valueOf(lng);

        LatLng latLngNew = new LatLng(mapLat, mapLng);
        mGoogleMap.addMarker(new MarkerOptions().position(latLngNew));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngNew, 17));

        detailCheckCount.setText("Toplam Checkin : "+venuesModel.getStats().getCheckinsCount());
        detailUserCount.setText("Ziyaretçi Sayısı : "+venuesModel.getStats().getUsersCount());
        detailTipCount.setText("İpucu Sayısı : "+venuesModel.getStats().getTipCount());
        detailAddress.setText(venuesModel.getLocation().getAddress());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;
    }
}
