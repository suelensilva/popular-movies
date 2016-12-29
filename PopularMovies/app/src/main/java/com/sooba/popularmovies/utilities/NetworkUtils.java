package com.sooba.popularmovies.utilities;

import android.net.Uri;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String MOVIES_URL = "https://api.themoviedb.org/3/movie";

    public static final String MOST_POPULAR_LIST = "popular";
    public static final String TOP_RATED_LIST = "top_rated";

    private static final String API_KEY_QUERY = "api_key";
    private static final String LANGUAGE_QUERY = "language";
    private static final String PAGE_QUERY = "page";

    private static final String LANGUAGE_DEFAULT_VALUE = "en-US";
    private static final String PAGE_DEFAULT_VALUE = "1";


    public static URL buildMoviesUrl(String listType, String apiKey) {

        if(TextUtils.isEmpty(listType) || TextUtils.isEmpty(apiKey))
            return null;

        Uri.Builder builder = Uri.parse(MOVIES_URL).buildUpon();
        builder
                .appendPath(listType)
                .appendQueryParameter(API_KEY_QUERY, apiKey)
                .appendQueryParameter(LANGUAGE_QUERY, LANGUAGE_DEFAULT_VALUE)
                .appendQueryParameter(PAGE_QUERY, PAGE_DEFAULT_VALUE);
        Uri uri = builder.build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
