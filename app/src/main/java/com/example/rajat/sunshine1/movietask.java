package com.example.rajat.sunshine1;

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
public class movietask extends AsyncTask<String, Void, List<movieobject>> {
    private String TAG;
    private static final String Base_string = "http://api.themoviedb.org/3/movie/";
    private static final String api_key = "388e5684ea2f3bb8de279874cb6990a5";
    private static final String MOVIE_ID="id";
    private static final String MOVIE_OVERVIEW="overview";
    private static final String MOVIE_TITLE="original_title";
    private static final String MOVIE_VOTE="vote_average";
    private static final String MOVIE_POSTERPATH="poster_path";
    private static final String MOVIE_RELEASE_DATE="release_date";
    private static final String MOVIE_BACKDROP_PATH="backdrop_path";

    // Parse json data to get arraylist of movieobjects

    private List<movieobject> getparseddata(String forecastjsonstr)
            throws JSONException {
        final String START_OF_JSON = "results";
        JSONObject startlist = new JSONObject(forecastjsonstr);
        JSONArray startarray = startlist.getJSONArray(START_OF_JSON);
        int noofobjects = startarray.length();
        List<movieobject> objectarray = new ArrayList<>();
        for (int i = 0; i < noofobjects; i++) {
            JSONObject object = startarray.getJSONObject(i);
            movieobject finalobjectarray = new movieobject();
            finalobjectarray.setMovieId(object.getLong(MOVIE_ID));
            finalobjectarray.setOverview(object.getString(MOVIE_OVERVIEW));
            finalobjectarray.setTitle(object.getString(MOVIE_TITLE));
            finalobjectarray.setMovieRating(object.getString(MOVIE_VOTE));
            finalobjectarray.setPosterPath(object.getString(MOVIE_POSTERPATH));
            finalobjectarray.setReleaseDate(object.getString(MOVIE_RELEASE_DATE));
            finalobjectarray.setBackdroppath(object.getString(MOVIE_BACKDROP_PATH)+"/10");
            objectarray.add(finalobjectarray);
        }

        return objectarray;

    }

    //network will be acessed in doinbackground method and json result will be passes to onpostexecute

    protected List<movieobject> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        List<movieobject> finalobjectarray = new ArrayList<>();

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {


            URL url = new URL(Base_string + params[0] + "?api_key="+api_key);


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

    protected void onPostExecute(List<movieobject> results) {
        }}
