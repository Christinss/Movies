package com.example.movies;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.movies.models.Movie;

public class MovieDetailsTop extends AppCompatActivity {
    //Widgets

    private ImageView imageViewDetailsTop, posterImageTop;
    private TextView titleDetailsTop, descDetailsTop, releaseDateTop, genresTop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_top);

        Toolbar toolbarDetails = findViewById(R.id.toolbar_details_top);
        setSupportActionBar(toolbarDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbarDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        imageViewDetailsTop = findViewById(R.id.imageView_details_top);
        posterImageTop = findViewById(R.id.poster_top);
        titleDetailsTop = findViewById(R.id.tv_title_details_top);
        releaseDateTop = findViewById(R.id.release_date_top);
        genresTop = findViewById(R.id.movie_genre_top);
        descDetailsTop = findViewById(R.id.tv_desc_details_top);

        GetDataFromIntent();
    }


    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            Movie movie = getIntent().getParcelableExtra("movie");

            titleDetailsTop.setText(movie.getTitle());
            releaseDateTop.setText(movie.getRelease_date());
            descDetailsTop.setText(movie.getMovie_overview());
            //genresTop.setText("" + movie.getGenres());



            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movie.getPoster_path())
                    .into(posterImageTop);

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movie.getBackdrop_path())
                    .into(imageViewDetailsTop);


        }
    }
}
