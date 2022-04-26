package com.pugerp.movieapp.di.modules;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.pugerp.movieapp.di.annotations.ActivityContext;
import com.pugerp.movieapp.di.annotations.PerActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    @Singleton
    @ActivityContext
    Context provideActivityContext() {
        return activity;
    }
}
