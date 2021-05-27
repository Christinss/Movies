package com.example.movies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.models.MovieModel;
import com.example.movies.repositories.MovieRepository;

import java.util.List;

//this class is used for ViewModel
public class MovieListViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    //constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    //getter
    public LiveData<List<MovieModel>> getPop() {
        return movieRepository.getPop();
    }

    public LiveData<List<MovieModel>> getTop() {
        return movieRepository.getTop();
    }

    //3 - Calling method in view-model
    public void searchMoviesPop(String query, int pageNumber) {
        movieRepository.searchMoviesPop(query, pageNumber);
    }

    public void searchMoviesTop(String query, int pageNumber) {
        movieRepository.searchMoviesTop(query, pageNumber);
    }

    public void searchNextPagePop() {movieRepository.searchNextPagePop();}

    public void searchNextPageTop() {movieRepository.searchNextPageTop();}

}
