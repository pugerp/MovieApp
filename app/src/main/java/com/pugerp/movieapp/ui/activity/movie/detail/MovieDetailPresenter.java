package com.pugerp.movieapp.ui.activity.movie.detail;

import androidx.annotation.NonNull;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.pugerp.movieapp.R;
import com.pugerp.movieapp.data.MovieDetail;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.data.Review;
import com.pugerp.movieapp.data.Video;
import com.pugerp.movieapp.di.components.ActivityComponent;
import com.pugerp.movieapp.network.NetworkCallback;
import com.pugerp.movieapp.ui._core.base.BasePresenter;

import javax.inject.Inject;

public class MovieDetailPresenter extends BasePresenter<MovieDetailActivity, ActivityComponent> implements MovieDetailContract.Presenter {

    @Inject
    public MovieDetailPresenter() {
    }

    @Override
    public void setComponent(@NonNull ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void getMovie(int id) {
        view.showLoading();
        addSubscribe(networkInterface.getMovieDetail( id, view.getString(R.string.tmdb_key), view.getString(R.string.language)), new NetworkCallback<MovieDetail>() {
            @Override
            public void onSuccess(MovieDetail model) {
                view.onFetchingMovieDetailSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.onFecthingMovieDetailFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void getVideos(int id) {
        addSubscribe(networkInterface.getVideosById(id, view.getString(R.string.tmdb_key), view.getString(R.string.language)), new NetworkCallback<PagingResp<Video>>() {
            @Override
            public void onSuccess(PagingResp<Video> model) {
                view.onFetchingVideosSuccess(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void getReviews(int id, int page) {
        addSubscribe(networkInterface.getReviews(id, view.getString(R.string.tmdb_key), view.getString(R.string.language), page), new NetworkCallback<PagingResp<Review>>() {
            @Override
            public void onSuccess(PagingResp<Review> model) {
                view.onFetchingReviewSuccess(model);
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onFinish() {

            }
        });
    }
}
