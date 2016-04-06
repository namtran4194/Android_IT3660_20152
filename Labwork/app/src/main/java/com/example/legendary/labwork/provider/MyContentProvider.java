package com.example.legendary.labwork.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.legendary.labwork.MyDBHandler;

/**
 * Created by Legendary on 06/04/2016.
 */
public class MyContentProvider extends ContentProvider {
    private MyDBHandler dbHandler;
    private static final String AUTHORITY = "com.example.legendary.labwork.provider.MyContentProvider";
    private static final String PRODUCTS_TABLE = "products";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PRODUCTS_TABLE);
    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE, PRODUCTS);
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE + "/#", PRODUCTS_ID);
    }

    @Override
    public boolean onCreate() {
        dbHandler = new MyDBHandler(getContext(), null, null, 1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MyDBHandler.TABLE_PRODUCTS);
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case PRODUCTS_ID:
                queryBuilder.appendWhere(MyDBHandler.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            case PRODUCTS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }
        Cursor cursor = queryBuilder.query(dbHandler.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);

        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case PRODUCTS:
                id = sqlDB.insert(MyDBHandler.TABLE_PRODUCTS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(PRODUCTS_TABLE + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        int rowsDeleted = 0;

        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();
        switch (uriType) {
            case PRODUCTS:
                rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, selection, selectionArgs);
                break;

            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, MyDBHandler.COLUMN_ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(MyDBHandler.TABLE_PRODUCTS, MyDBHandler.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        int rowsUpdated = 0;

        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();
        switch (uriType) {
            case PRODUCTS:
                rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, values, selection, selectionArgs);
                break;
            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, values, MyDBHandler.COLUMN_ID + "=" + id, null);
                } else {
                    rowsUpdated = sqlDB.update(MyDBHandler.TABLE_PRODUCTS, values, MyDBHandler.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
