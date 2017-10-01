package com.salman.imagerec;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<Items> list;


    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(List<Items> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        final Items items=list.get(position);
        holder.name.setText(items.getName());
        holder.date.setText(items.getDate());
        holder.Size.setText(items.getSize());
        holder.imageView.setImageBitmap(items.getImage());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageUri(holder.imageView.getContext(),items.getImage());
              }
        });

        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog alertDialog= new AlertDialog.Builder(holder.imageView.getContext()).create();
                alertDialog.setTitle("Are You Sure Want to Delete This Image ???");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.cancel();
                        removeItem(position);

                    }
                });
                alertDialog.show();
                return false;
            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   public void removeItem(int position){

       list.remove(position);
       notifyItemRemoved(position);

    }


    // Method that takes two parameter ,it passes a bitmap then compress it
    //and show it to gallery
    public void getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path), "image/*");

        inContext.startActivity(intent);

    }

}
