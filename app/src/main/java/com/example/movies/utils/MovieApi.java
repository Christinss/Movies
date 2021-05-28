package com.example.movies.utils;

import com.example.movies.models.Movie;
import com.example.movies.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


//client class that is receiving data objects from a service objects class
public interface MovieApi {
    //Search for movies
    //Search by ID
    @GET("3/movie/{movie_id}?")
    Call<Movie> getMovie(
            @Path("movie_id") int movie_id,
           @Query("api_key") String api_key
    );

    //GET popular movies
    //https://api.themoviedb.org/3/movie/popular
    //?api_key=fe3b8cf16d78a0e23f0c509d8c37caad
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

    //GET top rated movies
    //https://api.themoviedb.org/3/movie/top_rated
    //?api_key=fe3b8cf16d78a0e23f0c509d8c37caad
    @GET("/3/movie/top_rated")
    Call<MovieSearchResponse> getTopRated(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );
}
