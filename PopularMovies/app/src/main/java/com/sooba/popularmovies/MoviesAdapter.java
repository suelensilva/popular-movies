package com.sooba.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sooba.popularmovies.model.Movie;

import java.util.List;

/**
 * A recycler view adapter to show a grid of popular movies
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    List<Movie> movies;


    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.mTitle.setText(movies.get(position).getTitle());
        holder.mSubtitle.setText(movies.get(position).getSubtitle());
    }

    public void setData(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        if(movies == null)
            return 0;

        return movies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        public final TextView mTitle;
        public final TextView mSubtitle;

        public MovieHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            mSubtitle = (TextView) itemView.findViewById(R.id.tv_movie_subtitle);
        }
    }
}
