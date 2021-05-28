package com.example.movies.repositories;

import androidx.lifecycle.LiveData;

import com.example.movies.models.Movie;
import com.example.movies.request.MovieApiClient;

import java.util.List;

//Repository class
public class MovieRepository {

    //Singleton pattern
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance(){
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getPop() {
        return movieApiClient.getMoviesPop();}

    public LiveData<List<Movie>> getTop() {
        return movieApiClient.getMoviesTop();}

    //2 - Calling the method in repository
    public void searchMoviesPop(String query, int pageNumber) {

        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPop(query, pageNumber);
    }

    public void searchMoviesTop(String query, int pageNumber) {

        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesTop(query, pageNumber);
    }


    public void searchNextPagePop() {searchMoviesPop(mQuery, mPageNumber+1); }

    public void searchNextPageTop() {searchMoviesTop(mQuery, mPageNumber+1); }


}

