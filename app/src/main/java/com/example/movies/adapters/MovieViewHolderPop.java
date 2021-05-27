package com.example.movies.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;

public class MovieViewHolderPop extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Widgets
    TextView title;
    ImageView imageView;


    //Click listener
    OnMovieListenerPop onMovieListenerPop;
    public MovieViewHolderPop(@NonNull View itemView, OnMovieListenerPop onMovieListenerPop) {
        super(itemView);


        this.onMovieListenerPop = onMovieListenerPop;
        title = itemView.findViewById(R.id.movie_title);
        imageView = itemView.findViewById(R.id.movie_img);


        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        onMovieListenerPop.onMovieClickPop(getAbsoluteAdapterPosition());

    }
}