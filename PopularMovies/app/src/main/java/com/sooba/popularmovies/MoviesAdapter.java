package com.sooba.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sooba.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A recycler view adapter to show a grid of popular movies
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";

    Context mContext;
    List<Movie> movies;

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    final private ListItemClickListener mOnClickListener;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public MoviesAdapter (Context context, ListItemClickListener clickListener) {
        mContext = context;
        mOnClickListener = clickListener;
    }


    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        Movie movie = movies.get(position);

        holder.mTitle.setText(movie.getTitle());

        String posterPath = movie.getPosterPath();
        Picasso.with(mContext).load(POSTER_BASE_URL+posterPath).into(holder.mPoster);
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

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mTitle;
        public final ImageView mPoster;

        public MovieHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            mPoster = (ImageView) itemView.findViewById(R.id.iv_movie_poster);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
