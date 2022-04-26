package com.pugerp.movieapp.ui.activity.movie.detail;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.pugerp.movieapp.BuildConfig;
import com.pugerp.movieapp.R;
import com.pugerp.movieapp.adapter.ReviewAdapter;
import com.pugerp.movieapp.data.Genre;
import com.pugerp.movieapp.data.GenresItem;
import com.pugerp.movieapp.data.MovieDetail;
import com.pugerp.movieapp.data.PagingResp;
import com.pugerp.movieapp.data.Review;
import com.pugerp.movieapp.data.Video;
import com.pugerp.movieapp.databinding.AMovieDetailBinding;
import com.pugerp.movieapp.di.components.ActivityComponent;
import com.pugerp.movieapp.ui._core.base.BaseActivity;
import com.yatatsu.autobundle.AutoBundleField;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends BaseActivity<MovieDetailPresenter> implements MovieDetailContract.View {

    @AutoBundleField
    int movieId;

    private AMovieDetailBinding binding;
    private ReviewAdapter reviewAdapter;
    private int currentPage = 1;
    private List<Review> data = new ArrayList<>();

    public static void startActivity(BaseActivity activity, int movieId) {
        activity.startActivity(MovieDetailActivityAutoBundle.builder(movieId).build(activity));
    }

    @Override
    protected View inflateLayout() {
        binding = AMovieDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void setup(Bundle saveInstanceState) {
        setToolbar();
        initAdapter();
        initScrollListener();

        presenter.getMovie(movieId);
        presenter.getVideos(movieId);
        presenter.getReviews(movieId, currentPage);
    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbar);

        showBackButton(true);
    }

    private void setToolbarTitle(String title) {
        binding.collapseLayout.setTitle(title);
    }

    private void showBackButton(boolean val) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(val);
            if (val)
                binding.toolbar.getNavigationIcon().setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
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
    protected void setComponent(@NonNull ActivityComponent component) {
        component.inject(this);
        presenter.onAttach(this);
        presenter.setComponent(component);
    }

    public void onFetchingMovieDetailSuccess(MovieDetail model) {
        setToolbarTitle(model.getTitle());

        binding.tvRating.setText(""+model.getVoteAverage());

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMAGE + model.getBackdropPath())
                .into(binding.ivBackdrop);

        binding.tvOverview.setText(model.getOverview());

        for (GenresItem item : model.getGenres()) {
            Chip chip = new Chip(this);
            chip.setText(item.getName());
            binding.chipGroup.addView(chip);
        }

    }

    public void onFecthingMovieDetailFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onFetchingVideosSuccess(PagingResp<Video> model) {
        List<String> videos = new ArrayList<>();
        for (Video v : model.getResults()) {
            videos.add(v.getKey());
        }

        YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_player);

        youTubePlayerFragment.initialize(getString(R.string.google_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideos(videos);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    public void onFetchingReviewSuccess(PagingResp<Review> model) {
        if (data != null && data.isEmpty()) {
            data.addAll(model.getResults());
        }

        for (Review r : model.getResults()) {
            if (!data.contains(r)) {
                data.add(r);
            }
        }

        reviewAdapter.notifyDataSetChanged();


    }

    public void initAdapter() {
        reviewAdapter = new ReviewAdapter(data);

        SpaceDecoration spaceDecoration = new SpaceDecoration((int) getResources().getDimension(R.dimen._8sdp));
        spaceDecoration.setPaddingEdgeSide(false);
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingHeaderFooter(false);

        binding.rvReview.setLayoutManager(new LinearLayoutManager(this));
        binding.rvReview.addItemDecoration(spaceDecoration);
        binding.rvReview.setAdapter(reviewAdapter);
    }

    public void initScrollListener() {
        binding.rvReview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.rvReview.getLayoutManager();

                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size() - 1) {
                    loadMore();
                }
            }
        });
    }

    public void loadMore() {
        currentPage++;
        presenter.getReviews(movieId, currentPage);

    }
}
