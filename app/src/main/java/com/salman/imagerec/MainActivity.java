package com.salman.imagerec;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity {


    RecyclerView rec;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Items> list = new ArrayList<>();
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rec= (RecyclerView) findViewById(R.id.recView);
        Logger.addLogAdapter(new AndroidLogAdapter());
        ///Creating a directory to save photos
        File fi = new File(Environment.getExternalStorageDirectory() + "/PhotosOfSalman");
        if (fi.isDirectory()) {

        }else {
            Toast.makeText(getApplicationContext(),"Nothing to show",Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //starting camera :D
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 000);


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if the result is capturing Image
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                //receiving image and saving in storage
                bitmap = (Bitmap) data.getExtras().get("data");
                 savePhoto(bitmap);
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();

            }
        }

    }

    public void savePhoto(Bitmap bitmap){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String  name = simpleDateFormat.format(new Date());

        String  directory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(directory+"/PhotosOfSalman");
        folder.mkdirs();

        File myFile = new File(folder,name+".JPEG");

        try {
            FileOutputStream stream = new FileOutputStream(myFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
            stream.flush();
            stream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//showing photo in recycler View
    @Override
    protected void onResume() {
        super.onResume();

        if(bitmap != null) {

            SimpleDateFormat name = new SimpleDateFormat("mm_ss");
            String Name = name.format(new Date());

            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            String Date = date.format(new Date());
            int size = bitmap.getByteCount();
            String si = String.valueOf(size);
            Logger.d(si);

            rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerViewAdapter = new RecyclerViewAdapter(list);
            rec.setAdapter(recyclerViewAdapter);
            list.add(new Items(bitmap, "IMG_00" + Name, Date,si));
            Logger.d("onResume state");
        }
        else {
            Toast.makeText(getApplicationContext(),"Nothing To Show",Toast.LENGTH_SHORT).show();
        }



    }
}