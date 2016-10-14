package com.example.dell.es.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dell.es.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImagePickActivity extends AppCompatActivity implements View.OnClickListener{

  private Button takePictureButton,buttonInfo,send;
    private ImageView imageOfCamera;
    private Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_pick_layout);
        buttonInfo= (Button) findViewById(R.id.btn_info);
        buttonInfo.setOnClickListener(this);
        send= (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
        takePictureButton= (Button) findViewById(R.id.takePic);
        takePictureButton.setOnClickListener(this);

        imageOfCamera = (ImageView) findViewById(R.id.cameraPick);
        Picasso.with(this).load(R.drawable.gift).into(imageOfCamera);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }

        }
    }




    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "E&S");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("E&S", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageOfCamera.setImageURI(file);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_info){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            FragmentInfoExcursion newCustomFragment =new FragmentInfoExcursion();
            transaction.replace(R.id.conteiner, newCustomFragment );
            transaction.commit();
            send.setVisibility(View.GONE);
            takePictureButton.setVisibility(View.GONE);


        }
       else if(v.getId()==R.id.send){
            Intent intent = new Intent();
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
            Notification noti = new Notification.Builder(this)
                    .setTicker("E&S")
                    .setContentTitle("Campaign")
                    .setContentText("Good luck!")
                    .setSmallIcon(R.drawable.diamond)
                    .setContentIntent(pIntent).getNotification();
            noti.flags=Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(0, noti);
        }
        else if(v.getId()==R.id.takePic){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            file = Uri.fromFile(getOutputMediaFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

            startActivityForResult(intent, 100);
        }

    }
}
