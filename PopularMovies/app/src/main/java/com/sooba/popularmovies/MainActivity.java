package com.sooba.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.sooba.popularmovies.model.Movie;
import com.sooba.popularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        moviesAdapter = new MoviesAdapter(new MoviesAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                startActivity(intent);
            }
        });

        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Movie movie = new Movie();
            movie.setTitle("Movie "+i);
            movie.setSubtitle("Subtitle...");
            movies.add(movie);
        }
        moviesAdapter.setData(movies);
        moviesRecyclerView.setAdapter(moviesAdapter);

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

    public class FetchMoviesAsyncTask extends AsyncTask<URL, Void, String> {

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
        protected void onPostExecute(String s) {
            Log.d(TAG, s);
        }
    }
}
