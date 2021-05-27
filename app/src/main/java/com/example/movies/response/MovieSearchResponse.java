package com.example.movies.response;

import com.example.movies.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

////This class is for getting multiple movies (Movie list)
public class MovieSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;

    @SerializedName("results")
    @Expose()
    private List<MovieModel> movies;

    public List<MovieModel> getMovies() {
        return movies;
    }

    @NotNull
    @Override
    public String toString() {
        return "MovieResponse{" +
                "total_count=" + total_count +
                ", movies=" + movies +
                '}';
    }
}
