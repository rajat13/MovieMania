package com.example.rajat.sunshine1;

/**
 * Created by rajat on 10/7/16.
 */

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.os.AsyncTask;
import android.util.Log;

import com.example.rajat.sunshine1.movieobject;
import com.example.rajat.sunshine1.trailerobject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajat on 10/7/16.
 */
public class trailertask extends AsyncTask<String, Void, List<trailerobject>> {
    private String TAG;
    private static final String Base_string = "http://api.themoviedb.org/3/movie";
    private static final String api_key = "388e5684ea2f3bb8de279874cb6990a5";



    // Parse json data to get arraylist of trailerobjects

    private List<trailerobject> getparseddata(String forecastjsonstr)
            throws JSONException {

        final String START_OF_JSON = "results";
        final String TRAILER_BASEPATH="http://www.youtube.com/watch?v=";
        final String JSON_TRAILER_KEY_IDENTIFIER="key";
        final String TRAILER_IMAGE_BASE_PATH="http://img.youtube.com/vi/";
        final String TRAILER_IMAGE_SUFFIX="/0.jpg";

        JSONObject startlist = new JSONObject(forecastjsonstr);
        JSONArray startarray = startlist.getJSONArray(START_OF_JSON);
        int noofobjects = startarray.length();
        List<trailerobject> objectarray = new ArrayList<>();
        for (int i = 0; i < noofobjects; i++) {
            JSONObject object = startarray.getJSONObject(i);
            String trailerkey = object.getString(JSON_TRAILER_KEY_IDENTIFIER);
            trailerobject trailer = new trailerobject(TRAILER_BASEPATH + trailerkey, TRAILER_IMAGE_BASE_PATH + trailerkey + TRAILER_IMAGE_SUFFIX);
            objectarray.add(trailer);
        }
        return objectarray;

    }

    //network will be acessed in doinbackground method and json result will be passes to onpostexecute

    protected List<trailerobject> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        final String VIDEO="videos";
        final String API_KEY_QUERY="api_key";
        List<trailerobject> finalobjectarray = new ArrayList<>();

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {

            Uri uri=Uri.parse(Base_string)
                    .buildUpon()
                    .appendPath(params[0])
                    .appendPath(VIDEO)
                    .appendQueryParameter(API_KEY_QUERY,api_key)
                    .build();
            URL url=new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.

            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;

            }
            forecastJsonStr = buffer.toString();
            Log.i("hey", "Hello" + forecastJsonStr);
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        try {
            finalobjectarray = getparseddata(forecastJsonStr);
        } catch (JSONException e) {
            Log.e("Json exception", e.getMessage(), e);
            e.printStackTrace();
        }

        return finalobjectarray; //final trailerobject array
    }


    //onPostExecute method of asynctask

    protected void onPostExecute(List<trailerobject> results) {
    }}
