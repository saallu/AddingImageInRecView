package com.salman.imagerec;

import android.content.Intent;
import android.provider.MediaStore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Salman on 9/24/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name;
    TextView date;
    TextView Size;


    public ViewHolder(final View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        name=itemView.findViewById(R.id.name);
        date=itemView.findViewById(R.id.date);
        Size=itemView.findViewById(R.id.size);




    }



}
