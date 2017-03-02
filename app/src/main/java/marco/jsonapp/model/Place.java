package marco.jsonapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marco on 02/03/2017.
 */

public class Place {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String contact;

    //KEYS
    private static final String NAME_KEY="name";
    private static final String ADDRESS_KEY="address";
    private static final String LAT_KEY="lat";
    private static final String LON_KEY="lng";
    private static final String PHONE_KEY="phone";


    public Place(JSONObject jsonPlace){
        try{
            name=jsonPlace.getString(NAME_KEY);
            JSONObject jsonAddress= jsonPlace.getJSONObject("location");
            address= jsonAddress.getString(ADDRESS_KEY);
            latitude= Double.parseDouble(jsonAddress.getString(LAT_KEY));
            longitude= Double.parseDouble(jsonAddress.getString(LON_KEY));
            JSONObject jsonContact=jsonPlace.getJSONObject("contact");
            contact= jsonContact.getString(PHONE_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public static String getNameKey() {
        return NAME_KEY;
    }

    public static String getAddressKey() {
        return ADDRESS_KEY;
    }

    public static String getLatKey() {
        return LAT_KEY;
    }

    public static String getLonKey() {
        return LON_KEY;
    }

}
