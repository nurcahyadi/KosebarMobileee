package com.example.android.kosebarvmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailbarangActivity extends AppCompatActivity {

    String url = "http://10.20.1.233/kosebar/getdetailbarang.php?user_id=";
    TextView nmbarang,hrgbarang,deskbarang;
    ImageView gmbrbarang;
    Button sewa;
    RequestQueue rq;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailbarang);

        nmbarang = findViewById(R.id.namabarang);
        hrgbarang = findViewById(R.id.hargabarang);
        deskbarang = findViewById(R.id.deskripsibarang);
        gmbrbarang = findViewById(R.id.gambarbarang);
        sewa = findViewById(R.id.btnsewa);

        rq = Volley.newRequestQueue(this);

        Intent ambildata = getIntent();
        final String nma = ambildata.getStringExtra("name");
        final String hrg = ambildata.getStringExtra("price");
        final String deskrip = ambildata.getStringExtra("description");

        nmbarang.setText(nma);
        hrgbarang.setText(hrg);
        deskbarang.setText(deskrip);

//        sendrequest();


        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(DetailbarangActivity.this,PesanActivity.class);
                startActivity(a);
            }
        });
    }

    public void sendrequest(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String nama = response.getString("name");
                    String harga = response.getString("price");
                    String deskripsi = response.getString("description");


                    nmbarang.setText(nama);
                    hrgbarang.setText(harga);
                    deskbarang.setText(deskripsi);

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rq.add(jsonObjectRequest);
    }


}
