package com.example.dell.es.ui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dell.es.model.Item;
import com.example.dell.es.utils.Constant;


public class ItemDbAdapter {
    private static final String DATABASE_NAME = "Store";
    private static final String SQLITE_TABLE = "Item";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private final Context mCtx;

    public ItemDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    Constant.KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    Constant.KEY_NAME + " ," +
                    Constant.KEY_DESCRIPTION + " ," +
                    Constant.KEY_PRICE + " ," +
                    Constant.KEY_IMAGEURI + ");";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }



    public ItemDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public void createItem(Item item) {
        open();
        ContentValues initialValues = new ContentValues();
        initialValues.put(Constant.KEY_NAME, item.getName());
        initialValues.put(Constant.KEY_PRICE, item.getPrice());
        initialValues.put(Constant.KEY_IMAGEURI, item.getImageUri());
        initialValues.put(Constant.KEY_DESCRIPTION, item.getDescription());
        mDb.insert(SQLITE_TABLE, null, initialValues);
        close();
    }

    public boolean deleteAllCountries() {
open();
        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        close();
        return doneDelete > 0;

    }

    public Cursor fetchItemsByName(String inputText) throws SQLException {
        open();
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {Constant.KEY_ROWID,
                            Constant.KEY_NAME, Constant.KEY_PRICE, Constant.KEY_DESCRIPTION, Constant.KEY_IMAGEURI},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {Constant.KEY_ROWID,
                            Constant.KEY_NAME, Constant.KEY_PRICE, Constant.KEY_DESCRIPTION, Constant.KEY_IMAGEURI},
                    Constant.KEY_NAME + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        close();
        return mCursor;

    }

    public Cursor fetchAllItems() {
        open();
        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {Constant.KEY_ROWID,
                        Constant.KEY_NAME, Constant.KEY_PRICE, Constant.KEY_DESCRIPTION,Constant.KEY_IMAGEURI},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        close();
        return mCursor;

    }

    public void insertSomeCountries() {
Item item=new Item("G.P.M.H. POWER CONTROL","TITANIUM AND STAINLESS STEEL","17770",
        "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/1/5/158569-3001_01_4.jpg");
        createItem(item);
        Item item1=new Item("HAPPY DREAMS PENDANT","18-CARAT WHITE GOLD AND DIAMONDS","23480",
               "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/7/9/799769-1003_01.jpg" );
        createItem(item1);
        Item item2=new Item("L.U.C XPS 1860 EDITION","18-CARAT ROSE GOLD","30250",
                "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/1/6/161946-5001_01.jpg");
        createItem(item2);
        Item item3=new Item("ICE CUBE RING","18-CARAT ROSE GOLD AND DIAMOND","22555",
                "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/_/8/_827702-5069_01.jpg");
        createItem(item3);
        Item item4=new Item("IMPERIALE COCKTAIL SAUTOIR NECKLACE","18-CARAT ROSE GOLD, AMETHYSTS AND DIAMONDS","16554",
                "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/8/1/819570-5001_01_2.jpg");
        createItem(item4);
        Item item5=new Item("CHOPARDISSIMO RING","18-CARAT ROSE GOLD AND DIAMONDS","22558",
                "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/_/8/_826580-5210_01.jpg");
        createItem(item5);
        Item item6=new Item("HAPPY DREAMS 36 MM","18-CARAT WHITE GOLD AND DIAMONDS","44000",
                "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/2/0/204445-1001_01.jpg");
        createItem(item6);
        Item item7=new Item("MILLE MIGLIA GTS POWER CONTROL","STAINLESS STEEL","10001",
                "http://objects.chopard.com/media/catalog/product/cache/2/thumbnail/361x510/9df78eab33525d08d6e5fb8d27136e95/1/6/168566-3001_01_4.jpg");
        createItem(item7);


    }
}