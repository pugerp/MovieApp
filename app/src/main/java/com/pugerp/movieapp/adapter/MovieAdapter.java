package com.pugerp.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pugerp.movieapp.BuildConfig;
import com.pugerp.movieapp.data.Movie;
import com.pugerp.movieapp.databinding.IMovieBinding;
import com.pugerp.movieapp.databinding.IMovieGridBinding;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MovieListener listener;
    private List<Movie> data;
    private boolean isGridItem;

    public MovieAdapter(List<Movie> data, boolean isGridItem, MovieListener listener) {
        this.listener = listener;
        this.data = data;
        this.isGridItem = isGridItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @NonNull IMovieBinding binding = IMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        @NonNull IMovieGridBinding gridBinding = IMovieGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return isGridItem ? new MovieGridHolder(gridBinding) : new MovieHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isGridItem) {
            ((MovieGridHolder) holder).bind(data.get(position));
        } else {
            ((MovieHolder) holder).bind(data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public class MovieHolder extends RecyclerView.ViewHolder {
        private IMovieBinding binding;

        public MovieHolder(@NonNull IMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            Glide.with(binding.ivMoviePoster)
                    .load(BuildConfig.BASE_URL_IMAGE + movie.getPosterPath())
                    .into(binding.ivMoviePoster);

            binding.tvRating.setText(""+movie.getVoteAverage());
            binding.parent.setOnClickListener(view -> listener.onClicked(movie));
        }
    }

    public class MovieGridHolder extends RecyclerView.ViewHolder {

        private IMovieGridBinding binding;

        public MovieGridHolder(@NonNull IMovieGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            Glide.with(binding.ivMoviePoster)
                    .load(BuildConfig.BASE_URL_IMAGE + movie.getPosterPath())
                    .into(binding.ivMoviePoster);

            binding.tvRating.setText(""+movie.getVoteAverage());
            binding.parent.setOnClickListener(view -> listener.onClicked(movie));
        }
    }


    public interface MovieListener {
        void onClicked(Movie movie);
    }
}
