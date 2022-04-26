package com.pugerp.movieapp.ui.activity.main;

import com.pugerp.movieapp.data.Genre;
import com.pugerp.movieapp.ui._core.mvp.MvpView;

public interface MainContract {
    interface View extends MvpView {
        void onFetchingFailed(String message);
        void onFetchingSuccess(Genre model);

    }
    interface Presenter {
        void fetchGenres();
        void fetchUpComingMovies();
        void fetchNowPlayingMovies();
    }
}
