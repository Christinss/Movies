package com.example.movies;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.movies.models.MovieModel;

public class MovieDetailsTop extends AppCompatActivity {
    //Widgets
    private ImageView imageViewDetailsTop, posterImageTop;
    private TextView titleDetailsTop, descDetailsTop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_top);

        Toolbar toolbarDetails = findViewById(R.id.toolbar_details_top);
        setSupportActionBar(toolbarDetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        imageViewDetailsTop = findViewById(R.id.imageView_details_top);
        titleDetailsTop = findViewById(R.id.tv_title_details_top);
        descDetailsTop = findViewById(R.id.tv_desc_details_top);

        GetDataFromIntent();
    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            titleDetailsTop.setText(movieModel.getTitle());
            descDetailsTop.setText(movieModel.getMovie_overview());

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"
                            + movieModel.getPoster_path())
                    .into(imageViewDetailsTop);


        }
    }
}
