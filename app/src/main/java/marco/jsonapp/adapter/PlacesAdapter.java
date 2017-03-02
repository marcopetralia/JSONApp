package marco.jsonapp.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import marco.jsonapp.R;
import marco.jsonapp.model.Place;

/**
 * Created by marco on 02/03/2017.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private ArrayList<Place> dataSet = new ArrayList<>();

    public void setDataSet(ArrayList<Place> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);

        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlacesAdapter.PlaceViewHolder holder, int position) {
        Place place = dataSet.get(position);
        holder.placeNameTV.setText(place.getName());
        holder.placeAdressTV.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView placeNameTV;
        TextView placeAdressTV;
        Button goBTN;
        Button callBTN;

        PlaceViewHolder(View itemView) {
            super(itemView);
            goBTN = (Button) itemView.findViewById(R.id.go_btn);
            callBTN = (Button) itemView.findViewById(R.id.call_btn);
            placeNameTV = (TextView) itemView.findViewById(R.id.place_name);
            placeAdressTV = (TextView) itemView.findViewById(R.id.place_address);

            goBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //inseriamo un listener all'icona che ci collega col sito
                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri uri= Uri.parse("geo:0,0?q="+addressTV.getText().toString());
                    intent.setData(uri);
                    v.getContext().startActivity(intent);
                }
            });

            callBTN.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) { //inseriamo un listener all'icona che ci collega col sito
                    Intent intent=new Intent();
                    intent.setAction(Intent.ACTION_DIAL);
                    Uri uri= Uri.parse("tel:"+place.getAddress().toString());
                    intent.setData(uri);
                    v.getContext().startActivity(intent);
                }
                });
        }
    }
}
