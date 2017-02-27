package marco.jsonapp.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marco on 27/02/2017.
 */

public class Corso {

    private int ID;
    private String nome;
    private static final String ID_KEY="id";
    private static final String NAME_KEY="nome";


    public Corso(JSONObject jsonStudent) { //vuole un oggetto all'interno del json
        try {
            this.ID = jsonStudent.getInt(ID_KEY);
            Log.d("id",String.valueOf(ID).toString());
            this.nome = jsonStudent.optString(NAME_KEY,"");
            Log.d("nome",nome);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
