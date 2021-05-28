package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.models.Movie;

import java.util.List;

public class MovieTopRatedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMoviesTop;
    private OnMovieListenerTop onMovieListenerTop;

    public MovieTopRatedAdapter(OnMovieListenerTop onMovieListenerTop) {
        this.onMovieListenerTop = onMovieListenerTop;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_list, parent, false);

        return new MovieViewHolderTop(view, onMovieListenerTop);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        ((MovieViewHolderTop)holder).title.setText(mMoviesTop.get(i).getTitle());

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"
                        + mMoviesTop.get(i).getPoster_path())
                .into(((MovieViewHolderTop)holder).imageView);

    }

    @Override
    public int getItemCount() {
        if (mMoviesTop != null) {
            return mMoviesTop.size();
        }

        return 0;
    }

    public void setMoviesTop(List<Movie> mMoviesTop) {
        this.mMoviesTop = mMoviesTop;
        notifyDataSetChanged();
    }

    public Movie getSelectedMovie(int position) {
        if (mMoviesTop != null) {
            if (mMoviesTop.size() > 0) {
                return mMoviesTop.get(position);
            }
        }
        return null;
    }
}
