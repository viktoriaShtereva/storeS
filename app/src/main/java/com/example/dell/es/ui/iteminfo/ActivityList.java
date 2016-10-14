package com.example.dell.es.ui.iteminfo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.dell.es.R;
import com.example.dell.es.model.Item;
import com.example.dell.es.ui.adapter.ItemDbAdapter;
import com.example.dell.es.utils.Constant;
import com.squareup.picasso.Picasso;

public class ActivityList extends AppCompatActivity {
    private ItemDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

    private SimpleCursorAdapter getDataAdapter() {
        return dataAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);

        dbHelper = new ItemDbAdapter(this);

        dbHelper.deleteAllCountries();


        //Add some data
        dbHelper.insertSomeCountries();

        //Generate ListView from SQLite Database
        displayListView();

    }

    private void displayListView() {
        Cursor cursor = dbHelper.fetchAllItems();

        // The desired columns to be bound
        final String[] columns = new String[] {
                Constant.KEY_NAME,
                Constant.KEY_DESCRIPTION,
                Constant.KEY_IMAGEURI
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.nameItem,
                R.id.descriptionItem,
                R.id.imageItem

        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(this, R.layout.item_list_info_layout,
                cursor,
                columns,
                to,
                0);

        getDataAdapter().setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                String imageUri =cursor.getString(cursor.getColumnIndexOrThrow("imageUri"));
                if(view.getId()== R.id.imageItem){

                    ImageView imageView = (ImageView) view.findViewById(R.id.imageItem);
                    Picasso.with(view.getContext()).load(imageUri).into(imageView);
                    return true;
                }
                return false;

            }
        });



        ListView listView = (ListView) findViewById(R.id.listViewItems);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                // Get the state's capital from this row in the database.
                String name =
                        cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String price =
                        cursor.getString(cursor.getColumnIndexOrThrow("price"));
                String description =
                        cursor.getString(cursor.getColumnIndexOrThrow("description"));

                String imageUri =cursor.getString(cursor.getColumnIndexOrThrow("imageUri"));

                Item item=new Item(name,description,price,imageUri);
                Intent intent=new Intent(ActivityList.this,DetailItem.class);
                intent.putExtra("item",item);
                startActivity(intent);

            }
        });



        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return dbHelper.fetchItemsByName(constraint.toString());
            }
        });

    }



}
