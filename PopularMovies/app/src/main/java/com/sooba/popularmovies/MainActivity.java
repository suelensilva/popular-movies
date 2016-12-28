package com.sooba.popularmovies;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.sooba.popularmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesRecyclerView = (RecyclerView) findViewById(R.id.rv_movies);
        moviesAdapter = new MoviesAdapter();

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.movies, menu);
        return true;
    }
}
