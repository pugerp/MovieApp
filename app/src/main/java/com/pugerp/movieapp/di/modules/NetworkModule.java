package com.pugerp.movieapp.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pugerp.movieapp.BuildConfig;
import com.pugerp.movieapp.network.NetworkInterceptor;
import com.pugerp.movieapp.network.NetworkInterface;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    NetworkInterface provideNetworkInterface(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(NetworkInterface.class);
    }

    @Provides
    @Singleton
    OkHttpClient getOkHttpClient(NetworkInterceptor networkInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            if (!builder.interceptors().contains(httpLoggingInterceptor))
                builder.addInterceptor(httpLoggingInterceptor);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    NetworkInterceptor provideNetworkInterceptor() {
        return new NetworkInterceptor();
    }

    Gson getGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
