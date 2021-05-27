package com.example.movies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//model class for movies
public class MovieModel implements Parcelable {

    private String title;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private int movie_id;
    private String genre;


    @SerializedName("overview")
    private String movie_overview;


    //creating Constructor for properties
    public MovieModel(String title, String poster_path, String backdrop_path, String release_date, String genre, int movie_id, String movie_overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.genre = genre;
        this.movie_id = movie_id;
        this.movie_overview = movie_overview;

    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();
        genre = in.readString();
        movie_id = in.readInt();
        movie_overview = in.readString();
    }


    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
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

    public String getGenre() { return genre; }

    public String getMovie_overview() { return movie_overview; }

    public int getMovie_id() { return movie_id; }



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
        dest.writeString(genre);
        dest.writeInt(movie_id);
        dest.writeString(movie_overview);
    }


    @Override
    public String toString() {
        return "MovieModel{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", genre='" + genre + '\'' +
                ", movie_id=" + movie_id +
                ", movie_overview='" + movie_overview + '\'' +
                '}';
    }
}
