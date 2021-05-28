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

public class MoviePopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMoviesPop;
    private OnMovieListenerPop onMovieListenerPop;

    public MoviePopularAdapter(OnMovieListenerPop onMovieListenerPop) {
        this.onMovieListenerPop = onMovieListenerPop;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_list, parent, false);

        return new MovieViewHolderPop(view, onMovieListenerPop);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        ((MovieViewHolderPop)holder).title.setText(mMoviesPop.get(i).getTitle());

        Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/"
                            + mMoviesPop.get(i).getPoster_path())
                    .into(((MovieViewHolderPop) holder).imageView);


        }

    @Override
    public int getItemCount() {
        if (mMoviesPop != null) {
            return mMoviesPop.size();
        }

        return 0;
    }

    public void setMoviesPop(List<Movie> mMoviesPop) {
        this.mMoviesPop = mMoviesPop;
        notifyDataSetChanged();
    }

    //Getting the id of the movie clicked
    public Movie getSelectedMovie(int position) {
        if (mMoviesPop != null) {
            if (mMoviesPop.size() > 0) {
                return mMoviesPop.get(position);
            }
        }
        return null;
    }

}

