package com.pugerp.movieapp.ui.activity.movie.list;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.pugerp.movieapp.R;
import com.pugerp.movieapp.adapter.MovieAdapter;
import com.pugerp.movieapp.data.Movie;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.databinding.AMovieListBinding;
import com.pugerp.movieapp.di.components.ActivityComponent;
import com.pugerp.movieapp.ui._core.base.BaseActivity;
import com.pugerp.movieapp.ui.activity.movie.detail.MovieDetailActivity;
import com.yatatsu.autobundle.AutoBundleField;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends BaseActivity<MovieListPresenter> implements MovieListContract.View {

    @NonNull
    @AutoBundleField
    int genreId;
    @AutoBundleField
    String genreName;

    private AMovieListBinding binding;
    private MovieAdapter movieAdapter;
    private boolean isLoading = false;
    private int currentPage = 1;
    private List<Movie> data = new ArrayList<>();

    public static void startActivity(BaseActivity activity, int genreId, String genreName) {
        activity.startActivity(MovieListActivityAutoBundle.builder(genreId, genreName).build(activity));
    }

    @Override
    protected View inflateLayout() {
        binding = AMovieListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void setup(Bundle saveInstanceState) {
        setToolbar();
        presenter.getMoviesByGenre(genreId, currentPage);
        initAdapter();
        initScrollListener();
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar.toolbar);
        setToolbarTitle(genreName);
        showBackButton(true);
    }

    private void setToolbarTitle(String title) {
        binding.toolbar.tvToolbar.setText(title);
    }

    private void showBackButton(boolean val) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(val);
            if (val)
                binding.toolbar.toolbar.getNavigationIcon().setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void initScrollListener() {
        binding.rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) binding.rvMovie.getLayoutManager();

                if (!isLoading) {
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == data.size() - 1) {
                        loadMore();
                    }
                }
            }
        });
    }

    @Override
    public void loadMore() {
        currentPage++;
        presenter.getMoviesByGenre(genreId, currentPage);
    }

    @Override
    public void initAdapter() {
        movieAdapter = new MovieAdapter(data, true, movie -> MovieDetailActivity.startActivity(this, movie.getId()));

        SpaceDecoration spaceDecoration = new SpaceDecoration((int) getResources().getDimension(R.dimen._14sdp));
//        spaceDecoration.setPaddingEdgeSide(false);
//        spaceDecoration.setPaddingStart(false);
//        spaceDecoration.setPaddingHeaderFooter(false);

        binding.rvMovie.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvMovie.addItemDecoration(spaceDecoration);
        binding.rvMovie.setAdapter(movieAdapter);
    }

    @Override
    protected void setComponent(@NonNull ActivityComponent component) {
        component.inject(this);
        presenter.onAttach(this);
        presenter.setComponent(component);
    }

    @Override
    public void onFetchingMoviesSuccess(PagingResp<Movie> model) {
        if (data != null && data.isEmpty()) {
            data.addAll(model.getResults());
        }

        for (Movie m : model.getResults()) {
            if (!data.contains(m)) {
                data.add(m);
            }
        }

        movieAdapter.notifyDataSetChanged();
        isLoading = false;
    }

    @Override
    public void onFetchingMoviesFailed(String message) {
        Toast.makeText(this, "Load data failed", Toast.LENGTH_SHORT).show();
    }
}
