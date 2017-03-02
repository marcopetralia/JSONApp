package marco.jsonapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import marco.jsonapp.R;
import marco.jsonapp.adapter.PlacesAdapter;
import marco.jsonapp.model.Place;
import marco.jsonapp.services.FoursquareAPI;

/**
 * Created by marco on 02/03/2017.
 */

public class SampleAPIActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PlacesAdapter adapter;
    ProgressBar loading;
    FoursquareAPI foursquareAPI;

    EditText searchET;
    Button searchBTN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sampleapi);
        adapter=new PlacesAdapter();
        layoutManager=new LinearLayoutManager(this);
        recyclerView=(RecyclerView) findViewById(R.id.search_rv);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        searchET=(EditText) findViewById(R.id.search_et);
        searchBTN=(Button) findViewById(R.id.search_btn);
        loading=(ProgressBar) findViewById(R.id.loading);

        searchBTN.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             doSearch();
                                         }
                                     }
        );


    }

    public void doSearch(){
        String query=searchET.getText().toString();
        new FoursquareApiTask().execute(query);
    }

    private class FoursquareApiTask extends AsyncTask<String, Void, ArrayList<Place>> {
        /*Params, the type of the parameters che mandiamo che servono per manipolarli
        *Progress, the type of the progress units published during the background computation, che viene mandato dal server.
        *Result, the type of the result  che ci aspettiamo of the background computation.*/

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values); //This method is used to display any form of progress in
            // the user interface while the background computation is still executing. For instance,
            // it can be used to animate a progress bar or show logs in a text field.
        }

        @Override
        protected ArrayList<Place> doInBackground(String... params) {
            ArrayList<Place> placesArrayList=new ArrayList<>();

            try{
                foursquareAPI =new FoursquareAPI();
                String url=foursquareAPI.getUrlString(params[0]);
                JSONObject jsonResposnse =foursquareAPI.getJSONObjectFromURL(url);
                JSONArray jsonPlaces=jsonResposnse.getJSONObject("response").getJSONArray("venues");


                for(int i=0; i<jsonPlaces.length(); i++){
                    placesArrayList.add(new Place(jsonPlaces.getJSONObject(i)));
                }
                return placesArrayList;
            }catch (IOException | JSONException e){
                e.printStackTrace();
            }
            return placesArrayList;


        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);
            adapter.setDataSet(places);
            loading.setVisibility(View.GONE);
        }
    }
}
