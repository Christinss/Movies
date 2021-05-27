package com.example.movies;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    //Singleton pattern
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if (instance == null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    //Retrofit calls in the background threads
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    //connecting Retrofit
    public ScheduledExecutorService networkIO() {
        return mNetworkIO;
    }

}
