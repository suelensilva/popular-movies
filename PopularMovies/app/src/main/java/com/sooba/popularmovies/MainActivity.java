package com.sooba.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.sooba.popularmovies.model.Movie;
import com.sooba.popularmovies.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String RESULT_KEY = "results";

    private ProgressBar mProgressBar;

    private List<Movie> mMovies;
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        moviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        moviesAdapter = new MoviesAdapter(this, new MoviesAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("movie", mMovies.get(clickedItemIndex));
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(layoutManager);

        moviesRecyclerView.setHasFixedSize(true);

        new FetchMoviesAsyncTask().execute(NetworkUtils.buildMoviesUrl(NetworkUtils.MOST_POPULAR_LIST, getString(R.string.api_key)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if(id == R.id.action_most_popular) {
            new FetchMoviesAsyncTask().execute(NetworkUtils.buildMoviesUrl(NetworkUtils.MOST_POPULAR_LIST, getString(R.string.api_key)));
        } else if (id == R.id.action_top_rated) {
            new FetchMoviesAsyncTask().execute(NetworkUtils.buildMoviesUrl(NetworkUtils.TOP_RATED_LIST, getString(R.string.api_key)));
        }
        return true;
    }

    public class FetchMoviesAsyncTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String moviesResponseJson = null;

            try {
                moviesResponseJson = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return moviesResponseJson;
        }

        @Override
        protected void onPostExecute(String response) {
            Log.d(TAG, response);

            // TODO handle error
            mProgressBar.setVisibility(View.INVISIBLE);

            if(!TextUtils.isEmpty(response)) {

                mMovies = new ArrayList<>();
                try {
                    JSONObject responseJsonObj = new JSONObject(response);

                    if(responseJsonObj.has(RESULT_KEY)) {
                        JSONArray results = responseJsonObj.getJSONArray(RESULT_KEY);

                        for(int i = 0; i < results.length(); i++) {
                            JSONObject movieJsonObj = results.getJSONObject(i);
                            Movie movie = new Movie(movieJsonObj);
                            mMovies.add(movie);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                moviesAdapter.setData(mMovies);
                moviesRecyclerView.setAdapter(moviesAdapter);
                moviesAdapter.notifyDataSetChanged();
            }
        }
    }
}
