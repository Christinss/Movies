package com.example.movies.request;

import com.example.movies.utils.Credentials;
import com.example.movies.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//Singleton for Retrofit2 API call
public class Service {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    //Singleton pattern
    private static Retrofit retrofit = retrofitBuilder.build();

    //Retrofit API
    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    //constructor of RetroServiceInterface
    public static MovieApi getMovieApi() {
        return movieApi;
    }
}
