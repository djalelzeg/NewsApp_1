package com.example.android.newsapp_1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

/**
 * Created by djalél on 30/06/2018.
 */

public class FootballLoader extends AsyncTaskLoader<List<Football>> {
    /** Query URL */
    private String mUrl;
    /**
     * Constructs a new {@link FootballAdapter}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public FootballLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    /**
     * This is on a background thread.
     */
    @Override
    // Perform the network request, parse the response, and extract a list of football news.
    public List<Football> loadInBackground() {
        if(mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Football> footballs = QueryUtils.fetchNewsData(mUrl);

        return footballs;
    }
}
