package com.pugerp.movieapp.ui.activity.main;

import androidx.annotation.NonNull;

import com.pugerp.movieapp.R;
import com.pugerp.movieapp.data.Genre;
import com.pugerp.movieapp.data.Movie;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.di.components.ActivityComponent;
import com.pugerp.movieapp.network.NetworkCallback;
import com.pugerp.movieapp.ui._core.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainActivity, ActivityComponent> implements MainContract.Presenter {

    @Inject
    public MainPresenter() {
    }

    @Override
    public void setComponent(@NonNull ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void fetchGenres() {
        view.isLoadGenre(true);
        addSubscribe(networkInterface.getGenres(view.getString(R.string.tmdb_key), view.getString(R.string.language)), new NetworkCallback<Genre>() {
            @Override
            public void onSuccess(Genre model) {
                view.onFetchingSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.onFetchingFailed(message);
            }

            @Override
            public void onFinish() {
                view.isLoadGenre(false);
            }
        });
    }

    @Override
    public void fetchUpComingMovies() {
        view.isLoadingUpcoming(true);
        addSubscribe(networkInterface.getMoviesByUpComing(view.getString(R.string.tmdb_key), view.getString(R.string.language)), new NetworkCallback<PagingResp<Movie>>() {
            @Override
            public void onSuccess(PagingResp<Movie> model) {
                view.onFetchingUpcomingSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.onFetchingUpcomingFailed(message);
            }

            @Override
            public void onFinish() {
                view.isLoadingUpcoming(false);
            }
        });
    }

    @Override
    public void fetchNowPlayingMovies() {
        view.isLoadNowplaying(true);
        addSubscribe(networkInterface.getMoviesByNowPlaying(view.getString(R.string.tmdb_key), view.getString(R.string.language)), new NetworkCallback<PagingResp<Movie>>() {
            @Override
            public void onSuccess(PagingResp<Movie> model) {
                view.onFetchingNowplayingSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.onFetchingNowplayingFailed(message);
            }

            @Override
            public void onFinish() {
                view.isLoadNowplaying(false);
            }
        });
    }


}
