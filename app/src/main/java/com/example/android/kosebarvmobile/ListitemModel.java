package com.example.android.kosebarvmobile;

/**
 * Created by nurcahyadiperdana on 4/20/18.
 */

public class ListitemModel {

    private String name;
    private String description;
    private String stock;
    private String price;
    private String image;


    public ListitemModel( String name, String description,String stock, String price,String image) {

        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.image = image;
    }



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStock(){
        return stock;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }


}

