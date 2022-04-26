package com.pugerp.movieapp.ui.activity.main;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.pugerp.movieapp.R;
import com.pugerp.movieapp.adapter.GenreAdapter;
import com.pugerp.movieapp.adapter.MovieAdapter;
import com.pugerp.movieapp.data.Genre;
import com.pugerp.movieapp.data.Movie;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.databinding.AMainBinding;
import com.pugerp.movieapp.di.components.ActivityComponent;
import com.pugerp.movieapp.ui._core.base.BaseActivity;
import com.pugerp.movieapp.ui.activity.movie.detail.MovieDetailActivity;
import com.pugerp.movieapp.ui.activity.movie.list.MovieListActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private AMainBinding binding;
    private GenreAdapter genreAdapter;
    private MovieAdapter upcomingMovieAdapter;
    private MovieAdapter nowplayingMovieAdapter;

    @Override
    protected View inflateLayout() {
        binding = AMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void setup(Bundle saveInstanceState) {
        presenter.fetchGenres();
        presenter.fetchUpComingMovies();
        presenter.fetchNowPlayingMovies();
    }

    @Override
    protected void setComponent(@NonNull ActivityComponent component) {
        component.inject(this);
        presenter.onAttach(this);
        presenter.setComponent(component);
    }

    @Override
    public void onFetchingFailed(String message) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }

    public void isLoadGenre(boolean val) {
        binding.rvGenre.setVisibility(val ? View.GONE : View.VISIBLE);
        binding.loadingGenre.setVisibility(val ? View.VISIBLE : View.GONE);
    }

    public void isLoadingUpcoming(boolean val) {
        binding.rvUpcoming.setVisibility(val ? View.GONE : View.VISIBLE);
        binding.loadingUpcoming.setVisibility(val ? View.VISIBLE : View.GONE);
    }

    public void isLoadNowplaying(boolean val) {
        binding.rvNowPlaying.setVisibility(val ? View.GONE : View.VISIBLE);
        binding.loadingNowPlaying.setVisibility(val ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onFetchingSuccess(Genre model) {
        genreAdapter = new GenreAdapter(model.getGenres(), item -> {
            MovieListActivity.startActivity(this, item.getId(), item.getName());
        });

        SpaceDecoration spaceDecoration = new SpaceDecoration((int) getResources().getDimension(R.dimen._16sdp));
        spaceDecoration.setPaddingHeaderFooter(false);
        spaceDecoration.setPaddingEdgeSide(false);

        binding.rvGenre.addItemDecoration(spaceDecoration);
        binding.rvGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvGenre.setAdapter(genreAdapter);
    }

    public void onFetchingUpcomingSuccess(PagingResp<Movie> model) {
        upcomingMovieAdapter = new MovieAdapter(model.getResults(), false, movie -> MovieDetailActivity.startActivity(this, movie.getId()));

        SpaceDecoration spaceDecoration = new SpaceDecoration((int) getResources().getDimension(R.dimen._16sdp));
        spaceDecoration.setPaddingHeaderFooter(false);
        spaceDecoration.setPaddingEdgeSide(false);

        binding.rvUpcoming.addItemDecoration(spaceDecoration);
        binding.rvUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvUpcoming.setAdapter(upcomingMovieAdapter);
    }

    public void onFetchingUpcomingFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onFetchingNowplayingSuccess(PagingResp<Movie> model) {
        nowplayingMovieAdapter = new MovieAdapter(model.getResults(), false, movie -> MovieDetailActivity.startActivity(this, movie.getId()));

        SpaceDecoration spaceDecoration = new SpaceDecoration((int) getResources().getDimension(R.dimen._16sdp));
        spaceDecoration.setPaddingHeaderFooter(false);
        spaceDecoration.setPaddingEdgeSide(false);

        binding.rvNowPlaying.addItemDecoration(spaceDecoration);
        binding.rvNowPlaying.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvNowPlaying.setAdapter(nowplayingMovieAdapter);

    }

    public void onFetchingNowplayingFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}