package com.pugerp.movieapp.di.components;

import com.pugerp.movieapp.ui.activity.main.MainActivity;
import com.pugerp.movieapp.ui.activity.main.MainPresenter;
import com.pugerp.movieapp.di.annotations.PerActivity;
import com.pugerp.movieapp.di.modules.ActivityModule;
import com.pugerp.movieapp.ui.activity.movie.detail.MovieDetailActivity;
import com.pugerp.movieapp.ui.activity.movie.detail.MovieDetailPresenter;
import com.pugerp.movieapp.ui.activity.movie.list.MovieListActivity;
import com.pugerp.movieapp.ui.activity.movie.list.MovieListPresenter;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
@PerActivity
public interface ActivityComponent extends ApplicationComponent {
    void inject(MainActivity activity);
    void inject(MainPresenter presenter);

    void inject(MovieListActivity activity);
    void inject(MovieListPresenter presenter);

    void inject(MovieDetailActivity activity);
    void inject(MovieDetailPresenter presenter);
}
