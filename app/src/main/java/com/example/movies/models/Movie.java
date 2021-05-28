package com.example.movies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//model class for movies
public class Movie implements Parcelable {

    private String title;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private List<Genre> genres;



    @SerializedName("overview")
    private String movie_overview;


    //creating Constructor for properties
    public Movie(String title, String poster_path, String backdrop_path, String release_date, List<Genre> genres, String movie_overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.genres = genres;
        //this.genre = genres;
        this.movie_overview = movie_overview;

    }


    protected Movie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();
        genres = in.readArrayList(Genre.class.getClassLoader());
        movie_overview = in.readString();
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    //creating Getters for properties
    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() { return release_date; }

    public List<Genre> getGenres() { return genres; }

    public String getMovie_overview() { return movie_overview; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(release_date);
        dest.writeList(genres);
        dest.writeString(movie_overview);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", genre='" + genres + '\'' +
                ", movie_overview='" + movie_overview + '\'' +
                '}';
    }
}
