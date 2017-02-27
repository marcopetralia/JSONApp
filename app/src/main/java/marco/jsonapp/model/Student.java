package marco.jsonapp.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marco on 27/02/2017.
 */
public class Student {

    private static final String NAME_KEY = "nome";
    private static final String EMAIL_KEY = "email";
    private static final String GITHUB_KEY = "github";
    private static final String COURSE_KEY="corso";

    String name, email, github;
    Corso corso;

    public Student(JSONObject jsonStudent) { //vuole un oggetto all'interno del json
        try {

            name = jsonStudent.getString(NAME_KEY);
            Log.d("nome",name);
            email = jsonStudent.getString(EMAIL_KEY);
            Log.d("email",email);
            github = buildGithubUrl(jsonStudent.optString(GITHUB_KEY,"")); // è come getIntExtra(),
                                                   // optString vuole un valore da sostituire a null, stiamo
                                                    //al "sicuro" perchè non può avere mai valore null

            corso=new Corso(jsonStudent.getJSONObject(COURSE_KEY));
            Log.d("corso", String.valueOf(corso));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public Corso getCorso(){
        return this.corso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }


    private String buildGithubUrl(String username){
        username = username.replace("@",""); //sostituisce la @ con niente perchè l'url del
                                             // collegamento con github non l'accetta
        return "https://github.com/" + username;
    }

}