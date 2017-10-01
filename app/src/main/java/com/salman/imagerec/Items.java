package com.salman.imagerec;

import android.graphics.Bitmap;

import java.text.SimpleDateFormat;


public class Items {


    Bitmap image;
    String name;
    String date;
    String size;

    public String getSize() {
        return size;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Items(Bitmap image, String name, String date, String size) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.size = size;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Items(Bitmap image) {

        this.image = image;
    }
}
