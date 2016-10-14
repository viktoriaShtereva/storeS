package com.example.dell.es.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.dell.es.R;
import com.example.dell.es.utils.Constant;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, EntryActivity.class);
                startActivity(i);
                finish();
            }
        }, Constant.SPLASH_TIME_OUT);
    }
}
