package com.example.movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.adapters.MoviePopularAdapter;
import com.example.movies.adapters.MovieTopRatedAdapter;
import com.example.movies.adapters.OnMovieListenerPop;
import com.example.movies.adapters.OnMovieListenerTop;
import com.example.movies.models.Genre;
import com.example.movies.models.Movie;
import com.example.movies.viewmodels.MovieListViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements OnMovieListenerPop, OnMovieListenerTop {


    //RecyclerView
    private RecyclerView recyclerView_popular;
    private RecyclerView recyclerView_top;


    private MoviePopularAdapter moviePopularRecyclerAdapter;
    private MovieTopRatedAdapter movieTopRatedRecyclerAdapter;

    //ViewModel
    private MovieListViewModel movieListViewModel;

    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler_layout";
    private Parcelable savedRecyclerLayoutState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            recyclerView_popular.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }

        if (savedInstanceState != null) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            recyclerView_top.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView_popular = findViewById(R.id.recyclerViewPop);
        recyclerView_top = findViewById(R.id.recyclerViewTop);


        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();

        //Calling the observers
        ObservePopularMovies();

        ObserveTopRatedMovies();

        //Getting Popular Movies
        movieListViewModel.searchMoviesPop("query", 1);

        //Getting Top Rated Movies
        movieListViewModel.searchMoviesTop("query", 1);


    }

    private void ObservePopularMovies() {
        movieListViewModel.getPop().observe(this, movieModels -> {
            //Observing for any data change
            if (movieModels != null) {
                for (Movie movie : movieModels) {
                    moviePopularRecyclerAdapter.setMoviesPop(movieModels);
                }
            }

        });
    }


    private void ObserveTopRatedMovies() {
        movieListViewModel.getTop().observe(this, movieModels -> {
            //Observing for any data change
            if (movieModels != null) {
                for (Movie movie : movieModels) {
                    movieTopRatedRecyclerAdapter.setMoviesTop(movieModels);
                }
            }

        });
    }

    // 5 - Initializing RecyclerView and adding data to it
    private void ConfigureRecyclerView() {

        moviePopularRecyclerAdapter = new MoviePopularAdapter(this);
        movieTopRatedRecyclerAdapter = new MovieTopRatedAdapter(this);


        recyclerView_popular.setAdapter(moviePopularRecyclerAdapter);
        recyclerView_popular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerView_top.setAdapter(movieTopRatedRecyclerAdapter);
        recyclerView_top.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //RecyclerView Pagination
        //Loading next page of api response
        recyclerView_popular.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView_popular, int newState) {
                if (!recyclerView_popular.canScrollVertically(1)) {
                    //To display the next search results on the next page of api
                    movieListViewModel.searchNextPagePop();
                }
            }
        });

        recyclerView_top.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView_top, int newState) {
                if (!recyclerView_top.canScrollVertically(1)) {
                    //To display the next search results on the next page of api
                    movieListViewModel.searchNextPageTop();
                }
            }
        });

    }

    @Override
    public void onMovieClickPop(int position) {
        Intent intent = new Intent(this, MovieDetailsPop.class);
        intent.putExtra("movie", moviePopularRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onMovieClickTop(int position) {
        Intent intent = new Intent(this, MovieDetailsTop.class);
        intent.putExtra("movie", movieTopRatedRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView_popular.getLayoutManager()
                .onSaveInstanceState());

        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView_top.getLayoutManager()
                .onSaveInstanceState());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}