package marco.jsonapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import marco.jsonapp.R;
import marco.jsonapp.adapter.StudentsAdapter;
import marco.jsonapp.model.Student;

/**
 * Created by marco on 27/02/2017.
 */

public class MainActivity extends AppCompatActivity {
    RecyclerView studentsRv;
    LinearLayoutManager layoutManager;
    StudentsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentsRv = (RecyclerView) findViewById(R.id.students_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new  StudentsAdapter();
        studentsRv.setLayoutManager(layoutManager);
        studentsRv.setAdapter(adapter);
        fetchStudentsFromJSON(); //prendere studenti dal file json
    }

    private void fetchStudentsFromJSON() {
        ArrayList<Student> students = new ArrayList<>();
        try {

            JSONObject JsonObject=new JSONObject(readLocalJson());

                JSONArray studentsJsonArray = JsonObject.getJSONArray("students"); //si prende i singoli oggetti Student

                for (int j = 0; j < studentsJsonArray.length(); j++) {
                    JSONObject jsonStudent = studentsJsonArray.getJSONObject(j); //si prende i singoli oggetti Student
                    Log.d("ciao", String.valueOf(jsonStudent));
                    students.add(new Student(jsonStudent)); //aggiunge all'araylist il riferimento all'oggetto student
                }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        // add dataset to adapter
        adapter.setDataSet(students);

    }


    private String readLocalJson() {

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = getResources().openRawResource(R.raw.students_v2)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }


}