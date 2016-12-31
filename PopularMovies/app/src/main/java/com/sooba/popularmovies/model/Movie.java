package com.sooba.popularmovies.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

    /* EXAMPLE OF A MOVIE STRUCTURE
    "":"\/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg",
		"adult":false,
		"overview":"A rogue band of resistance fighters unite for a mission to steal the Death Star plans and bring a new hope to the galaxy.",
		"release_date":"2016-12-14",
		"genre_ids":[28,12,14,878,53,10752],
		"id":330459,
		"original_title":"Rogue One: A Star Wars Story",
		"original_language":"en",
		"title":"Rogue One: A Star Wars Story",
		"backdrop_path":"\/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg",
		"popularity":216.524868,
		"vote_count":1101,
		"video":false,
		"vote_average":7.3
     */

    private static final String POSTER_PATH_KEY = "poster_path";
    private static final String OVERVIEW_KEY = "overview";
    private static final String RELEASE_DATE_KEY = "release_date";
    private static final String ORIGINAL_TITLE_KEY = "original_title";
    private static final String ORIGINAL_LANGUAGE_KEY = "original_language";
    private static final String TITLE_KEY = "title";

    private String posterPath;
    private String overview;
    private String releaseDate;
    private String originalTitle;
    private String originalLanguage;
    private String title;

    public Movie(JSONObject jsonMovie) throws JSONException {
        if(jsonMovie.has(POSTER_PATH_KEY)) {
            posterPath = jsonMovie.getString(POSTER_PATH_KEY);
        }
        if(jsonMovie.has(OVERVIEW_KEY)) {
            overview = jsonMovie.getString(OVERVIEW_KEY);
        }
        if(jsonMovie.has(RELEASE_DATE_KEY)) {
            releaseDate = jsonMovie.getString(RELEASE_DATE_KEY);
        }
        if(jsonMovie.has(ORIGINAL_TITLE_KEY)) {
            originalTitle = jsonMovie.getString(ORIGINAL_TITLE_KEY);
        }
        if(jsonMovie.has(ORIGINAL_LANGUAGE_KEY)) {
            originalLanguage = jsonMovie.getString(ORIGINAL_LANGUAGE_KEY);
        }
        if(jsonMovie.has(TITLE_KEY)) {
            title = jsonMovie.getString(TITLE_KEY);
        }
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
