package marco.jsonapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import marco.jsonapp.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.student_list).setOnClickListener(this);
        findViewById(R.id.foursquare_api).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.foursquare_api){
            startActivity(new Intent(this,SampleAPIActivity.class));
        }
        if(id == R.id.student_list){
            startActivity(new Intent(this,StudentsActivity.class));
        }

    }
}