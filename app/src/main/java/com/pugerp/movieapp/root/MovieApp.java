package com.pugerp.movieapp.root;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.pugerp.movieapp.di.components.ApplicationComponent;
import com.pugerp.movieapp.di.components.DaggerApplicationComponent;
import com.pugerp.movieapp.di.modules.ApplicationModule;
import com.pugerp.movieapp.di.modules.NetworkModule;


public class MovieApp extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        initDagger();
    }

    private void initDagger() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    @NonNull
    public ApplicationComponent getComponent() {
        return component;
    }
}
