package com.example.rajat.sunshine1;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by rajat on 8/7/16.
 */
public class movieprovider extends ContentProvider {

    public static final int MOVIES=10;
    private static final UriMatcher surimatcher=buildurimatcher();
    private  moviehelper helper;
    public static final int MOVIE_WITH_ID=11;


static UriMatcher buildurimatcher(){
    UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    final String authority=moviecontract.CONTENT_AUTHORITY;
    matcher.addURI(authority,"movies",MOVIES);
    matcher.addURI(authority,"movies"+"/*",MOVIE_WITH_ID);
    return matcher;
}
    @Override
    public String getType(Uri uri){
        final int matcher=surimatcher.match(uri);
        switch (matcher){
            case MOVIES:
                return moviecontract.movieobjectcolums.CONTENT_TYPE;
            case MOVIE_WITH_ID:
                return moviecontract.movieobjectcolums.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("unsupported uri"+uri);
        }

    }

    @Override
    public boolean onCreate(){
         helper=new moviehelper(getContext());
        return true;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values){
        SQLiteDatabase db=helper.getWritableDatabase();
        int match=surimatcher.match(uri);
        Uri returnuri=null;


        if(match!=MOVIES){
            throw new IllegalArgumentException("Unsupppoerted uri for insertion"+uri);
        }
        if(match==MOVIES) {

            long id = db.insert(moviecontract.movieobjectcolums.TABLE_NAME, null, values);
            if (id > 0) {
                returnuri= ContentUris.withAppendedId(moviecontract.movieobjectcolums.CONTENT_URI,id);
            } else {
                throw new SQLiteException("unable to insert row into database");
            }
        }
        getContext().getContentResolver().notifyChange(uri,null);
        db.close();
        return returnuri;
    }

    @Override
    public Cursor query(Uri uri,String[] projection,String selecion,String[] selectionargs,String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        int match = surimatcher.match(uri);
        switch (match) {
            case MOVIE_WITH_ID:
                builder.setTables(moviecontract.movieobjectcolums.TABLE_NAME);
                builder.appendWhere(moviecontract.movieobjectcolums.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            case MOVIES:
                builder.setTables(moviecontract.movieobjectcolums.TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("unsupported uri" + uri);
        }
        Cursor cursor = builder.query(
                db, projection, selecion, selectionargs, null, null, sortOrder
        );
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        db.close();
        return cursor;
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionargs){
        SQLiteDatabase db=helper.getWritableDatabase();
        int deletedrows=0;
        int matcher=surimatcher.match(uri);
        switch(matcher){
            case MOVIE_WITH_ID:
                String idstr=uri.getLastPathSegment();
                String where=moviecontract.movieobjectcolums.COLUMN_ID+"="+idstr;
                if(!TextUtils.isEmpty(selection)){
                    where +="AND"+selection;
                }
                deletedrows=db.delete(moviecontract.movieobjectcolums.TABLE_NAME,where,selectionargs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported uri"+uri);
        }
        if(deletedrows>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return deletedrows;
    }


    @Override
    public int update(Uri uri,ContentValues values,String selection,String[] selectionargs){
        return 0;
    }


















}
