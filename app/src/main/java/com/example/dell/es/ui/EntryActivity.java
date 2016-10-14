package com.example.dell.es.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.es.R;
import com.example.dell.es.ui.login.LoginActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

public class EntryActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentEntry fragmentEntry = new FragmentEntry();
        fragmentTransaction.add(R.id.frameLayout, fragmentEntry);
        fragmentTransaction.commit();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.entryActivity);

        BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.entry_menu, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(int itemId) {
                switch (itemId) {
                    case R.id.sign_item:
                            Intent intent=new Intent(EntryActivity.this,LoginActivity.class);
                            startActivity(intent);

                        break;
                    case R.id.favorite_item:
                        Snackbar.make(coordinatorLayout, "Thank you!", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.shared_item:
                      Intent intent1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                        startActivity(intent1);
                        break;
                }
            }
        });

        // Set the color for the active tab. Ignored on mobile when there are more than three tabs.
        bottomBar.setActiveTabColor(R.color.black);

    }
    }


