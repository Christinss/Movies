package com.example.movies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.movies.models.MovieModel;

public class MovieDetailsPop extends AppCompatActivity {

    //Widgets
    private ImageView imageViewDetailsPop, posterImagePop;
    private TextView titleDetailsPop, descDetailsPop, releaseDatePop, genrePop, movieId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_pop);

        Toolbar toolbarDetails = findViewById(R.id.toolbar_details_pop);
        setSupportActionBar(toolbarDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        imageViewDetailsPop = findViewById(R.id.imageView_details_pop);
        posterImagePop = findViewById(R.id.poster_pop);
        titleDetailsPop = findViewById(R.id.tv_title_details_pop);
        releaseDatePop = findViewById(R.id.release_date_pop);
        genrePop = findViewById(R.id.movie_genre_pop);
        descDetailsPop = findViewById(R.id.tv_desc_details_pop);

        GetDataFromIntent();
    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            titleDetailsPop.setText(movieModel.getTitle());
            releaseDatePop.setText(movieModel.getRelease_date());
            descDetailsPop.setText(movieModel.getMovie_overview());
            genrePop.setText(movieModel.getGenre());

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movieModel.getPoster_path())
                    .into(posterImagePop);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movieModel.getBackdrop_path())
                    .into(imageViewDetailsPop);


        }
    }
}