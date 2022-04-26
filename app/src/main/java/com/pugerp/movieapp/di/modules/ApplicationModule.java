package com.pugerp.movieapp.di.modules;

import android.content.Context;

import com.pugerp.movieapp.di.annotations.AppContext;
import com.pugerp.movieapp.root.MovieApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    final MovieApp app;

    public ApplicationModule(MovieApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    @AppContext
    Context provideContext() {
        return this.app;
    }
}
