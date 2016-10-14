package com.example.dell.es.ui.iteminfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.es.R;
import com.example.dell.es.model.Item;
import com.example.dell.es.ui.service.ServerSyncService;
import com.example.dell.es.utils.Constant;
import com.squareup.picasso.Picasso;

/**
 * Created by dell on 10/2/2016.
 */
public class DetailItem extends AppCompatActivity implements View.OnClickListener {
    private TextView name, price, description;
    private Button order;
    private ImageView imageItem1, imageItem2, imageItem3;
    private String[] arr;
    private Item item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item);
       item = getIntent().getParcelableExtra("item");
        arr=new String[3];

        imageItem1 = (ImageView) findViewById(R.id.itemImage1);
        imageItem1.setOnClickListener(this);
        imageItem2 = (ImageView) findViewById(R.id.itemImage2);
        imageItem2.setOnClickListener(this);
        imageItem3 = (ImageView) findViewById(R.id.itemImage3);
        imageItem3.setOnClickListener(this);

        order = (Button) findViewById(R.id.wishListButton);
        order.setOnClickListener(this);
        name = (TextView) findViewById(R.id.textName);
        price = (TextView) findViewById(R.id.textPrice);
        description = (TextView) findViewById(R.id.textDescr);
        name.setText(item.getName());
        price.setText(item.getDescription());

        arr[0]=item.getImageUri();


        Picasso.with(this).load(item.getImageUri()).into(imageItem1);


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter(Constant.FILTER));
        Intent intent = new Intent(this, ServerSyncService.class);
        intent.putExtra("nameItem",item.getName());
        startService(intent);

    }


    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((mMessageReceiver), new IntentFilter(Constant.FILTER));
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onStop();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            handleMessage(intent);
        }
    };

    private void handleMessage(Intent msg) {
        Bundle data = msg.getExtras();
        String[] array = data.getStringArray(Constant.DATA);
        description.setText(array[2].trim());
        Picasso.with(this).load(array[0].trim()).into(imageItem2);
        arr[1]=array[0].trim();
        Picasso.with(this).load(array[1].trim()).into(imageItem3);
        arr[2]=array[1].trim();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.itemImage1 || v.getId() == R.id.itemImage2 || v.getId() == R.id.itemImage3) {
            Intent intent=new Intent(DetailItem.this,ImageSwipper.class);
            intent.putExtra("arr",arr);
            startActivity(intent);

        }
        else if(v.getId()==R.id.wishListButton){
            Intent intent=new Intent(DetailItem.this,Buy.class);
            intent.putExtra("buyItem",item);
            startActivity(intent);

        }

    }
}
