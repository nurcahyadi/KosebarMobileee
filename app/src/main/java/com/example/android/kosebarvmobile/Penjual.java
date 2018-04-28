package com.example.android.kosebarvmobile;

/**
 * Created by nurcahyadiperdana on 4/20/18.
 */

public class Penjual {
    private String store_name;
    private String address;
    private String image;

//    private static final String urlgambar = "http://192.168.1.101/kosebar/getdata.php";
    public Penjual(String store_name, String address, String image) {
        this.store_name = store_name;
        this.address = address;
        this.image = image;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getAddress() {
        return address;
    }


    public String getImage() {
        return image;
    }


}

