package com.pugerp.movieapp.ui.activity.movie.detail;

import com.pugerp.movieapp.data.MovieDetail;
import com.pugerp.movieapp.ui._core.mvp.MvpView;

public interface MovieDetailContract {
    interface View extends MvpView {
        void onFetchingMovieDetailSuccess(MovieDetail model);
        void onFecthingMovieDetailFailed(String message);
    }
    interface Presenter {
        void getMovie(int id);
    }
}
