package com.pugerp.movieapp.ui.activity.movie.list;

import com.pugerp.movieapp.data.Movie;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.ui._core.mvp.MvpView;

public interface MovieListContract {
    interface View extends MvpView {
        void onFetchingMoviesSuccess(PagingResp<Movie> model);
        void onFetchingMoviesFailed(String message);
        void initScrollListener();
        void initAdapter();
        void loadMore();
    }
    interface Presenter {
        void getMoviesByGenre(int genreId, int page);
    }
}
