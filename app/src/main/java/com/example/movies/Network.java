package com.example.movies;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Network {
    //Singleton pattern
    private static Network instance;

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
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
