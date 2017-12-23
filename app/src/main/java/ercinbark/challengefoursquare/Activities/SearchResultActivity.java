package ercinbark.challengefoursquare.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import ercinbark.challengefoursquare.Adapters.VenuesAdapter;
import ercinbark.challengefoursquare.Models.VenuesModel;
import ercinbark.challengefoursquare.R;

public class SearchResultActivity extends AppCompatActivity {
    RecyclerView rcyVenues;
    RecyclerView.LayoutManager manager;
    VenuesAdapter venuesAdapter;
    ArrayList<VenuesModel> venueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Bundle bundle = getIntent().getExtras();
        getViews();
        venueList = bundle.getParcelableArrayList("model");
        venuesAdapter = new VenuesAdapter(getApplicationContext(), venueList);
        rcyVenues.setAdapter(venuesAdapter);


    }

    private void getViews() {
        rcyVenues = findViewById(R.id.rcyVenues);
        rcyVenues.setHasFixedSize(true);
        manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcyVenues.setLayoutManager(manager);
    }
}
