package ercinbark.challengefoursquare.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ercinbark.challengefoursquare.Interfaces.ShowVenueDetail;
import ercinbark.challengefoursquare.Models.VenuesModel;
import ercinbark.challengefoursquare.R;

/**
 * Created by Casper on 23.12.2017.
 */

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.ViewHolder> {
    Context context;
    ArrayList<VenuesModel> venueList;
    ShowVenueDetail showVenuDetail;

    public VenuesAdapter(Context context, ArrayList<VenuesModel> venueList,ShowVenueDetail showVenuDetail) {
        this.context = context;
        this.venueList = venueList;
        this.showVenuDetail=showVenuDetail;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView venueName, venueAddres, venueCity, venueCountry;
        RelativeLayout venueDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            venueName = itemView.findViewById(R.id.venueName);
            venueAddres = itemView.findViewById(R.id.venueAddress);
            venueCity = itemView.findViewById(R.id.venueCity);
            venueCountry = itemView.findViewById(R.id.venueCountry);
            venueDetail=itemView.findViewById(R.id.rl_venueDetail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_venue, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.venueName.setText(venueList.get(position).getName());
        holder.venueAddres.setText(venueList.get(position).getLocation().getAddress());
        holder.venueCity.setText(venueList.get(position).getLocation().getCity());
        holder.venueCountry.setText(venueList.get(position).getLocation().getCountry());

        holder.venueDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showInterface ile seçtiğimiz position da bulunan objemizin verilerini aldık
                showVenuDetail.showDetail(venueList.get(position));
            }
        });
    }
    @Override
    public int getItemCount() {
        return venueList.size();
    }


}
