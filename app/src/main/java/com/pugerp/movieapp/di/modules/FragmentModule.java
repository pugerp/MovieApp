package com.pugerp.movieapp.di.modules;

import androidx.fragment.app.Fragment;

import com.pugerp.movieapp.di.annotations.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    Fragment provideFragment() {
        return fragment;
    }
}
