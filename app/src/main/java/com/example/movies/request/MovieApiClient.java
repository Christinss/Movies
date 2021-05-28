package com.example.movies.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.Network;
import com.example.movies.models.Movie;
import com.example.movies.response.MovieSearchResponse;
import com.example.movies.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    //LiveData for search
    private static MovieApiClient instance;

    //LiveData for popular movies
    private MutableLiveData<List<Movie>> mMoviesPop;

    //LiveData for top rated movies
    private MutableLiveData<List<Movie>> mMoviesTop;

    //making Popular Runnable request
    private RetrieveMoviesRunnablePop retrieveMoviesRunnablePop;

    //making Popular Runnable request
    private RetrieveMoviesRunnableTop retrieveMoviesRunnableTop;

    //Singleton pattern
    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMoviesPop = new MutableLiveData<>();
        mMoviesTop = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMoviesPop() {
        return mMoviesPop;
    }
    public LiveData<List<Movie>> getMoviesTop() {
        return mMoviesTop;
    }

    //1 - This method is going to be called through classes
    public void searchMoviesPop(String query, int pageNumber) {

        if (retrieveMoviesRunnablePop != null) {
            retrieveMoviesRunnablePop = null;
        }

        retrieveMoviesRunnablePop = new RetrieveMoviesRunnablePop(query, pageNumber);

        final Future myHandler2 = Network.getInstance().networkIO().submit(retrieveMoviesRunnablePop);

        Network.getInstance().networkIO().schedule(() -> {
            //Cancelling the Retrofit call
            myHandler2.cancel(true);
        }, 1000, TimeUnit.MILLISECONDS);

    }

    public void searchMoviesTop(String query, int pageNumber) {

        if (retrieveMoviesRunnableTop != null) {
            retrieveMoviesRunnableTop = null;
        }

        retrieveMoviesRunnableTop = new RetrieveMoviesRunnableTop(query, pageNumber);

        final Future myHandler3 = Network.getInstance().networkIO().submit(retrieveMoviesRunnableTop);

        Network.getInstance().networkIO().schedule(() -> {
            //Cancelling the Retrofit call
            myHandler3.cancel(true);
        }, 1000, TimeUnit.MILLISECONDS);

    }

    //Retrieving data from RESTapi by runnable class
    private class RetrieveMoviesRunnablePop implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnablePop(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting the response objects

            try {
                Response response2 = getPop(query, pageNumber).execute();

                if (cancelRequest) {
                    return;
                }

                if (response2.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response2.body()).getMovies());
                    if (pageNumber == 1) {
                        //Sending data to live data
                        //PostValue: used for background thread
                        //setValue: not for background thread
                        mMoviesPop.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMoviesPop.getValue();
                        currentMovies.addAll(list);
                        mMoviesPop.postValue(currentMovies);
                    }
                } else {
                    String error = response2.errorBody().toString();
                    Log.v("Tag", "Error " + error);
                    mMoviesPop.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMoviesPop.postValue(null);
            }

        }

        //Search Method/ query
        private Call<MovieSearchResponse> getPop(String query, int pageNumber){
            return Service.getMovieApi().getPopular(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );
        }

        private void cancelRequest(){
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

    private class RetrieveMoviesRunnableTop implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnableTop(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting the response objects

            try {
                Response response3 = getTop(query, pageNumber).execute();

                if (cancelRequest) {
                    return;
                }

                if (response3.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response3.body()).getMovies());
                    if (pageNumber == 1) {
                        //Sending data to live data
                        //PostValue: used for background thread
                        //setValue: not for background thread
                        mMoviesTop.postValue(list);
                    } else {
                        List<Movie> currentMovies = mMoviesTop.getValue();
                        currentMovies.addAll(list);
                        mMoviesTop.postValue(currentMovies);
                    }
                } else {
                    String error = response3.errorBody().toString();
                    Log.v("Tag", "Error " + error);
                    mMoviesPop.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mMoviesTop.postValue(null);
            }

        }

        //Search Method/ query
        private Call<MovieSearchResponse> getTop(String query, int pageNumber){
            return Service.getMovieApi().getTopRated(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );
        }

        private void cancelRequest(){
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }

    }


}

