package com.sooba.popularmovies;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sooba.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    // TODO remove this constant, because already exists in MainActivity
    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";

    private Movie mMovie;

    private TextView tvTitle;
    private ImageView ivPoster;
    private TextView tvOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvTitle = (TextView) findViewById(R.id.tv_detail_title);
        ivPoster = (ImageView) findViewById(R.id.iv_detail_poster);
        tvOverview = (TextView) findViewById(R.id.tv_detail_overview);

        Intent intent = getIntent();
        if(intent.hasExtra("movie")) {
            mMovie = (Movie) intent.getSerializableExtra("movie");

            tvTitle.setText(mMovie.getTitle());
            tvOverview.setText(mMovie.getOverview());


            Picasso.with(this).load(POSTER_BASE_URL+mMovie.getPosterPath()).into(ivPoster);
        }
    }
}
