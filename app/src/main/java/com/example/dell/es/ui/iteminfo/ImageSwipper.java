package com.example.dell.es.ui.iteminfo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.es.R;
import com.example.dell.es.ui.adapter.ImageAdapter;

public class ImageSwipper extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_switcher);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        String[] imageInfo = getIntent().getStringArrayExtra("arr");
        ImageAdapter adapter = new ImageAdapter(this,imageInfo);
        viewPager.setAdapter(adapter);


    }
}