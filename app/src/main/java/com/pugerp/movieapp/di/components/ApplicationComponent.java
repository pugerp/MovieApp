package com.pugerp.movieapp.di.components;

import android.content.Context;

import com.pugerp.movieapp.di.annotations.AppContext;
import com.pugerp.movieapp.di.modules.ApplicationModule;
import com.pugerp.movieapp.di.modules.NetworkModule;
import com.pugerp.movieapp.network.NetworkInterface;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApplicationModule.class, NetworkModule.class})
@Singleton
public interface ApplicationComponent {
    @AppContext
    Context getContext();
    NetworkInterface getNetworkInterface();
}
