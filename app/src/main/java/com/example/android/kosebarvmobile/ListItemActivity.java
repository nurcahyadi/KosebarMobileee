package com.example.android.kosebarvmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    private static final String URL_PRODUCTS = "http://10.20.1.233/kosebar/getdatabarang.php";
    //a list to store all the products
    List<ListitemModel> ListBarang;
    //the recyclerview
    RecyclerView recyclerView;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        ListBarang = new ArrayList<>();

//        sharedpreferences = getSharedPreferences("preferencegambar", Context.MODE_PRIVATE);
//
//        session = sharedpreferences.getBoolean(session_status, false);
//        id = sharedpreferences.getString("id", null);



        loadPenjual();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.signout){
            doLogout();
        } else {
            startActivity(new Intent(ListItemActivity.this, ProfileActivity.class));
        }
        return true;
    }
    public void doLogout(){
        SharedPreferences prefs = getSharedPreferences(my_shared_preferences, MODE_PRIVATE);
        prefs.edit().clear().commit();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }


    public void loadPenjual(){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject barang = array.getJSONObject(i);

//                                String id = barang.getString("id");
//                                // menyimpan login ke session
//                                SharedPreferences.Editor editor = sharedpreferences.edit();
//                                editor.putBoolean(session_status, true);
//                                editor.putString("id", id);
//                                editor.commit();

                                //adding the product to product list
                                ListBarang.add(new ListitemModel(

                                        barang.getString("name"),
                                        barang.getString("description"),
                                        barang.getString("stock"),
                                        barang.getString("price"),
                                        barang.getString("photo")


                                ));


                            }


                            //creating adapter object and setting it to recyclerview
                            ListitemAdapter adapter = new ListitemAdapter(ListItemActivity.this, ListBarang);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListItemActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
