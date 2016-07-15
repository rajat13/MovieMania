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
public class reviewtask extends AsyncTask<String, Void, List<revieobject>> {
    private String TAG;
    private static final String Base_string = "http://api.themoviedb.org/3/movie";
    private static final String api_key = "388e5684ea2f3bb8de279874cb6990a5";
    private static final String AUTHOR_QUERY="author";
    private static final String CONTENT_QUERY="content";
    final String REVIEWS="reviews";
    final String API_KEY_QUERY="api_key";

    // Parse json data to get arraylist of reviewobjects

    private List<revieobject> getparseddata(String forecastjsonstr)
            throws JSONException {
        final String START_OF_JSON = "results";
        JSONObject startlist = new JSONObject(forecastjsonstr);
        JSONArray startarray = startlist.getJSONArray(START_OF_JSON);
        int noofobjects = startarray.length();
        List<revieobject> objectarray = new ArrayList<>();
        for (int i = 0; i < noofobjects; i++) {
            JSONObject object = startarray.getJSONObject(i);
            String author=object.getString(AUTHOR_QUERY);
            String content=object.getString(CONTENT_QUERY);
            revieobject finalobject = new revieobject(author,content);
            objectarray.add(finalobject);
        }

        return objectarray;

    }

    //network will be acessed in doinbackground method and json result will be passes to onpostexecute

    protected List<revieobject> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        List<revieobject> finalobjectarray = new ArrayList<>();

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            String str=Base_string+"/"+params[0]+"/"+REVIEWS+"?api_key="+api_key;
            Log.i("hell",str);
            URL url=new URL(str);


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

        return finalobjectarray; //final movieobject array
    }


    //onPostExecute method of asynctask

    protected void onPostExecute(List<revieobject> results) {
    }}
