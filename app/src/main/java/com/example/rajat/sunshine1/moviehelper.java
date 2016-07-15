package com.example.rajat.sunshine1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rajat on 2/2/02.
 */
public class moviehelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="moviedatabase.db";

            public moviehelper(Context context){
                super(context,DATABASE_NAME,null,DATABASE_VERSION);
            }

    @Override
    public void onCreate(SQLiteDatabase sqlitedatabase) {
         final String CREATE_MOVIE_TABLE = "CREATE TABLE " +moviecontract.movieobjectcolums.TABLE_NAME+"("+
                moviecontract.movieobjectcolums._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                moviecontract.movieobjectcolums.COLUMN_BACKDROPPATH+" TEXT NOT NULL,"+
                moviecontract.movieobjectcolums.TABLE_NAME+" TEXT NOT NULL,"+
                moviecontract.movieobjectcolums.COLUMN_ID+" TEXT NOT NULL,"+
                moviecontract.movieobjectcolums.COLUMN_OVERVIEW+" TEXT NOT NULL,"+
                moviecontract.movieobjectcolums.COLUMN_RATING+" REAL NOT NULL,"+
                moviecontract.movieobjectcolums.COLUMN_RELEASEDATE+" TEXT NOT NULL,"+
                moviecontract.movieobjectcolums.COLUMN_POSTERPATH+" TEXT NOT NULL,"+
                moviecontract.movieobjectcolums.COLUMN_TITLE+" TEXT NOT NULL"+");";
        sqlitedatabase.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlitedatabase,int previous_version,int new_version){
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS"+moviecontract.movieobjectcolums.TABLE_NAME);
        onCreate(sqlitedatabase);
    }



}
