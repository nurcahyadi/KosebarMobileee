package com.example.android.kosebarvmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class ProfileActivity extends AppCompatActivity {
    TextView mNama,mEmail;
    ImageView ava;
    RequestQueue rq;
    String nm, imel;
    String url = "http://10.20.1.233/kosebar/nampilprofile.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_USERNAME = "email";
    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "name";
    public final static String TAG_PASS = "password";
    SharedPreferences sharedpreferences;
    Boolean session = false;
    String id, username;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    private List<ListitemModel> ListBarang;
    private Context mCtx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        rq = Volley.newRequestQueue(this);
        mNama = findViewById(R.id.namapembeli);
        mEmail = findViewById(R.id.emailpembeli);
        ava = findViewById(R.id.avatar);

//        sendjsonrequest();

        // Check sharedpreference
        SharedPreferences pref = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        String nm = pref.getString(TAG_NAMA, null);
        String em = pref.getString(TAG_USERNAME, null);

//        Log.d("nama ", restoredText);
        mNama.setText(nm);
        mEmail.setText(em);




    }














    public void sendjsonrequest(){
        String email = getPref("email", getApplicationContext());
        String password = getPref("password", getApplicationContext());


        JSONObject obj = new JSONObject();

        try{
            obj.put("email", email);
            obj.put("password", password);
        }catch (JSONException e){
            Log.d("error", e.getLocalizedMessage());
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                Log.d("response", response.toString());

                try {
                    String nm = response.getString(TAG_NAMA);
                    String imel = response.getString(TAG_USERNAME);


                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(session_status, true);
                    editor.putString(TAG_ID, id);
                    editor.putString(TAG_USERNAME, imel);

                    editor.commit();

                    mNama.setText(nm);
                    mEmail.setText(imel);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rq.add(jsonObjectRequest);
    }

    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}




