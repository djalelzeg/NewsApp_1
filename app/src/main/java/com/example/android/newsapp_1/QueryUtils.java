package com.example.android.newsapp_1;

/**
 * Created by djalél on 29/06/2018.
 */

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public class QueryUtils {
    /**
     * Query the USGS dataset and return a list of {@link Football} objects.
     */
    public static List<Football> fetchNewsData(String stringUrl) {
        if(stringUrl == null) {
            return null;
        }
// Create URL object
        URL url = createUrl(stringUrl);
        String jsonResponse = makeHttpRequest(url);
// Extract relevant fields from the JSON response and create a list of {@link football}s
        List<Football> footballList = extractDataFromJson(jsonResponse);
// Return the list of {@link football}s
        return footballList;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        if (stringUrl == null) {
            return null;
        }

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

     /**
      * Make an HTTP request to the given URL and return a String as the response.
      */
    private static String makeHttpRequest(URL url) {
        HttpURLConnection urlConnection = null;
        String jsonResponse = "";
        InputStream inputStream = null;
        // If the URL is null, then return early.
        if(url == null) {
            return null;
        }

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
        // If the request was successful (response code 200),
        // then read the input stream and parse the response.
            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "makeHttpRequest: "+jsonResponse);
        return jsonResponse;
    }
    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream (InputStream inputStream) {
        InputStreamReader streamReader = null;
        StringBuilder result = new StringBuilder() ;
        BufferedReader bufferedReader = null;

        streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        bufferedReader = new BufferedReader(streamReader);

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                result.append(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
    /**
     * Return a list of {@link Football} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Football> extractDataFromJson (String jsonResponse) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
     // Create an empty ArrayList that we can start adding footballs news to
        List<Football> footballList = new ArrayList<>();
        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
        // Create a JSONObject from the JSON response string
            JSONObject json = new JSONObject(jsonResponse);
        // Extract the JSONobject associated with the key called "response",
            // which represents an object of response (or football news).
            JSONObject response = json.getJSONObject("response");
            // Extract the JSONaray associated with the key called "results",
            // which represents an aray of results (or football news).
            JSONArray results = response.getJSONArray("results");
// For each football news in the footballobject, create an {@link football} object
            for (int i = 0; i < results.length(); i++) {
// Get a single football news at position i within the list of football news
                JSONObject currentNews = results.getJSONObject(i);
                // Extract the value for the key called "webtitle"
                String title = currentNews.getString("webTitle");
                // Extract the value for the key called "sectionName"
                String section = currentNews.getString("sectionName");
                // Extract the value for the key called "webpublication"
                // Extract the value for the key called "webPublicationate"
                String date = currentNews.getString("webPublicationDate");
                // Extract the value for the key called "webUrl"
                String url = currentNews.getString("webUrl");
                // Extract the value for the key called "author"
                String tags = currentNews.getString("webTitle");
          // Create a new {@link football} object with the title, date, url,
                // and type and section from the JSON response.
                Football footballObject = new Football(title, date, url, tags, section );
                // Add the new {@link football} to the list of footballs.
                footballList.add(footballObject);
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        // Return the list of football news
        return footballList;
    }
}
