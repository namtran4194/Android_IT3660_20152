package com.example.legendary.labwork;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.legendary.labwork.provider.MyContentProvider;

/**
 * Created by Legendary on 06/04/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private ContentResolver myCR;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT_NAME = "productName";
    public static final String COLUMN_QUANTITY = "quantity";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        myCR = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCT_NAME + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProductName());
        values.put(COLUMN_QUANTITY, product.getQuantity());

        myCR.insert(MyContentProvider.CONTENT_URI, values);
    }

    public Product findProduct(String productName) {
        String[] projection = {COLUMN_ID, COLUMN_PRODUCT_NAME, COLUMN_QUANTITY};
        String selection = "productName = \"" + productName + "\"";
        Cursor cursor = myCR.query(MyContentProvider.CONTENT_URI, projection, selection, null, null);

        Product product = new Product();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            product = null;
        }
        return product;
    }

    public boolean deleteProduct(String productName) {
        boolean result = false;

        String selection = "productName = \"" + productName + "\"";
        int rowsDeleted = myCR.delete(MyContentProvider.CONTENT_URI, selection, null);
        if (rowsDeleted > 0)
            result = true;

        return result;
    }
}
