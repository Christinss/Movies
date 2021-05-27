package com.example.movies.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;

public class MovieViewHolderTop extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Widgets
        TextView title;
        ImageView imageView;


        //Click listener
        OnMovieListenerTop onMovieListenerTop;
        public MovieViewHolderTop(@NonNull View itemView, OnMovieListenerTop onMovieListenerTop) {
            super(itemView);

            this.onMovieListenerTop = onMovieListenerTop;
            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.movie_img);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onMovieListenerTop.onMovieClickTop(getAbsoluteAdapterPosition());

        }

}
