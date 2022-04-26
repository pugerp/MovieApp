package com.pugerp.movieapp.ui.activity.movie.list;

import androidx.annotation.NonNull;

import com.pugerp.movieapp.R;
import com.pugerp.movieapp.data.Movie;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.di.components.ActivityComponent;
import com.pugerp.movieapp.network.NetworkCallback;
import com.pugerp.movieapp.ui._core.base.BasePresenter;

import javax.inject.Inject;

public class MovieListPresenter extends BasePresenter<MovieListActivity, ActivityComponent> implements MovieListContract.Presenter {

    @Inject
    public MovieListPresenter() {
    }

    @Override
    public void setComponent(@NonNull ActivityComponent component) {
        component.inject(this);
    }


    @Override
    public void getMoviesByGenre(int genreId, int page) {
        view.showLoading();
        addSubscribe(networkInterface.getMoviesByGenre(view.getString(R.string.tmdb_key),
                view.getString(R.string.language),
                String.valueOf(genreId), page),
                new NetworkCallback<PagingResp<Movie>>() {
            @Override
            public void onSuccess(PagingResp<Movie> model) {
                view.onFetchingMoviesSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.onFetchingMoviesFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
}
