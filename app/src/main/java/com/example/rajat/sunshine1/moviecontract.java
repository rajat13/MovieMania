package com.example.rajat.sunshine1;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rajat on 2/2/02.
 */
public class moviecontract {


    public static final String CONTENT_AUTHORITY="com.example.rajat.sunshine1";
    public static final Uri CONTENT_BASE_URI= Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final class movieobjectcolums implements BaseColumns {
        public static final Uri CONTENT_URI = CONTENT_BASE_URI.buildUpon().appendPath("movies").build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + "movies";
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + "movies";

        public static final String TABLE_NAME = "movietable";
        public static final String COLUMN_ID = "movieid";
        public static final String COLUMN_OVERVIEW = "movieoverview";
        public static final String COLUMN_POSTERPATH = "posterpath";
        public static final String COLUMN_TITLE = "movietitle";
        public static final String COLUMN_RATING = "movierating";
        public static final String COLUMN_RELEASEDATE = "releasedate";
        public static final String COLUMN_BACKDROPPATH = "backdroppath";
        public static Uri getmovieuriwithid(String id){
            return Uri.withAppendedPath(movieobjectcolums.CONTENT_URI,id);
    }}
}
