package com.example.dell.es.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dell.es.R;

/**
 * Created by dell on 10/14/2016.
 */
public class ContactActivity extends AppCompatActivity implements View.OnClickListener{
    TextView telSofia,placeSofia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);
        telSofia= (TextView) findViewById(R.id.sofiaNumber);
        telSofia.setText("0899999988");
        telSofia.setOnClickListener(this);
        placeSofia= (TextView) findViewById(R.id.sofiaPlace);
        placeSofia.setText("12 Narodno Sabranie Square");
        placeSofia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sofiaNumber){
            Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:+35987664338"));
            startActivity(intent);

        }
        else if(v.getId()==R.id.sofiaPlace){
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.bg/maps/place/%D0%BF%D0%BB.+%E2%80%9E%D0%9D%D0%B0%D1%80%D0%BE%D0%B4%D0%BD%D0%BE+%D1%81%D1%8A%D0%B1%D1%80%D0%B0%D0%BD%D0%B8%D0%B5%E2%80%9C+12,+1000+%D0%A1%D0%BE%D1%84%D0%B8%D1%8F/@42.6940921,23.3294032,17z/data=!3m1!4b1!4m5!3m4!1s0x40aa857155cf9c31:0x4b1893d8aab125b3!8m2!3d42.6940921!4d23.3315919"));
            startActivity(intent);
        }

    }
}
